package com.kooknluke.montanabreweries;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;


public class Nutrition extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition);
    }

    public void onRadioButtonClicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.rb100:
                if (checked) {
                    // AC is checked
                    TextView tv = (TextView) findViewById(R.id.txtSearchByNutrition);
                    tv.setText("You have chosen < 100");
                }
                break;
            case R.id.rb150:
                if (checked) {
                    // DF is checked
                    TextView tv = (TextView) findViewById(R.id.txtSearchByNutrition);
                    tv.setText("You have chosen < 150");
                }
                break;
            case R.id.rb200:
                if (checked) {
                    // GJ is checked
                    TextView tv = (TextView) findViewById(R.id.txtSearchByNutrition);
                    tv.setText("You have chosen < 200");
                }
                break;
            case R.id.rb250:
                if (checked) {
                    // KO is checked
                    TextView tv = (TextView) findViewById(R.id.txtSearchByNutrition);
                    tv.setText("You have chosen < 250");
                }
                break;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_nutrition, menu);
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
