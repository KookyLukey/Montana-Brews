package com.kooknluke.montanabreweries;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;


public class beerInfo extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer_info);

        final TextView name = (TextView) findViewById(R.id.tvBeerDescriptionName);
        final ImageView imageView = (ImageView) findViewById(R.id.ivBeerImage);
        final TextView description = (TextView) findViewById(R.id.tvBeerDescription);

        final String beerName = getIntent().getStringExtra("beerName");

        Connection conn = new Connection();

        JSONArray arr = conn.connect(beerName);

        try {
            if (arr == null) {
                name.setText("Not Found");
                imageView.setImageResource(R.drawable.imageunavailable);
                description.setText("Not Found");
            }
            else if (arr.length() == 2) {
                    name.setText(arr.getString(0));
                    imageView.setImageResource(R.drawable.imageunavailable);
                    description.setText("Not Found");
            }
            else {

                name.setText(arr.getString(0));

                byte[] decodedString = Base64.decode(arr.getString(1), Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                imageView.setImageBitmap(decodedByte);

                description.setText(arr.getString(2));

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_beer_info, menu);
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
