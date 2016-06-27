package com.kooknluke.montanabreweries;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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
import java.util.List;


public class townBeerList extends ActionBarActivity {

    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_town_beer_list);

        final Context context = this;
        final ArrayList<String> list = new ArrayList<>();

        final ListView lv = (ListView) findViewById(R.id.lvTown);
        List<String> beerList = getIntent().getStringArrayListExtra("beer");
        progress = new ProgressDialog(this);
        final AdView adView = (AdView) findViewById(R.id.TownBreweryListAV);

        MobileAds.initialize(getApplicationContext(), "ca-app-pub-6225081440194649~2118773217");
        AdRequest adReq = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("502949AF1DC4C38881283DD133E9F4A1")
                .build();

        adView.loadAd(adReq);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                beerList){

            @Override
            public View getView(int position, View convertView,
                                ViewGroup parent) {
                View view =super.getView(position, convertView, parent);

                TextView textView=(TextView) view.findViewById(android.R.id.text1);

            /*YOUR CHOICE OF COLOR*/
                textView.setTextColor(Color.WHITE);

                return view;
            }
        };

        lv.setAdapter(arrayAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    progress.setTitle("Loading");
                    progress.setMessage("Fetching your brewery");
                    progress.show();

                    String title = URLEncoder.encode(((TextView)view).getText().toString(), "UTF-8");

                    String query = "%20SELECT%20*%20FROM%20%60beer%60%20WHERE%20brewery_name%20%3D%20%22" + title + "%22";

                    Connection conn = new Connection();
                    JSONArray arr = conn.connect(query);
                    if (arr == null) {
                        list.add("No beers found");
                    } else {
                        for (int i = 0; i < arr.length(); i++) {
                            JSONObject sys = arr.getJSONObject(i);
                            String temp = sys.getString("_id");
                            list.add(temp);
                        }
                    }
                } catch (JSONException e){
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                Intent i = new Intent(context, beerList.class);
                if (list.isEmpty()) {
                    list.add("No Brewery Found");
                    i.putStringArrayListExtra("beer", list);
                    startActivity(i);
                    list.clear();
                }
                else {
                    i.putStringArrayListExtra("beer", list);
                    startActivity(i);
                    list.clear();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_town_beer_list, menu);
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
