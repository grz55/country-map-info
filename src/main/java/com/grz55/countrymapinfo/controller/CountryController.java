package com.grz55.countrymapinfo.controller;

import com.grz55.countrymapinfo.exception.CountryNotFoundException;
import com.grz55.countrymapinfo.model.Country;
import com.grz55.countrymapinfo.model.CountryMapInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Controller
public class CountryController {

    @GetMapping("/")
    public String showCountry(@RequestParam(name = "country", defaultValue = "poland") String countryName, Model model) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://restcountries.eu/rest/v2/name/" + countryName;
        try{
            Country[] forObject = restTemplate.getForObject(url, Country[].class);
            Country country = forObject[0];
            double latitude = country.getLatlng().get(0);
            double longitude = country.getLatlng().get(1);
            CountryMapInfo countryMapInfo = new CountryMapInfo(country.getName(), country.getCapital(), latitude, longitude);
            model.addAttribute("countryMapInfo", countryMapInfo);
        } catch (RestClientException e){
            throw new CountryNotFoundException(countryName);
        }
        return "map";
    }
}
