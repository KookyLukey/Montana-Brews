package com.kooknluke.montanabreweries;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;


public class Breweries extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breweries);

        final Context context = this;

        final Button btnShowBeers = (Button) findViewById(R.id.btnShowBeers);
        final Button btnShowAddress = (Button) findViewById(R.id.btnShowAddress);
        final EditText etSearchBreweries = (EditText) findViewById(R.id.etSearchBreweries);

        btnShowBeers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userInput = etSearchBreweries.getText().toString();
                
            }
        });

        btnShowAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userInput = etSearchBreweries.getText().toString();

            }
        });
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
