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


public class State extends ActionBarActivity {

    String state;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state);

        final ArrayList<String> list = new ArrayList<>();

        final Context context = this;

        final Button btnSearch = (Button) findViewById(R.id.btnSearchState);

        btnSearch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String query = "SELECT+breweries._id+FROM+breweries+JOIN+towns+ON+breweries.name_of_town+%3D+towns._id+WHERE+towns.state+%3D+%27"+state+"%27";

                try {
                    Connection conn = new Connection();
                    JSONArray arr = conn.connect(query);
                    if (arr == null) {
                        list.add("NULL");
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
            }
        });
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.rbMontana:
                if (checked) {
                    // AC is checked
                    state = "Montana";
                }
                break;
        }
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
}
