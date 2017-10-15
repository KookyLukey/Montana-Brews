package com.kooknluke.abrewforyou;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.kooknluke.abrewforyou.Constants.BeerResultCommonMessagesConstants;
import com.kooknluke.abrewforyou.Constants.QueryConstants;
import com.kooknluke.abrewforyou.DB.sqlLite.SqlLiteDbHelper;

import java.util.ArrayList;


public class Breweries extends ActionBarActivity {

    private Button btnShowBeers;
    private String query;
    private String userInput;
    private BreweriesTask breweriesTask;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breweries);

        final ArrayList<String> list = new ArrayList<>();

        context = this;
        breweriesTask = new BreweriesTask();
        final AdView adView = (AdView) findViewById(R.id.BreweriesAV);
        btnShowBeers = (Button) findViewById(R.id.btnShowBeers);
        final EditText etSearchBreweries = (EditText) findViewById(R.id.etSearchBreweries);

        MobileAds.initialize(getApplicationContext(), "ca-app-pub-6225081440194649~2118773217");
        AdRequest adReq = new AdRequest.Builder()
//                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
//                .addTestDevice("502949AF1DC4C38881283DD133E9F4A1")
                .build();

        adView.loadAd(adReq);

        btnShowBeers.setEnabled(true);

        btnShowBeers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userInput = etSearchBreweries.getText().toString();

                new BreweriesTask().execute();
            }
        });
    }

    private class BreweriesTask extends AsyncTask<Void, Void, ArrayList<String>> {

        private ProgressDialog progress;

        BreweriesTask() {
            progress = new ProgressDialog(context);
        }

        @Override
        protected void onPreExecute () {
            super.onPreExecute();
            progress.setTitle("Loading");
            progress.setMessage("Fetching your Beer...");
            progress.show();
        }


        @Override
        protected ArrayList<String> doInBackground (Void...params){
            ArrayList<String> list = new ArrayList<>();
            SQLiteDatabase db = null;
            SqlLiteDbHelper s = SqlLiteDbHelper.getInstance(context);
            db = s.getReadableDatabase();
            String[] userInputForQuery = new String[]{"%" + userInput + "%"};

            try {
                Cursor resultSet = db.rawQuery(QueryConstants.BEERS_FROM_BREWERY_LIKE_NAME_QUERY, userInputForQuery);
                while (resultSet.moveToNext()) {
                    list.add(resultSet.getString(0));
                    for (String columnName : resultSet.getColumnNames()) {
                        Log.d("RESULTS", columnName + " : " + resultSet.getString(resultSet.getColumnIndex(columnName)));
                    }
                }
            } catch (SQLiteException e) {
                Log.e("Error", "Error executing breweries query :: " + e.getMessage());
            } finally {
                if (db != null) {
                    db.close();
                }
                if (s != null) {
                    s.close();
                }
            }
            Log.d("QUERY", "Query used :: " + QueryConstants.BEERS_FROM_BREWERY_LIKE_NAME_QUERY);
            Log.d("PARAMETERS", "1 :: " + userInputForQuery[0]);


            if (list.isEmpty()) {
                list.add(BeerResultCommonMessagesConstants.NO_BEER_FOUND_FOR_BREWERY);
            }

            Log.d("RESULTS", list.toString());

            return list;
        }

        @Override
        protected void onPostExecute (ArrayList<String> result){
            progress.dismiss();
            Intent i = new Intent(context, beerList.class);
            i.putExtra("beer", 1);
            Log.d("In Task", " LIST :: " + result);
            i.putStringArrayListExtra("beer", result);
            startActivity(i);
        }

    }

    @Override
    public void onResume() {
        super.onResume();

        if (btnShowBeers.isEnabled() == false) {
            btnShowBeers.setEnabled(true);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_breweries, menu);
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
