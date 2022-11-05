package assignment;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

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
}
