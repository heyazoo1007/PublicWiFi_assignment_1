package assignment;

import com.squareup.okhttp.*;

import java.io.IOException;

public class Rest {

    public void callGET() {
        try {
            OkHttpClient client = new OkHttpClient();
            String strURL = "http://";
            Request.Builder builder = new Request.Builder().url(strURL).get();
            builder.addHeader("Content-type", "application/json");
            Request request = builder.build();
            Response response = client.newCall(request).execute();

            if (response.isSuccessful()) {
                ResponseBody body = response.body();
                String responseString = body.string();
                System.out.println("[responseBody] : " + responseString);
                body.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void callPost() {
        try {
            OkHttpClient client = new OkHttpClient();
            String strURL = "http://openapi.seoul.go.kr:8088/sample/xml/TbPublicWifiInfo/1/5/";
            String strBody = "{\"parameter\":\"NA\"}";
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), strBody);
            Request.Builder builder = new Request.Builder().url(strURL).post(requestBody);
            builder.addHeader("Content-type", "application/json");
            Request request = builder.build();
            Response response = client.newCall(request).execute();

            if(response.isSuccessful()){
                ResponseBody body = response.body();
                String responseString = body.string();
                System.out.println("[responseBody]:" + responseString);
                body.close();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}

