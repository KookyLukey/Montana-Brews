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


public class Town extends ActionBarActivity {

    Integer zipcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_town);

        final ArrayList<String> list = new ArrayList<>();

        final Context context = this;

        final Button btnSearchTwn = (Button) findViewById(R.id.btnSearchTwn);
        final TextView textView = (TextView) findViewById(R.id.townTextView);

        btnSearchTwn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                final TestAdapter mDbHelper = new TestAdapter(context);
                mDbHelper.createDatabase();
                mDbHelper.open();

                final Cursor testdata = mDbHelper.getTownData("Breweries", zipcode);

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

                Intent i = new Intent(context, townBeerList.class);
                i.putStringArrayListExtra("beer", list);
                startActivity(i);
                list.clear();
            }
        });
    }

    public void onRadioButtonClicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.rbBozeman:
                if (checked) {
                    // AC is checked
                    TextView tv = (TextView) findViewById(R.id.txtSearchByTown);
                    zipcode = 59715;
                    tv.setText("You have chosen Bozeman");
                }
                break;
            case R.id.rbBillings:
                if (checked) {
                    // DF is checked
                    TextView tv = (TextView) findViewById(R.id.txtSearchByTown);
                    zipcode = 59101;
                    tv.setText("You have chosen Billings");
                }
                break;
            case R.id.rbHelena:
                if (checked) {
                    // GJ is checked
                    TextView tv = (TextView) findViewById(R.id.txtSearchByTown);
                    zipcode = 59601;
                    tv.setText("You have chosen Helena");
                }
                break;
            case R.id.rbKalispell:
                if (checked) {
                    // KO is checked
                    TextView tv = (TextView) findViewById(R.id.txtSearchByTown);
                    zipcode = 59903;
                    tv.setText("You have chosen Kalispell");
                }
                break;
            case R.id.rbMissoula:
                if (checked) {
                    // KO is checked
                    TextView tv = (TextView) findViewById(R.id.txtSearchByTown);
                    zipcode = 59801;
                    tv.setText("You have chosen Missoula");
                }
                break;
            case R.id.rbWibaux:
                if (checked) {
                    // KO is checked
                    TextView tv = (TextView) findViewById(R.id.txtSearchByTown);
                    zipcode = 59353;
                    tv.setText("You have chosen Wibaux");
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
