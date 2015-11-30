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

import java.util.ArrayList;


public class beerHistory extends ActionBarActivity {

    String beerType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer_history);

        final ArrayList<String> list = new ArrayList<>();

        final Context context = this;

        //mDbHelper.close();

        final Button btnBeerSearch = (Button) findViewById(R.id.btnBeerHistorySearch);

        btnBeerSearch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                final TestAdapter mDbHelper = new TestAdapter(context);
                mDbHelper.createDatabase();
                mDbHelper.open();

                final Cursor testdata = mDbHelper.getBeerHistoryData("beer_history", beerType);

                if (testdata.moveToFirst()) {
                    while (testdata.isAfterLast() == false) {
                        String name = testdata.getString(testdata
                                .getColumnIndex("_id"));
                        String time = testdata.getString(testdata
                                .getColumnIndex("time"));
                        String poo = testdata.getString(testdata
                                .getColumnIndex("place_of_origin"));

                        list.add(name);
                        list.add("Relative Time of Appearance: " + time);
                        list.add("Place of Origin: " + poo);
                        testdata.moveToNext();
                    }
                }
                mDbHelper.close();

                Intent i = new Intent(context, beerHistoryList.class);
                if (list.isEmpty()) {
                    list.add("No History Data Found");
                    i.putStringArrayListExtra("beer", list);
                    startActivity(i);
                    list.clear();
                } else {
                    i.putStringArrayListExtra("beer", list);
                    startActivity(i);
                    list.clear();
                }
            }
        });
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.rbBHBarleyWine:
                if (checked) {
                    beerType = "Barley Wine";
                }
                break;
            case R.id.rbBHDoubleIPA:
                if (checked) {
                    beerType = "Double IPA";
                }
                break;
            case R.id.rbBHHelles:
                if (checked) {
                    beerType = "Helles";
                }
                break;
            case R.id.rbBHIPA:
                if (checked) {
                    beerType = "IPA";
                }
                break;
            case R.id.rbBHLager:
                if (checked) {
                    beerType = "Lager";
                }
                break;
            case R.id.rbBHPaleAle:
                if (checked) {
                    beerType = "Pale Ale";
                }
                break;
            case R.id.rbBHPorter:
                if (checked) {
                    beerType = "Porter";
                }
                break;
            case R.id.rbBHSaison:
                if (checked) {
                    beerType = "Saison";
                }
                break;
            case R.id.rbBHStout:
                if (checked) {
                    beerType = "Stout";
                }
                break;
            case R.id.rbBHWeizen:
                if (checked) {
                    beerType = "Weizen";
                }
                break;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_beer_history, menu);
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
