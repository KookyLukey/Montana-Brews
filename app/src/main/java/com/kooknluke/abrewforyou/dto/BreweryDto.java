package com.kooknluke.abrewforyou.dto;

/**
 * Created by Dan Poss on 9/23/2017.
 */

// This class is for transitioning the json object into a Brewery Object
public class BreweryDto {

    // The object Id from the DB for the Brewery
    Long breweryObjId;

    // The name of the Brewery
    String name;

    // The physical address of the Brewery
    String breweryAddress;

    // The Zipcode of the given Brewery
    String breweryZipcode;

    public BreweryDto() {

    }

    public Long getBreweryObjId() {
        return breweryObjId;
    }

    public void setBreweryObjId(Long breweryObjId) {
        this.breweryObjId = breweryObjId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreweryAddress() {
        return breweryAddress;
    }

    public void setBreweryAddress(String breweryAddress) {
        this.breweryAddress = breweryAddress;
    }

    public String getBreweryZipcode() {
        return breweryZipcode;
    }

    public void setBreweryZipcode(String breweryZipcode) {
        this.breweryZipcode = breweryZipcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BreweryDto)) return false;

        BreweryDto that = (BreweryDto) o;

        if (getBreweryObjId() != null ? !getBreweryObjId().equals(that.getBreweryObjId()) : that.getBreweryObjId() != null)
            return false;
        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null)
            return false;
        if (getBreweryAddress() != null ? !getBreweryAddress().equals(that.getBreweryAddress()) : that.getBreweryAddress() != null)
            return false;
        return getBreweryZipcode() != null ? getBreweryZipcode().equals(that.getBreweryZipcode()) : that.getBreweryZipcode() == null;
    }

    @Override
    public int hashCode() {
        int result = getBreweryObjId() != null ? getBreweryObjId().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getBreweryAddress() != null ? getBreweryAddress().hashCode() : 0);
        result = 31 * result + (getBreweryZipcode() != null ? getBreweryZipcode().hashCode() : 0);
        return result;
    }
}
