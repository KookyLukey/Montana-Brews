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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;


public class Town extends ActionBarActivity {

    private String town;
    private ProgressDialog progress;
    private String query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_town);

        final Context context = this;
        final ListView lv = (ListView) findViewById(R.id.lvTown);
        final ArrayList<String> list = new ArrayList<>();
        progress = new ProgressDialog(this);

        ArrayList<String> townList = new ArrayList<>();
        townList.add(0, "Belgrade");
        townList.add(1, "Billings");
        townList.add(2, "Bozeman");
        townList.add(3, "Hamilton");
        townList.add(4, "Helena");
        townList.add(5, "Livingston");
        townList.add(6, "Missoula");
        townList.add(7, "Stevensville");
        townList.add(8, "Wibaux");

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
                try {
                    TextView tv =(TextView) view.findViewById(R.id.firstLine);
                    String town = URLEncoder.encode(tv.getText().toString(), "UTF-8");

                    query = "SELECT+*+FROM+breweries+WHERE+name_of_town+%3D+%27" + town + "%27";

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                new AsyncTask<Void, Void, Void>() {

                    @Override
                    protected void onPreExecute() {
                        super.onPreExecute();
                        progress.setTitle("Loading");
                        progress.setMessage("Fetching Breweries...");
                        progress.show();
                    }


                    @Override
                    protected Void doInBackground(Void... params) {
                        try {
                            Connection conn = new Connection();
                            JSONArray arr = conn.connect(query);
                            if (arr == null) {
                                list.add("No Brewery Found");
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
                            if (list.isEmpty()) {
                                list.add("No Brewery Found");
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
        progress.dismiss();
    }
}
