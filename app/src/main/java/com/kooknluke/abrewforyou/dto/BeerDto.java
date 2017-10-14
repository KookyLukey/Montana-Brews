package com.kooknluke.abrewforyou.dto;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

/**
 * Created by Dan Poss on 9/23/2017.
 */

// This class is for transitioning the json object into a Beer Object
public class BeerDto extends Model {

    // Beer's Object Id from DB
//    @Column(name="beer_obj_id")
    Long beerObjId;

    // Beer's Name
//    @Column(name="BEER_NAME")
    String name;

    // Beer's Type
//    @Column(name="TYPE_OF_BEER")
    String type;

    // Beer's ABV
//    @Column(name="ABV")
    Double alcoholByVolume;

    // Beer's Brewery Object Id from DB
//    @Column(name="BREWERY_OBJ_ID")
    Long breweryObjId;

    // Beer's Season Object Id
//    @Column(name="SEASON_OBJ_ID")
    Long seasonObjId;

    public BeerDto() {

    }

    public Long getBeerObjId() {
        return beerObjId;
    }

    public void setBeerObjId(Long beerObjId) {
        this.beerObjId = beerObjId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getAlcoholByVolume() {
        return alcoholByVolume;
    }

    public void setAlcoholByVolume(Double alcoholByVolume) {
        this.alcoholByVolume = alcoholByVolume;
    }

    public Long getBreweryObjId() {
        return breweryObjId;
    }

    public void setBreweryObjId(Long breweryObjId) {
        this.breweryObjId = breweryObjId;
    }

    public Long getSeasonObjId() {
        return seasonObjId;
    }

    public void setSeasonObjId(Long seasonObjId) {
        this.seasonObjId = seasonObjId;
    }

    @Override
    public String toString() {
        return "BeerDto{" +
                "beerObjId=" + beerObjId +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", alcoholByVolume=" + alcoholByVolume +
                ", breweryObjId=" + breweryObjId +
                ", seasonObjId=" + seasonObjId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BeerDto)) return false;

        BeerDto beerDto = (BeerDto) o;

        if (!getBeerObjId().equals(beerDto.getBeerObjId())) return false;
        if (!getName().equals(beerDto.getName())) return false;
        if (getType() != null ? !getType().equals(beerDto.getType()) : beerDto.getType() != null)
            return false;
        if (getAlcoholByVolume() != null ? !getAlcoholByVolume().equals(beerDto.getAlcoholByVolume()) : beerDto.getAlcoholByVolume() != null)
            return false;
        if (getBreweryObjId() != null ? !getBreweryObjId().equals(beerDto.getBreweryObjId()) : beerDto.getBreweryObjId() != null)
            return false;
        return getSeasonObjId() != null ? getSeasonObjId().equals(beerDto.getSeasonObjId()) : beerDto.getSeasonObjId() == null;
    }

    @Override
    public int hashCode() {
        int result = getBeerObjId().hashCode();
        result = 31 * result + getName().hashCode();
        result = 31 * result + (getType() != null ? getType().hashCode() : 0);
        result = 31 * result + (getAlcoholByVolume() != null ? getAlcoholByVolume().hashCode() : 0);
        result = 31 * result + (getBreweryObjId() != null ? getBreweryObjId().hashCode() : 0);
        result = 31 * result + (getSeasonObjId() != null ? getSeasonObjId().hashCode() : 0);
        return result;
    }

//    public static List<BeerDto> getAll() {
//        return new Select()
//                .from(BeerDto.class)
//                .orderBy("BEER_NAME ASC")
//                .execute();
//    }
}
