package com.kooknluke.abrewforyou.Constants;

/**
 * Created by Dan Poss on 9/25/2017.
 */

public class QueryConstants {

    public static final String BEERS_FROM_BREWERY_QUERY = "SELECT b.BEER_NAME FROM BREWERIES brew JOIN BEER b ON b.BREWERY_OBJ_ID = brew._id WHERE brew.BREWERY_NAME = ?";
    public static final String BEERS_FROM_BREWERY_LIKE_NAME_QUERY = "SELECT b.BEER_NAME FROM beer b JOIN breweries brew ON b.BREWERY_OBJ_ID = brew._id WHERE brew.BREWERY_NAME LIKE ?";
    public static final String GET_BEER_FROM_SEASON = "SELECT b.BEER_NAME FROM seasons s JOIN beer b ON b.SEASON_OBJ_ID = s._id WHERE s.SEASON_NAME = ?";
    public static final String BREWERIES_BY_TOWN = "SELECT * FROM beer b";
}
