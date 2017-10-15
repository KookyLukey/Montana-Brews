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
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.kooknluke.abrewforyou.Constants.QueryConstants;
import com.kooknluke.abrewforyou.DB.sqlLite.SqlLiteDbHelper;

import java.util.ArrayList;


public class Town extends ActionBarActivity {

    private String town;
    private String query;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_town);

        context = this;
        final ListView lv = (ListView) findViewById(R.id.lvTown);
        final ArrayList<String> list = new ArrayList<>();
        final AdView adView = (AdView) findViewById(R.id.TownAV);

        MobileAds.initialize(getApplicationContext(), "ca-app-pub-6225081440194649~2118773217");
        AdRequest adReq = new AdRequest.Builder()
//                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
//                .addTestDevice("502949AF1DC4C38881283DD133E9F4A1")
                .build();

        adView.loadAd(adReq);

        ArrayList<String> townList = new ArrayList<>();
        townList.add(0, "Aurora CO");
        townList.add(1, "Belgrade MT");
        townList.add(2, "Bigfork MT");
        townList.add(3, "Billings MT");
        townList.add(4, "Black Eagle MT");
        townList.add(5, "Boulder CO");
        townList.add(6, "Bozeman MT");
        townList.add(7, "Colorado Springs CO");
        townList.add(8, "Detroit MI");
        townList.add(9, "Fort Collins CO");
        townList.add(10, "Great Falls MT");
        townList.add(11, "Hamilton MT");
        townList.add(12, "Helena MT");
        townList.add(13, "Livingston MT");
        townList.add(14, "Missoula MT");
        townList.add(15, "Stevensville MT");
        townList.add(16, "Wibaux MT");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                R.layout.beerlistview,
                R.id.firstLine,
                townList){

            @Override
            public View getView(int position, View convertView,
                                ViewGroup parent) {
                View view =super.getView(position, convertView, parent);

                TextView textView=(TextView) view.findViewById(R.id.firstLine);

            /*YOUR CHOICE OF COLOR*/
                textView.setTextColor(Color.WHITE);

                return view;
            }
        };

        lv.setAdapter(arrayAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            TextView tv = (TextView) view.findViewById(R.id.firstLine);
            String[] temp = tv.getText().toString().split(" ");
            int i = 1;
            while (i < temp.length - 1) {
                town = town.concat(" " + temp[i]);
                i++;
            }

            new TownTask().execute();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_town, menu);
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
    }

    private class TownTask extends AsyncTask<Void, Void, ArrayList<String>> {

        private ProgressDialog progress;

        TownTask() { progress = new ProgressDialog(context); }

        @Override
        protected void onPreExecute () {
            super.onPreExecute();
            progress.setTitle("Loading");
            progress.setMessage("Fetching Breweries...");
            progress.show();
        }


        @Override
        protected ArrayList<String> doInBackground (Void...params){
            ArrayList<String> list = new ArrayList<>();
            SQLiteDatabase db = null;
            SqlLiteDbHelper s = SqlLiteDbHelper.getInstance(context);
            db = s.getReadableDatabase();
            String[] userInputForQuery = new String[]{town};

            try {
                Cursor resultSet = db.rawQuery(QueryConstants.BREWERIES_BY_TOWN, userInputForQuery);
                while (resultSet.moveToNext()) {
                    list.add(resultSet.getString(0));
                    for (String columnName : resultSet.getColumnNames()) {
                        Log.d("RESULTS", columnName + " : " + resultSet.getString(resultSet.getColumnIndex(columnName)));
                    }
                }
            } catch (SQLiteException e) {
                Log.e("Error", "Error executing town query :: " + e.getMessage());
            } finally {
                if (db != null) {
                    db.close();
                }
                if (s != null) {
                    s.close();
                }
            }
            Log.d("QUERY", "Query used :: " + QueryConstants.BREWERIES_BY_TOWN);
            Log.d("PARAMETERS", "1 :: " + userInputForQuery[0]);


            if (list.isEmpty()) {
                list.add(context.getString(R.string.NO_BREWERIES_FOUND_FOR_TOWN));
            }

            Log.d("RESULTS", list.toString());

            return list;
        }

        @Override
        protected void onPostExecute (ArrayList<String> result){
            progress.dismiss();
            Intent i = new Intent(context, beerList.class);
            i.putExtra("Breweries", 0);
            Log.d("In Task", " LIST :: " + result);
            i.putStringArrayListExtra("beer", result);
            startActivity(i);
        }
    }

}
