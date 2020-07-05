package com.company.utils;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public final class Requests {
    private final static int timeout = 10000;
    private final static String GET = "GET";
    private final static String POST = "POST";


    public static JSONObject get(String url_str) throws IOException {
        HttpURLConnection connection = createRequest(url_str,GET);

        String str;
        if ( connection.getResponseCode() != 200){
            throw new RuntimeException(String.valueOf(connection.getResponseCode()));
        }
        StringBuilder builder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        while ((str = bufferedReader.readLine()) != null){
            builder.append(str);
        }
        return new JSONObject(builder.toString());
    }

    public static JSONObject post(String url_str, JSONObject jsonObject) throws IOException {
        HttpURLConnection connection = createRequest(url_str,POST);
        String str = "";
        connection.getOutputStream().write(jsonObject.toString().getBytes());
        if ( connection.getResponseCode() != 200){
            throw new RuntimeException();
        }
        StringBuilder builder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        while ((str = bufferedReader.readLine()) != null){
            builder.append(str);
        }
        return new JSONObject(builder.toString());
    }

    private static HttpURLConnection createRequest(String url_str, String method) throws IOException {
        URL url = new URL(url_str);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod(method);
        connection.setConnectTimeout(timeout);
        connection.setRequestProperty("Content-Type","application/json");
        connection.setRequestProperty("User-Agent", "Mozilla/5.0");

        if (method.equals(POST)){
            connection.setDoOutput(true);
        }

        return connection;
    }

}
