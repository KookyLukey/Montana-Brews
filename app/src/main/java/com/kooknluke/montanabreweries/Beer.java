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


public class Beer extends ActionBarActivity {

    String str;
    RadioButton rbAle = (RadioButton) findViewById(R.id.rbAle);
    RadioButton rbLager = (RadioButton) findViewById(R.id.rbLager);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer);

        final Context context = this;

        TestAdapter mDbHelper = new TestAdapter(context);
        mDbHelper.createDatabase();
        mDbHelper.open();

        final Cursor testdata = mDbHelper.getTestData();

        if (testdata.moveToFirst()) {
            str = testdata.getString(testdata.getColumnIndex("content"));
        }

        mDbHelper.close();

        final Button btnBeerSearch = (Button) findViewById(R.id.btnBeerSearch);
        final TextView testDisplay = (TextView) findViewById(R.id.txtBeerTestDisplay);

        btnBeerSearch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                testDisplay.setText(str);

            }
        });
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
