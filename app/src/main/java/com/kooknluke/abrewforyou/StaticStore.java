package com.kooknluke.abrewforyou;

import android.app.Application;

import java.util.ArrayList;

/**
 * Created by Dan Poss on 4/17/2016.
 */
public class StaticStore extends Application{
    private ArrayList<String> list;

    public ArrayList<String> getArray() {
        return list;
    }

    public void setArrayList(ArrayList<String> list) {
        this.list = list;
    }

    @Override
    public void onCreate() {

    }
}
