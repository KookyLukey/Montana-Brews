package com.kooknluke.montanabreweries;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class Map extends FragmentActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    GoogleApiClient mGoogleApiClient = null;
    Double latLoc;
    Double longLoc;
    final ArrayList<Double> latList = new ArrayList<>();
    final ArrayList<Double> longList = new ArrayList<>();
    final ArrayList<String> nameList = new ArrayList<>();
    final ArrayList<String> beerList = new ArrayList<>();
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        setUpMapIfNeeded();

        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
    }

    @Override
    public void onConnected(Bundle connectionHint) {
        Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null) {

            latLoc = mLastLocation.getLatitude();
            longLoc = mLastLocation.getLongitude();
            LatLng location = new LatLng(latLoc, longLoc);

            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(location, 6);
            mMap.animateCamera(cameraUpdate);
            mMap.addMarker(new MarkerOptions().position(new LatLng(latLoc, longLoc)).title("You"));
            addBreweryMarkers();
        }
    }

    protected void addBreweryMarkers(){

        String latitudeQuery = "SELECT+latitude+FROM+breweries";
        String longitudeQuery = "SELECT+longitude+FROM+breweries";
        String nameQuery = "SELECT+_id+FROM+breweries";

        Connection conn = new Connection();
        JSONArray arr = conn.connect(latitudeQuery);

        try {
            for (int i = 0; i < arr.length(); i++) {
                JSONObject sys = arr.getJSONObject(i);
                Double temp = sys.getDouble("latitude");
                latList.add(temp);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Connection conn2 = new Connection();
        JSONArray longArr = conn2.connect(longitudeQuery);

        try {
            for (int i = 0; i < longArr.length(); i++) {
                JSONObject sys = longArr.getJSONObject(i);
                Double tempN = sys.getDouble("longitude");
                longList.add(tempN);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONArray nameArr = conn2.connect(nameQuery);

        try {
            for (int i = 0; i < nameArr.length(); i++) {
                JSONObject sys = nameArr.getJSONObject(i);
                String nameTemp = sys.getString("_id");
                nameList.add(nameTemp);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for(int i = 0; i < latList.size(); i++){
            LatLng brewLatLng = new LatLng(latList.get(i), longList.get(i));

            MarkerOptions brewMarker = new MarkerOptions().position(brewLatLng).title(nameList.get(i));
            brewMarker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
            mMap.addMarker(brewMarker);
        }

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                String title = null;
                try {
                    title = URLEncoder.encode(marker.getTitle(), "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                String beerQuery = "SELECT+*++FROM++%60beer%60++WHERE+brewery_name%3D%27" + title + "%27";


                Connection conn = new Connection();
                JSONArray beerArr = conn.connect(beerQuery);

                if (beerArr == null) {
                    beerList.add("No Beer Found for Brewery");
                }
                else {
                    for (int i = 0; i < beerArr.length(); i++) {
                        JSONObject sys = null;
                        try {
                            sys = beerArr.getJSONObject(i);
                            String temp = sys.getString("_id");
                            beerList.add(temp);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }

                Intent i = new Intent(context, beerList.class);
                if (beerList.isEmpty()) {
                    beerList.add("No Beer Found for Brewery");
                    i.putStringArrayListExtra("beer", beerList);
                    startActivity(i);
                    beerList.clear();
                }
                else {
                    i.putStringArrayListExtra("beer", beerList);
                    startActivity(i);
                    beerList.clear();
                }
            }
        });
    }

    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
