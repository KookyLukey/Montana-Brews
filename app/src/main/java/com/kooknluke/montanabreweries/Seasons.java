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
        final TextView textView = (TextView) findViewById(R.id.txtSeasonsTest);

        btnSearch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                final TestAdapter mDbHelper = new TestAdapter(context);
                mDbHelper.createDatabase();
                mDbHelper.open();

                final Cursor testdata = mDbHelper.getSeasonsData("Seasons", season);

                if (testdata.moveToFirst()) {
                    while (testdata.isAfterLast() == false) {
                        String name = testdata.getString(testdata
                                .getColumnIndex("_id"));

                        list.add(name);
                        testdata.moveToNext();
                    }
                    textView.setText(Arrays.toString(list.toArray()));
                }

                mDbHelper.close();

                Intent i = new Intent(context, seasonsBeerList.class);
                i.putStringArrayListExtra("beer", list);
                startActivity(i);
                list.clear();
            }
        });
    }

    public void onRadioButtonClicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.rbSpring:
                if (checked) {
                    // AC is checked
                    TextView tv = (TextView) findViewById(R.id.txtSeasonsTest);
                    season = "Spring";
                    tv.setText("You have chosen Spring");
                }
                break;
            case R.id.rbSummer:
                if (checked) {
                    // DF is checked
                    TextView tv = (TextView) findViewById(R.id.txtSeasonsTest);
                    season = "Summer";
                    tv.setText("You have chosen Summer");
                }
                break;
            case R.id.rbAutumn:
                if (checked) {
                    // GJ is checked
                    TextView tv = (TextView) findViewById(R.id.txtSeasonsTest);
                    season = "Fall";
                    tv.setText("You have chosen Fall");
                }
                break;
            case R.id.rbWinter:
                if (checked) {
                    // KO is checked
                    TextView tv = (TextView) findViewById(R.id.txtSeasonsTest);
                    season = "Winter";
                    tv.setText("You have chosen Winter");
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
