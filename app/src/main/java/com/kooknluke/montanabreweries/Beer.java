package com.kooknluke.montanabreweries;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

public class Beer extends ActionBarActivity {

    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer);

        final ArrayList<String> list = new ArrayList<>();

        final Context context = this;
        progress = new ProgressDialog(this);
        final ListView lv = (ListView) findViewById(R.id.typeBeerlv);

        String[] types = getResources().getStringArray(R.array.type_of_beer);



        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
            this,
            R.layout.beerlistview,
            R.id.firstLine,
            types
        ) {
                @Override
                public View getView(int position, View convertView,
                                    ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);
                    TextView textView = (TextView) view.findViewById(R.id.firstLine);

                    textView.setTextColor(Color.WHITE);

                    return view;
                }
        };

        lv.setAdapter(arrayAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    progress.setTitle("Loading");
                    progress.setMessage("Fetching your beer");
                    progress.show();

                    TextView tv = (TextView) view.findViewById(R.id.firstLine);
                    String type = URLEncoder.encode(tv.getText().toString(), "UTF-8");
                    String query = "SELECT+*+FROM+beer+WHERE+type_of_beer+LIKE+%27%25" + type + "%25%27";

                    Connection conn = new Connection();
                    JSONArray arr = conn.connect(query);
                    if (arr == null) {
                        list.add("No Beer Found");
                    } else {
                        for (int i = 0; i < arr.length(); i++) {
                            JSONObject sys = arr.getJSONObject(i);
                            String temp = sys.getString("_id");
                            list.add(temp);
                        }
                    }

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {
                    Intent i = new Intent(context, beerList.class);
                    if (list.isEmpty()) {
                        list.add("No Beer Found");
                        i.putStringArrayListExtra("beer", list);
                        startActivity(i);
                        list.clear();
                    } else {
                        i.putStringArrayListExtra("beer", list);
                        startActivity(i);
                        list.clear();
                    }
                }
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

    @Override
    protected void onResume() {
        super.onResume();
        progress.dismiss();
    }
}
