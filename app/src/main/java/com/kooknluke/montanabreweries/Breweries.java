package com.kooknluke.montanabreweries;

import android.content.Context;
import android.content.DialogInterface;
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

import java.util.ArrayList;
import java.util.Arrays;


public class Breweries extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breweries);

        final ArrayList<String> list = new ArrayList<>();

        final Context context = this;

        final Button btnShowBeers = (Button) findViewById(R.id.btnShowBeers);
        final Button btnShowAddress = (Button) findViewById(R.id.btnShowAddress);
        final EditText etSearchBreweries = (EditText) findViewById(R.id.etSearchBreweries);
        final TextView txtTestBreweries = (TextView) findViewById(R.id.txtTestBreweries);

        btnShowBeers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userInput = etSearchBreweries.getText().toString();
                final TestAdapter mDbHelper = new TestAdapter(context);
                mDbHelper.createDatabase();
                mDbHelper.open();

                final Cursor testdata = mDbHelper.getBreweriesBeerData("Beer", userInput);

                if (testdata.moveToFirst()) {
                    while (testdata.isAfterLast() == false) {
                        String name = testdata.getString(testdata
                                .getColumnIndex("_id"));

                        list.add(name);
                        testdata.moveToNext();
                    }
                    txtTestBreweries.setText(Arrays.toString(list.toArray()));
                }

//                testDisplay.setText(str + " -> ");
//                testDisplay.append(str2);

                list.clear();

                mDbHelper.close();
                
            }
        });

        btnShowAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            String userInput = etSearchBreweries.getText().toString();
            final TestAdapter mDbHelper = new TestAdapter(context);
            mDbHelper.createDatabase();
            mDbHelper.open();

            final Cursor testdata = mDbHelper.getBreweriesAddressData("Breweries", userInput);

            if (testdata.moveToFirst()) {
                while (testdata.isAfterLast() == false) {
                    String name = testdata.getString(testdata
                            .getColumnIndex("address"));

                    list.add(name);
                    testdata.moveToNext();
                }
                txtTestBreweries.setText(Arrays.toString(list.toArray()));
            }

            list.clear();

            mDbHelper.close();

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
