package com.kooknluke.montanabreweries;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;


public class Beer extends ActionBarActivity {

    String str;
    String str2;
    String type;
    String abv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer);

        final ArrayList<String> list = new ArrayList<>();

        final Context context = this;

        final Button btnBeerSearch = (Button) findViewById(R.id.btnBeerSearch);

        btnBeerSearch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String query = "SELECT+*+FROM+beer+WHERE+type_of_beer+LIKE+%27%25" + type + "%25%27+AND+ABV+%3C%3D"+ abv;

                try {
                    btnBeerSearch.setEnabled(false);
                    Connection conn = new Connection();
                    JSONArray arr = conn.connect(query);
                    if (arr == null) {
                        list.add("No Beer Found");
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
                finally {
                    Intent i = new Intent(context, beerList.class);
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
            }
        });
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.rbAle:
                if (checked) {
                    type = "Ale";
                }
                break;
            case R.id.rbStout:
                if (checked) {
                    type = "Stout";
                }
                break;
            case R.id.rbLager:
                if (checked) {
                    type = "Lager";
                }
                break;
            case R.id.rbWeizen:
                if (checked) {
                    type = "Weizen";
                }
                break;
            case R.id.rbIPA:
                if (checked) {
                    type = "IPA";
                }
                break;
            case R.id.rbOktoberfest:
                if (checked) {
                    type = "Oktoberest";
                }
                break;
            case R.id.rbPorter:
                if (checked) {
                    type = "Porter";
                }
                break;
            case R.id.rbHelles:
                if (checked) {
                    type = "Helles";
                }
                break;
            case R.id.rb5:
                if (checked) {
                    abv = "5.0";
                }
                break;
            case R.id.rb6:
                if (checked) {
                    abv = "6.0";
                }
                break;
            case R.id.rb7:
                if (checked) {
                    abv = "7.0";
                }
                break;
            case R.id.rb8:
                if (checked) {
                    abv = "8.0";
                }
                break;
            case R.id.rb9:
                if (checked) {
                    abv = "9.0";
                }
                break;
            case R.id.rb10:
                if (checked) {
                    abv = "9.9";
                }
                break;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_beer, menu);
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
