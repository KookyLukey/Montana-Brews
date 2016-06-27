package com.kooknluke.montanabreweries;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;


public class Seasons extends ActionBarActivity {

    private String season;
    private Button btnSearch;
    private ProgressDialog progress;
    private String query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seasons);

        final Context context = this;
        final ListView lv = (ListView) findViewById(R.id.lvSeasons);
        final ArrayList<String> list = new ArrayList<>();

        progress = new ProgressDialog(this);
        final AdView adView = (AdView) findViewById(R.id.SeasonsAV);

        MobileAds.initialize(getApplicationContext(), "ca-app-pub-6225081440194649~2118773217");
        AdRequest adReq = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("502949AF1DC4C38881283DD133E9F4A1")
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
                try {
                    TextView tv = (TextView) view.findViewById(R.id.firstLine);
                    String season = URLEncoder.encode(tv.getText().toString(), "UTF-8");

                    query = "SELECT+*+FROM+seasons+WHERE+season+%3D+%27" + season + "%27";

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                new AsyncTask<Void, Void, Void>() {

                    @Override
                    protected void onPreExecute() {
                        super.onPreExecute();
                        progress.setTitle("Loading");
                        progress.setMessage("Fetching your Beer...");
                        progress.show();
                    }


                    @Override
                    protected Void doInBackground(Void... params) {
                        try {
                            Connection conn = new Connection();
                            JSONArray arr = conn.connect(query);
                            if (arr == null) {
                                list.add("No Beer Found");
                            } else {
                                for (int i = 0; i < arr.length(); i++) {
                                    JSONObject sys = arr.getJSONObject(i);
                                    String temp = sys.getString("_id");
                                    list.add(temp);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } finally {
                            Intent i = new Intent(context, beerList.class);
                            i.putExtra("Breweries", 1);
                            if (list.isEmpty()) {
                                list.add("No Beer Found");
                                i.putStringArrayListExtra("beer", list);
                                startActivity(i);
                                list.clear();
                            } else {
                                i.putStringArrayListExtra("beer", list);
                                startActivity(i);
                                list.clear();
                            }
                        }
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void result) {
                        progress.dismiss();
                    }
                }.execute();
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
}
