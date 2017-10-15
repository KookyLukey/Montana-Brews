package com.kooknluke.abrewforyou.Constants;

/**
 * Created by Dan Poss on 9/25/2017.
 */

public class QueryConstants {

    public static final String BEERS_FROM_BREWERY_QUERY = "SELECT b.beer_name FROM BREWERIES brew JOIN BEER b ON b.BREWERY_OBJ_ID = brew._id WHERE brew.BREWERY_NAME = ?";
    public static final String BEERS_FROM_BREWERY_LIKE_NAME_QUERY = "SELECT b.beer_name FROM beer b JOIN breweries brew ON b.BREWERY_OBJ_ID = brew._id WHERE brew.BREWERY_NAME LIKE ?";

}
