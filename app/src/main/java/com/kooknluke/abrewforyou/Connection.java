package com.kooknluke.abrewforyou;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


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
        HttpURLConnection c = null;
        StringBuilder list = new StringBuilder();
        if (!query.contains("SELECT")) {
            url = new URL("http://www.mtbrews.net/images/getImage.php?q=" + query);
        }
        else {
            url = new URL("http://www.mtbrews.net/images/getBeers.php?q=" + query);
        }

        try {
            c = (HttpURLConnection) url.openConnection();
            c.setRequestMethod("GET");
            c.setRequestProperty("Content-length", "0");
            c.setUseCaches(false);
            c.setAllowUserInteraction(false);
            c.setConnectTimeout(1000);
            c.setReadTimeout(1000);
            c.connect();
            int status = c.getResponseCode();

            switch (status) {
                case 200:
                case 201:
                    BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
                    String line;
                    while ((line = br.readLine()) != null) {
                        list.append(line+"\n");
                    }
                    br.close();
                    JSONArray arr = new JSONArray(list.toString());
                    return arr;

            }
        } catch (IOException e) {
 //           Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
        } catch (Exception e) {
//            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
        } finally {
            if (c!= null) {
                try {
                    c.disconnect();
                } catch(Exception e) {
//                    Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
                }
            }
        }
        return null;
    }
}
