package com.kooknluke.montanabreweries;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
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

import java.util.ArrayList;
import java.util.Arrays;


public class Seasons extends ActionBarActivity {

    private String season;
    private Button btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seasons);

        final ArrayList<String> list = new ArrayList<>();

        final Context context = this;

        btnSearch = (Button) findViewById(R.id.btnSeasonSearch);

        btnSearch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String query = "SELECT+*+FROM+seasons+WHERE+season+%3D+%27"+season+"%27";

                btnSearch.setEnabled(false);

                try {
                    Connection conn = new Connection();
                    JSONArray arr = conn.connect(query);
                    if (arr == null) {
                        list.add("No Beer Found for " + season);
                    } else {
                        for (int i = 0; i < arr.length(); i++) {
                            JSONObject sys = arr.getJSONObject(i);
                            String temp = sys.getString("_id");
                            list.add(temp);
                        }
                    }
                } catch (JSONException e){
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }

                Intent i = new Intent(context, beerList.class);
                if (list.isEmpty()) {
                    list.add("No Beer Found for " + season);
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

    public void onRadioButtonClicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.rbSpring:
                if (checked) {
                    season = "Spring";
                    if (!btnSearch.isEnabled()) {
                        btnSearch.setEnabled(true);
                    }
                }
                break;
            case R.id.rbSummer:
                if (checked) {
                    season = "Summer";
                    if (!btnSearch.isEnabled()) {
                        btnSearch.setEnabled(true);
                    }
                }
                break;
            case R.id.rbAutumn:
                if (checked) {
                    season = "Fall";
                    if (!btnSearch.isEnabled()) {
                        btnSearch.setEnabled(true);
                    }
                }
                break;
            case R.id.rbWinter:
                if (checked) {
                    season = "Winter";
                    if (!btnSearch.isEnabled()) {
                        btnSearch.setEnabled(true);
                    }
                }
                break;
        }
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
}
