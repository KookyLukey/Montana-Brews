package com.kooknluke.montanabreweries;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;


public class Breweries extends ActionBarActivity {

    private Button btnShowBeers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breweries);

        final ArrayList<String> list = new ArrayList<>();

        final Context context = this;

        btnShowBeers = (Button) findViewById(R.id.btnShowBeers);
        final Button btnShowAddress = (Button) findViewById(R.id.btnShowAddress);
        final EditText etSearchBreweries = (EditText) findViewById(R.id.etSearchBreweries);
        final TextView txtTestBreweries = (TextView) findViewById(R.id.txtTestBreweries);

        btnShowBeers.setEnabled(true);

        btnShowBeers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userInput = etSearchBreweries.getText().toString();

                String query = "SELECT+*+FROM+beer+WHERE+brewery_name+LIKE+%27%25" + userInput + "%25%27";


                if (btnShowBeers.isEnabled() == true) {
                    btnShowBeers.setEnabled(false);
                }

                Connection conn = new Connection();
                JSONArray arr = conn.connect(query);

                if (arr == null) {
                    list.add("NULL");
                }
                else {
                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject sys = null;
                        try {
                            sys = arr.getJSONObject(i);
                            String temp = sys.getString("_id");
                            list.add(temp);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }


                Intent i = new Intent(context, beerList.class);
                if (list.isEmpty()) {
                    list.add("No Beer Found for Brewery");
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

        btnShowAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            String userInput = etSearchBreweries.getText().toString();

            String query = "SELECT+*+FROM+breweries+WHERE+_id+LIKE+%27%25" + userInput + "%25%27";

            Connection conn = new Connection();
            JSONArray arr = conn.connect(query);

            try {
                JSONObject sys = arr.getJSONObject(0);
                String temp = sys.getString("address");
                txtTestBreweries.setText(temp);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            }
        });
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
