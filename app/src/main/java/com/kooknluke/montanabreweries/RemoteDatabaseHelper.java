package com.kooknluke.montanabreweries;

/**
 * Created by Dan Poss on 4/3/2016.
 */
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

public class RemoteDatabaseHelper {

    private String TAG = "";

    protected void mysqlReturn() {
        String conn = "http://www.mtbrews.net/images/getBeers.php";
        StringBuilder builder = new StringBuilder();

        try {
            URL url = new URL(conn);
            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet();
            request.setURI(new URI(conn));
            HttpResponse response = client.execute(request);
            StatusLine status = response.getStatusLine();

            if (status.equals(200)) {
                HttpEntity entity = response.getEntity();
                InputStream content = entity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(content));
                String line;
                while ((line = reader.readLine()) != null){
                    builder.append(line);
                }
                Log.e("File: ", builder.toString());
            }
            else {
                Log.e(TAG, "Failed to download File....");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
