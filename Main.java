package com.company;

import com.company.utils.Requests;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        JSONObject response = new JSONObject();
        JSONObject res = new JSONObject();

        try {
             response = Requests.get("http://127.0.0.1:8000");
        } catch (IOException e) {
            e.getMessage();
        }
        System.out.println(response.toString());

        try {
            res = Requests.post("http://127.0.0.1:8000",response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(res.toString());
    }
}
