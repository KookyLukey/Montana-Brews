package com.kooknluke.abrewforyou;

import android.util.Base64;
import android.util.Log;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class Connection {

    public JSONArray connect(String query) {
        try {
            JSONArray arr = connection(query);

            return arr;
        } catch (MalformedURLException e) {
            Log.e("ERROR", "Error performing connection :: " + e.getMessage());
        }
        return null;
    }

    public JSONArray connection(String query) throws MalformedURLException {
        URL url = null;
        HttpURLConnection connection = null;
        StringBuilder list = new StringBuilder();
        if (!query.contains("SELECT")) {
            url = new URL("http://www.mtbrews.net/Images/getImage.php?q=" + query);
        }
        else {
            url = new URL("http://www.mtbrews.net/Images/getBeers.php");
        }

        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("q1","bW9udGFuYWJfdXNlcg==");
            connection.setRequestProperty("q2","cGFzc3dvcmQ=");
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setAllowUserInteraction(false);
            connection.setConnectTimeout(1000);
            connection.setReadTimeout(1000);

            Log.d("CONNECTION REQUEST", "Query before being encoded before being sent :: " + query);
            byte[] queryOutputInByteArray = Base64.encode(query.getBytes("UTF-8"), Base64.DEFAULT);
            Log.d("CONNECTION REQUEST", "Encoded query sent to server :: " + new String(queryOutputInByteArray));
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(queryOutputInByteArray);
            outputStream.close();
            connection.connect();
            int status = connection.getResponseCode();

            switch (status) {
                case 200:
                case 201:
                    BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String line;
                    while ((line = br.readLine()) != null) {
                        list.append(line+"\n");
                    }
                    br.close();
                    String decodeResults = new String(Base64.decode(list.toString(),Base64.DEFAULT));
                    Log.d("CONNECTION", "Value returned :: " + decodeResults);
                    JSONArray arr = new JSONArray(decodeResults);
                    return arr;

            }
        } catch (IOException e) {
            Log.e("CONNECTION ERROR","Input Output error with connection :: ", e);
        } catch (Exception e) {
            Log.e("CONNECTION ERROR","Unexpected error when handling the Connection :: ", e);
        } finally {
            if (connection!= null) {
                try {
                    connection.disconnect();
                } catch(Exception e) {
                    Log.e("CONNECTION ERROR","Error Disconnecting from created connection :: ", e);
                }
            }
        }
        return null;
    }
}
