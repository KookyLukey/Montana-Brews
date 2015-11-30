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

import java.util.ArrayList;
import java.util.Arrays;


public class Seasons extends ActionBarActivity {

    String season;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seasons);

        final ArrayList<String> list = new ArrayList<>();

        final Context context = this;

        final Button btnSearch = (Button) findViewById(R.id.btnSeasonSearch);

        btnSearch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                final TestAdapter mDbHelper = new TestAdapter(context);
                mDbHelper.createDatabase();
                mDbHelper.open();

                final Cursor testdata = mDbHelper.getSeasonsData("seasons", season);

                if (testdata.moveToFirst()) {
                    while (testdata.isAfterLast() == false) {
                        String name = testdata.getString(testdata
                                .getColumnIndex("_id"));

                        list.add(name);
                        testdata.moveToNext();
                    }
                }

                mDbHelper.close();

                Intent i = new Intent(context, seasonsBeerList.class);
                if (list.isEmpty()) {
                    list.add("No Beer Found");
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
                }
                break;
            case R.id.rbSummer:
                if (checked) {
                    season = "Summer";
                }
                break;
            case R.id.rbAutumn:
                if (checked) {
                    season = "Fall";
                }
                break;
            case R.id.rbWinter:
                if (checked) {
                    season = "Winter";
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
