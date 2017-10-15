package com.kooknluke.abrewforyou;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.kooknluke.abrewforyou.Constants.BeerResultCommonMessagesConstants;
import com.kooknluke.abrewforyou.Constants.QueryConstants;
import com.kooknluke.abrewforyou.DB.sqlLite.SqlLiteDbHelper;

import java.util.ArrayList;


public class Seasons extends ActionBarActivity {

    private String season;
    private Button btnSearch;
    private ProgressDialog progress;
    private Context context;
    private SeasonTask seasonTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seasons);

        context = this;
        final ListView lv = (ListView) findViewById(R.id.lvSeasons);
        final ArrayList<String> list = new ArrayList<>();

        progress = new ProgressDialog(this);
        final AdView adView = (AdView) findViewById(R.id.SeasonsAV);

        MobileAds.initialize(getApplicationContext(), "ca-app-pub-6225081440194649~2118773217");
        AdRequest adReq = new AdRequest.Builder()
//                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
//                .addTestDevice("502949AF1DC4C38881283DD133E9F4A1")
                .build();

        adView.loadAd(adReq);

        ArrayList<String> seasonList = new ArrayList<>();
        seasonList.add(0, "Spring");
        seasonList.add(1, "Summer");
        seasonList.add(2, "Fall");
        seasonList.add(3, "Winter");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                R.layout.beerlistview,
                R.id.firstLine,
                seasonList
        ) {
            @Override
            public View getView(int position, View convertView,
                                ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView textView = (TextView) view.findViewById(R.id.firstLine);

                textView.setTextColor(Color.WHITE);

                return view;
            }
        };

        lv.setAdapter(arrayAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView tv = (TextView) view.findViewById(R.id.firstLine);
                String season = tv.getText().toString();

                new SeasonTask().execute();

            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_seasons, menu);
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

    @Override
    public void onResume() {
        super.onResume();
        progress.dismiss();
    }

    private class SeasonTask extends AsyncTask<Void, Void, ArrayList<String>> {

        @Override
        protected void onPreExecute() {
            progress.setTitle("Loading");
            progress.setMessage("Fetching your Beer...");
            progress.show();
        }

        @Override
        protected ArrayList<String> doInBackground(Void... voids) {
            ArrayList<String> list = new ArrayList<>();

            String query = QueryConstants.GET_BEER_FROM_SEASON;
            SQLiteDatabase db = null;
            SqlLiteDbHelper s = SqlLiteDbHelper.getInstance(context);
            db = s.getReadableDatabase();
            String[] userInputForQuery = new String[]{"%" + season + "%"};

            try {
                Cursor resultSet = db.rawQuery(query, userInputForQuery);
                while (resultSet.moveToNext()) {
                    list.add(resultSet.getString(0));
                    for (String columnName : resultSet.getColumnNames()) {
                        Log.d("RESULTS", columnName + " : " + resultSet.getString(resultSet.getColumnIndex(columnName)));
                    }
                }
            } catch (SQLiteException e) {
                Log.e("Error", "Error executing Seasons query :: " + e.getMessage());
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
                list.add(BeerResultCommonMessagesConstants.NO_BEER_FOUND_FOR_GIVEN_SEASON);
            }

            return list;
        }

        @Override
        protected void onPostExecute(ArrayList<String> results) {
            progress.dismiss();
            Intent i = new Intent(context, beerList.class);
            i.putExtra("Breweries", 1);
            Log.d("In Task", " LIST :: " + results);
            i.putStringArrayListExtra("beer", results);
            startActivity(i);
        }

    }
}
