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

        //mDbHelper.close();

        final Button btnBeerSearch = (Button) findViewById(R.id.btnBeerSearch);
        final TextView testDisplay = (TextView) findViewById(R.id.txtBeerTestDisplay);

        btnBeerSearch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                final TestAdapter mDbHelper = new TestAdapter(context);
                mDbHelper.createDatabase();
                mDbHelper.open();

                final Cursor testdata = mDbHelper.getBeerData();

                if (testdata.moveToFirst()) {
                    str = testdata.getString(testdata.getColumnIndex("_id"));
                    str2 = testdata.getString(testdata.getColumnIndex("type_of_beer"));
                }

                testDisplay.setText(str + " -> ");
                testDisplay.append(str2);

                while (testdata.isAfterLast() == false) {
                    String name = testdata.getString(testdata
                            .getColumnIndex("_id"));

                    list.add(name);
                    testdata.moveToNext();
                }
                }
                testDisplay.setText(Arrays.toString(list.toArray()));

                mDbHelper.close();
            }
        });
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.rbAle:
                if (checked) {
                    // AC is checked
                    TextView tv = (TextView) findViewById(R.id.txtBeerTestDisplay);
                    type = "Ale";
                    tv.setText("You have chosen Ale");
                }
                break;
            case R.id.rbStout:
                if (checked) {
                    // DF is checked
                    TextView tv = (TextView) findViewById(R.id.txtBeerTestDisplay);
                    type = "Stout";
                    tv.setText("You have chosen Stout");
                }
                break;
            case R.id.rbLager:
                if (checked) {
                    // GJ is checked
                    TextView tv = (TextView) findViewById(R.id.txtBeerTestDisplay);
                    type = "Lager";
                    tv.setText("You have chosen Lager");
                }
                break;
            case R.id.rbWeizen:
                if (checked) {
                    // KO is checked
                    TextView tv = (TextView) findViewById(R.id.txtBeerTestDisplay);
                    type = "Weizen";
                    tv.setText("You have chosen Weizen");
                }
                break;
            case R.id.rbIPA:
                if (checked) {
                    // PR is checked
                    TextView tv = (TextView) findViewById(R.id.txtBeerTestDisplay);
                    type = "IPA";
                    tv.setText("You have chosen IPA");
                }
                break;
            case R.id.rbOktoberfest:
                if (checked) {
                    // SV is checked
                    TextView tv = (TextView) findViewById(R.id.txtBeerTestDisplay);
                    type = "Oktoberest";
                    tv.setText("You have chosen Oktoberfest");
                }
                break;
            case R.id.rbPorter:
                if (checked) {
                    // WZ is checked
                    TextView tv = (TextView) findViewById(R.id.txtBeerTestDisplay);
                    type = "Porter";
                    tv.setText("You have chosen Porter");
                }
                break;
            case R.id.rbHelles:
                if (checked) {
                    // WZ is checked
                    TextView tv = (TextView) findViewById(R.id.txtBeerTestDisplay);
                    type = "Helles";
                    tv.setText("You have chosen Helles");
                }
                break;
            case R.id.rb5:
                if (checked) {
                    // WZ is checked
                    TextView tv = (TextView) findViewById(R.id.txtBeerTestDisplay);
                    abv = "5";
                    tv.setText("You have chosen 5%");
                }
                break;
            case R.id.rb6:
                if (checked) {
                    // WZ is checked
                    TextView tv = (TextView) findViewById(R.id.txtBeerTestDisplay);
                    abv = "6";
                    tv.setText("You have chosen 6%");
                }
                break;
            case R.id.rb7:
                if (checked) {
                    // WZ is checked
                    TextView tv = (TextView) findViewById(R.id.txtBeerTestDisplay);
                    abv = "7";
                    tv.setText("You have chosen 7%");
                }
                break;
            case R.id.rb8:
                if (checked) {
                    // WZ is checked
                    TextView tv = (TextView) findViewById(R.id.txtBeerTestDisplay);
                    abv = "8";
                    tv.setText("You have chosen 8%");
                }
                break;
            case R.id.rb9:
                if (checked) {
                    // WZ is checked
                    TextView tv = (TextView) findViewById(R.id.txtBeerTestDisplay);
                    abv = "9";
                    tv.setText("You have chosen 9%");
                }
                break;
            case R.id.rb10:
                if (checked) {
                    // WZ is checked
                    TextView tv = (TextView) findViewById(R.id.txtBeerTestDisplay);
                    abv = "10";
                    tv.setText("You have chosen 10%");
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
