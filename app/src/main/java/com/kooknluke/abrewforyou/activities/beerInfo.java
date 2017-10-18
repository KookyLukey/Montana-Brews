package com.kooknluke.abrewforyou.activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.kooknluke.abrewforyou.Connection;
import com.kooknluke.abrewforyou.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;


public class beerInfo extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer_info);

        final ImageView imageView = (ImageView) findViewById(R.id.ivBeerImage);
        final TextView name = (TextView) findViewById(R.id.tvBeerDescriptionName);
        final TextView ABV = (TextView) findViewById(R.id.tvABV);
        final TextView Brewery = (TextView) findViewById(R.id.tvBreweryName);
        final TextView description = (TextView) findViewById(R.id.tvBeerDescription);
        final AdView adView = (AdView) findViewById(R.id.BeerInfoAV);

        MobileAds.initialize(getApplicationContext(), "ca-app-pub-6225081440194649~2118773217");
        AdRequest adReq = new AdRequest.Builder()
//                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
//                .addTestDevice("502949AF1DC4C38881283DD133E9F4A1")
                .build();

        adView.loadAd(adReq);

        String beerName = getIntent().getStringExtra("beerName");

        Connection conn = new Connection();

        JSONArray arr = conn.connect(beerName);

        try {
            beerName = URLDecoder.decode(beerName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        try {
            if (arr == null) {
                name.setText(beerName);
                imageView.setImageResource(R.drawable.imageunavailable);
                description.setText("No Description Found");
            }
            else if (arr.getString(0).contains("null")) {
                    name.setText(beerName);
                    imageView.setImageResource(R.drawable.imageunavailable);
                    description.setText("No Description Found");
            }
            else {

                name.setText(arr.getString(0));

                byte[] decodedString = Base64.decode(arr.getString(1), Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                imageView.setImageBitmap(decodedByte);

                description.setText(arr.getString(2));
            }

            beerName = URLEncoder.encode(beerName, "UTF-8");

            String query = "SELECT%20ABV,%20brewery_name%20FROM%20beer%20WHERE%20_id%20=%20%27" + beerName + "%27";

            arr = conn.connect(query);

            if (arr == null) {
                ABV.append(" Not Found");
                Brewery.append(" Not Found");
            }
            else {
                JSONObject obj = arr.getJSONObject(0);
                ABV.append(" " + obj.getString("ABV") + "%");
                Brewery.append(" " + obj.getString("brewery_name"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_beer_info, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
