package com.kooknluke.montanabreweries;

import android.content.Context;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class Home extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        final Context context = this;

        final Button btnBeer = (Button) findViewById(R.id.btn_beer);
        final Button btnBreweries = (Button) findViewById(R.id.btn_breweries);
        final Button btnTown = (Button) findViewById(R.id.btn_town);
        final Button btnState = (Button) findViewById(R.id.btn_state);
        final Button btnNutrition = (Button) findViewById(R.id.btn_nutrition);
        final Button btnSeason = (Button) findViewById(R.id.btn_season);
        final Button btnBeerHistory = (Button) findViewById(R.id.btnBeerHistory);
        final Button btnConnection = (Button) findViewById(R.id.btnConnect);

//        RemoteDatabaseHelper  rbh = new RemoteDatabaseHelper();
//        rbh.mysqlReturn();

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

        btnNutrition.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intentNutrition = new Intent(context, Nutrition.class);
                startActivity(intentNutrition);
            }
        });

        btnBeerHistory.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, beerHistory.class);
                startActivity(i);
            }
        });

        btnConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, Connection.class);
                startActivity(i);
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
}
