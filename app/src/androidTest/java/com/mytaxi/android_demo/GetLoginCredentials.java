package com.mytaxi.android_demo;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GetLoginCredentials {

    private static String BaseURI = "https://randomuser.me/api/?seed=a1f30d446f820665";

    public static HashMap<String, String> getLoginCredentials() throws Exception {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(BaseURI)
                .build();
        Response responses = null;
        try {
            responses = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
            String jsonData = responses.body().string();
            JSONObject httpJSONResponse = new JSONObject(jsonData);
            String ResponseUserName = TestUtil.getValueByJPath(httpJSONResponse, "/results[0]/login/username");
            String ResponsePassword = TestUtil.getValueByJPath(httpJSONResponse, "/results[0]/login/password");
            HashMap<String, String> logincredentials = new HashMap<String, String>();
            logincredentials.put("Userid", ResponseUserName);
            logincredentials.put("Pwd", ResponsePassword);
            return logincredentials;
    }
}



