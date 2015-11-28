package com.kooknluke.montanabreweries;

import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;


public class Breweries extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breweries);
    }

    public void onRadioButtonClicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.rbAC:
                if (checked) {
                    // AC is checked
                    TextView tv = (TextView) findViewById(R.id.txtSearchByBreweries);
                    tv.setText("You have chosen A-C");
                }
                break;
            case R.id.rbDF:
                if (checked) {
                    // DF is checked
                    TextView tv = (TextView) findViewById(R.id.txtSearchByBreweries);
                    tv.setText("You have chosen D-F");
                }
                break;
            case R.id.rbGJ:
                if (checked) {
                    // GJ is checked
                    TextView tv = (TextView) findViewById(R.id.txtSearchByBreweries);
                    tv.setText("You have chosen G-J");
                }
                break;
            case R.id.rbKO:
                if (checked) {
                    // KO is checked
                    TextView tv = (TextView) findViewById(R.id.txtSearchByBreweries);
                    tv.setText("You have chosen K-O");
                }
                break;
            case R.id.rbPR:
                if (checked) {
                    // PR is checked
                    TextView tv = (TextView) findViewById(R.id.txtSearchByBreweries);
                    tv.setText("You have chosen P-R");
                }
                break;
            case R.id.rbSV:
                if (checked) {
                    // SV is checked
                    TextView tv = (TextView) findViewById(R.id.txtSearchByBreweries);
                    tv.setText("You have chosen S-V");
                }
                break;
            case R.id.rbWZ:
                if (checked) {
                    // WZ is checked
                    TextView tv = (TextView) findViewById(R.id.txtSearchByBreweries);
                    tv.setText("You have chosen W-Z");
                }
                break;
        }
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
