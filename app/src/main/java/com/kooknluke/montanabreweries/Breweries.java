package com.kooknluke.montanabreweries;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;


public class Breweries extends ActionBarActivity {

    private Button btnShowBeers;
    private ProgressDialog progress;
    private String query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breweries);

        final ArrayList<String> list = new ArrayList<>();

        final Context context = this;
        progress = new ProgressDialog(this);
        final AdView adView = (AdView) findViewById(R.id.BreweriesAV);
        btnShowBeers = (Button) findViewById(R.id.btnShowBeers);
        final EditText etSearchBreweries = (EditText) findViewById(R.id.etSearchBreweries);

        MobileAds.initialize(getApplicationContext(), "ca-app-pub-6225081440194649~2118773217");
        AdRequest adReq = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("502949AF1DC4C38881283DD133E9F4A1")
                .build();

        adView.loadAd(adReq);

        btnShowBeers.setEnabled(true);

        btnShowBeers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            String userInput = URLEncoder.encode(etSearchBreweries.getText().toString());

            query = "SELECT+*+FROM+beer+WHERE+brewery_name+LIKE+%27%25" + userInput + "%25%27";

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
                            list.add("No Beer Found for Brewery");
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
                            list.add("No Beer Found for Brewery");
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
    public void onResume() {
        super.onResume();
        progress.dismiss();

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
