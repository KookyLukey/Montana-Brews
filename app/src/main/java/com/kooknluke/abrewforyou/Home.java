package com.kooknluke.abrewforyou;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;


public class Home extends ActionBarActivity {

    private static final String BASE64_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjQuNKwjTMCAemNvJl0tK/xiybaJ3AsAZsAmezH3hmszIag9i74I47B7HmNbQiLAQCnqJdXVhTvE6yLK/58EpCsDO+62D6YSSQKW9VGDYPFiJQL1pzaeOS9W4SJmIWiX5+ABP/G9wuTY1qIoRt6sOeaVP6Q+iub9Aw3sM9E4pV6gbFWdi0CpCnOb00KV6cy7XUbI+XrRqa8PNhwIpbak3oCqcmtKb2OsG7fKXs5I/yB4l1JazN9F5YqGtr6stWEOVYvwHmU/5JjaS+/u9fF3ae2wI4YWjJBHH5TYoz1LricPHxtksCcfr//deFgJNzexy4BoTYoZ4P1g3ZKgldeZAoQIDAQAB";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideActionBar();
        setContentView(R.layout.activity_home);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        final Context context = this;

        final Button btnBeer = (Button) findViewById(R.id.btn_beer);
        final Button btnBreweries = (Button) findViewById(R.id.btn_breweries);
        final Button btnTown = (Button) findViewById(R.id.btn_town);
        final Button btnState = (Button) findViewById(R.id.btn_state);
        final Button btnSeason = (Button) findViewById(R.id.btn_season);
        final Button btnMap = (Button) findViewById(R.id.btnMap);
        final AdView adView = (AdView) findViewById(R.id.adView);

//        AdRequest adReq = new AdRequest.Builder().build();

        MobileAds.initialize(getApplicationContext(), "ca-app-pub-6225081440194649~2118773217");
        AdRequest adReq = new AdRequest.Builder()
//                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
//                .addTestDevice("502949AF1DC4C38881283DD133E9F4A1")
                .build();

        adView.loadAd(adReq);

        btnBeer.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intentBeer = new Intent(context, Beer.class);
                startActivity(intentBeer);
            }
        });

        btnBreweries.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intentBreweries = new Intent(context, Breweries.class);
                startActivity(intentBreweries);
            }
        });

        btnTown.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intentTown = new Intent(context, Town.class);
                startActivity(intentTown);
            }
        });

        btnState.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intentState = new Intent(context, State.class);
                startActivity(intentState);
            }
        });

        btnSeason.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intentSeason = new Intent(context, Seasons.class);
                startActivity(intentSeason);
            }
        });

        btnMap.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intentBeer = new Intent(context, Map.class);
                startActivity(intentBeer);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
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

    private void hideActionBar(){
        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        // Remember that you should never show the action bar if the
        // status bar is hidden, so hide that too if necessary.
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }
}
