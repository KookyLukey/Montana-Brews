package com.kooknluke.abrewforyou;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
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

import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.net.URLEncoder;
import java.sql.Array;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Logger;

import com.kooknluke.abrewforyou.Constants.BeerResultCommonMessagesConstants;
import com.kooknluke.abrewforyou.Constants.QueryConstants;
import com.kooknluke.abrewforyou.DB.sqlLite.SqlLiteDbHelper;
import com.kooknluke.abrewforyou.dto.BeerDto;

public class Beer extends ActionBarActivity {

    private Logger logger = Logger.getLogger("Beer");
    private ProgressDialog progress;
    private Context context;
    private ArrayList<String> resultListToUse = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer);

        final ArrayList<String> list = new ArrayList<>();

        context = this;
        progress = new ProgressDialog(this);
        final ListView lv = (ListView) findViewById(R.id.typeBeerlv);
        final AdView adView = (AdView) findViewById(R.id.BeerAV);

        MobileAds.initialize(getApplicationContext(), "ca-app-pub-6225081440194649~2118773217");
        AdRequest adReq = new AdRequest.Builder()
//                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
//                .addTestDevice("502949AF1DC4C38881283DD133E9F4A1")
                .build();

        adView.loadAd(adReq);

        String[] types = getResources().getStringArray(R.array.type_of_beer);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                R.layout.beerlistview,
                R.id.firstLine,
                types
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
                String type = null;

                type = tv.getText().toString();

                new BeerTask(type).execute();
            }
        });
    }

    private class BeerTask extends AsyncTask<Void, Void, ArrayList<String>> {

        private ProgressDialog progress;
        private String type;

        BeerTask(String type) {
            progress = new ProgressDialog(context);
            this.type = type;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress.setTitle("Loading");
            progress.setMessage("Fetching your Beer...");
            progress.show();
        }


        @Override
        protected ArrayList<String> doInBackground(Void... params) {
            ArrayList<String> list = new ArrayList<>();
            SQLiteDatabase db = null;
            SqlLiteDbHelper s = new SqlLiteDbHelper(context);
            s.onCreate(db);
            db = s.getReadableDatabase();
            Cursor resultSet = db.query("beer",
                    new String[] {"beer_name"},
                    "type_of_beer LIKE ?",
                    new String[]{"%" + type + "%"},
                    null,
                    null,
                    null);

            while (resultSet.moveToNext()) {
                list.add(resultSet.getString(0));
                for (String columnName : resultSet.getColumnNames()) {
                    Log.d("RESULTS", columnName + " : " + resultSet.getString(resultSet.getColumnIndex(columnName)));
                }
            }

            if (list.isEmpty()) {
                list.add(BeerResultCommonMessagesConstants.NO_BEER_FOUND_OF_GIVEN_TYPE);
            }
            return list;
        }

        @Override
        protected void onPostExecute(ArrayList<String> result) {
            progress.dismiss();
            Intent i = new Intent(context, beerList.class);
            i.putExtra("beer", 1);
            Log.d("In Task", " LIST :: " + result);
            resultListToUse = result;
            i.putStringArrayListExtra("beer", resultListToUse);
            startActivity(i);
        }

    }

        @Override
        public boolean onCreateOptionsMenu (Menu menu){
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_beer, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected (MenuItem item){
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
        protected void onResume () {
            super.onResume();
        }
}
