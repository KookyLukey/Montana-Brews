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


public class State extends ActionBarActivity {

    private String state;
    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state);

        final Context context = this;
        final ListView lv = (ListView) findViewById(R.id.lvState);
        final ArrayList<String> list = new ArrayList<>();

        progress = new ProgressDialog(this);

        ArrayList<String> townList = new ArrayList<>();
        townList.add(0, "Montana");


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                townList){

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

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {

                    progress.setTitle("Loading");
                    progress.setMessage("Fetching your beer from" + ((TextView) view).getText().toString());
                    progress.show();

                    String state = URLEncoder.encode(((TextView) view).getText().toString(), "UTF-8");

                    String query = "SELECT+breweries._id+FROM+breweries+JOIN+towns+ON+breweries.name_of_town+%3D+towns._id+WHERE+towns.state+%3D+%27"+state+"%27";

                    try {
                        Connection conn = new Connection();
                        JSONArray arr = conn.connect(query);
                        if (arr == null) {
                            list.add("No Brewery Found");
                        }
                        else {
                            for (int i = 0; i < arr.length(); i++) {
                                JSONObject sys = arr.getJSONObject(i);
                                String temp = sys.getString("_id");
                                list.add(temp);
                            }
                        }
                    }
                    catch (JSONException e){
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }

                    Intent i = new Intent(context, townBeerList.class);
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
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_state, menu);
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
