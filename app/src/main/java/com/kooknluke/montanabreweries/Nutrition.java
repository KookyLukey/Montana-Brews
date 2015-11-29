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
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;


public class Nutrition extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition);

        final ArrayList<String> list = new ArrayList<>();

        final Context context = this;

        final Button btnNutrition = (Button) findViewById(R.id.btnNutritionSearch);
        final EditText etSearchNutrition = (EditText) findViewById(R.id.etNutritionSearch);
//        final TextView txtTestNutrition = (TextView) findViewById(R.id.txtSearchNutrition);

        btnNutrition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userInput = etSearchNutrition.getText().toString();
                final TestAdapter mDbHelper = new TestAdapter(context);
                mDbHelper.createDatabase();
                mDbHelper.open();

                final Cursor testdata = mDbHelper.getNutritionData("Nutrition", userInput);

                if (testdata.moveToFirst()) {
                    while (testdata.isAfterLast() == false) {
                        String name = testdata.getString(testdata
                                .getColumnIndex("_id"));
                        String ss = testdata.getString(testdata
                                .getColumnIndex("serving_size"));
                        String cal = testdata.getString(testdata
                                .getColumnIndex("calories"));
                        String fat = testdata.getString(testdata
                                .getColumnIndex("fat"));
                        list.add(name);
                        list.add("Serving Size:" + ss);
                        list.add("Calories: " + cal);
                        list.add("Fat: " + fat);
                        testdata.moveToNext();
                    }
//                    txtTestNutrition.setText(Arrays.toString(list.toArray()));
                }

                mDbHelper.close();

                Intent i = new Intent(context, nutritionBeerList.class);
                i.putStringArrayListExtra("beer", list);
                startActivity(i);
                list.clear();
            }
        });
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
