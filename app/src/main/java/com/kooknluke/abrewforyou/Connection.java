package com.kooknluke.abrewforyou;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Connection {

    public JSONArray connect(String query) {
        try {
            JSONArray arr = connection(query);

            return arr;
        } catch (MalformedURLException e) {

        }
        return null;
    }

    public JSONArray connection(String query) throws MalformedURLException {
        URL url = null;
        HttpURLConnection connection = null;
        StringBuilder list = new StringBuilder();
        if (!query.contains("SELECT")) {
            url = new URL("http://www.mtbrews.net/images/getImage.php?q=" + query);
        }
        else {
            url = new URL("http://www.mtbrews.net/images/getBeers.php?q=" + query);
        }

        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-length", "0");
            connection.setUseCaches(false);
            connection.setAllowUserInteraction(false);
            connection.setConnectTimeout(1000);
            connection.setReadTimeout(1000);
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
                    JSONArray arr = new JSONArray(list.toString());
                    return arr;

            }
        } catch (IOException e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
        } finally {
            if (connection!= null) {
                try {
                    connection.disconnect();
                } catch(Exception e) {
                    Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
                }
            }
        }
        return null;
    }
}
