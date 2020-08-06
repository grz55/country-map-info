package com.grz55.countrymapinfo.exception;

public class CountryNotFoundException extends RuntimeException {
    public CountryNotFoundException(String countryName) {
        super("Country name problem (Country=" + countryName + "). Country may not exist in the API under provided name");
    }
}
