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


public class Town extends ActionBarActivity {

    String town;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_town);

        final ArrayList<String> list = new ArrayList<>();

        final Context context = this;

        final Button btnSearchTwn = (Button) findViewById(R.id.btnSearchTwn);

        btnSearchTwn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String query = "SELECT+*+FROM+breweries+WHERE+name_of_town+%3D+%27" + town + "%27";

                try {
                    Connection conn = new Connection();
                    JSONArray arr = conn.connect(query);
                    if (arr == null) {
                        list.add("NULL");
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
            }
        });
    }

    public void onRadioButtonClicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.rbBelgrade:
                if (checked) {
                    town = "Belgrade";
                }
                break;
            case R.id.rbBozeman:
                if (checked) {
                    town = "Bozeman";
                }
                break;
            case R.id.rbBillings:
                if (checked) {
                    town = "Billings";
                }
                break;
            case R.id.rbHamilton:
                if (checked) {
                    town = "Hamilton";
                }
                break;
            case R.id.rbHelena:
                if (checked) {
                    town = "Helena";
                }
                break;
            case R.id.rbLivingston:
                if (checked) {
                    town = "Livingston";
                }
                break;
            case R.id.rbMissoula:
                if (checked) {
                    town = "Missoula";
                }
                break;
            case R.id.rbStevensville:
                if (checked) {
                    town = "Stevensville";
                }
                break;
            case R.id.rbWibaux:
                if (checked) {
                    town = "Wibaux";
                }
                break;
        }
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
}
