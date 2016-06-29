package com.kooknluke.montanabreweries;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
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

import com.kooknluke.montanabreweries.ShowcaseViewFiles.ShowcaseView;
import com.kooknluke.montanabreweries.targets.PointTarget;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;


public class beerList extends ActionBarActivity {

    private ProgressDialog progress;
    private String beer;
    private boolean breweries = false;
    private String query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer_list);

        final Context context = this;
        final ListView lv = (ListView) findViewById(R.id.lvBeerList);
        progress = new ProgressDialog(this);

        Integer checker = 1;

        checker = getIntent().getIntExtra("Breweries", 0);

        switch (checker) {
            case 0:
                setTitle("List of Breweries");
                breweries = true;
                break;
            case 1:
                setTitle("List of Beer");
                breweries = false;
                break;
        }

        ArrayList<String> beerList;
        if (getIntent().getStringArrayListExtra("beer") == null) {
            beerList = ((StaticStore)this.getApplication()).getArray();
        }
        else {
            beerList = getIntent().getStringArrayListExtra("beer");
            ((StaticStore)this.getApplication()).setArrayList(beerList);
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                beerList){

            @Override
            public View getView(int position, View convertView,
                                ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                TextView textView=(TextView) view.findViewById(android.R.id.text1);

            /*YOUR CHOICE OF COLOR*/
                textView.setTextColor(Color.WHITE);

                return view;
            }
        };

        lv.setAdapter(arrayAdapter);

        SharedPreferences wmbPreference = PreferenceManager.getDefaultSharedPreferences(this);

        if (!breweries) {
            boolean isFirstRun = wmbPreference.getBoolean("FIRSTRUN", true);

            if (isFirstRun) {
                new ShowcaseView.Builder(this)
                        .setTarget(new PointTarget(325, 400))
                        .setContentTitle("Accessing more Information")
                        .setContentText("If you would like to know more about this brew, you can click this text and it will show you more.")
                        .hideOnTouchOutside()
                        .setStyle(0)
                        .build();

                SharedPreferences.Editor editor = wmbPreference.edit();
                editor.putBoolean("FIRSTRUN", false);
                editor.commit();
            }

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    try {
                        beer = URLEncoder.encode(((TextView) view).getText().toString(), "UTF-8");
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
                            Intent i = new Intent(context, beerInfo.class);
                            i.putExtra("beerName", beer);
                            startActivity(i);
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
        else {
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    final ArrayList<String> list = new ArrayList<>();
                    try {
                        beer = URLEncoder.encode(((TextView) view).getText().toString(), "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    query = "SELECT+*++FROM++%60beer%60++WHERE+brewery_name%3D%22" + beer + "%22";

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
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_beer_list, menu);
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
    protected void onResume() {
        super.onResume();
        progress.dismiss();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
