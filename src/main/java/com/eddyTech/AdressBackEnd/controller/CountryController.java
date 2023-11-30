package com.eddyTech.AdressBackEnd.controller;

import com.eddyTech.AdressBackEnd.dto.country.CreateCountryDto;
import com.eddyTech.AdressBackEnd.dto.country.EditCountryDto;
import com.eddyTech.AdressBackEnd.service.CountryService;
import com.eddyTech.AdressBackEnd.utils.ResponseHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/address/country")
@RequiredArgsConstructor
public class CountryController {

    private final CountryService countryService;

    @GetMapping()
    public ResponseEntity<Object> fetchCountries(){
        var response = countryService.fetchCountries();
        if (response.getIsSuccess()){
            return ResponseHandler.generateResponse(response.getMessage(), true, HttpStatus.OK, response.getData());
        }else {
            return ResponseHandler.generateResponse(response.getMessage(), false, HttpStatus.NOT_IMPLEMENTED, response.getData());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createCountry(@RequestBody @Valid CreateCountryDto request){
        var response = countryService.createCountry(request);
        if (response.getIsSuccess()){
            return ResponseHandler.generateResponse(response.getMessage(), true, HttpStatus.OK, response.getData());
        }else {
            return ResponseHandler.generateResponse(response.getMessage(), false, HttpStatus.NOT_IMPLEMENTED, response.getData());
        }
    }

    @PostMapping("/edit")
    public ResponseEntity<Object> editCountry(@RequestBody @Valid EditCountryDto request){
        var response = countryService.editCountry(request);

        if (response.getIsSuccess()){
            return ResponseHandler.generateResponse(response.getMessage(), true, HttpStatus.OK, response.getData());
        }else {
            return ResponseHandler.generateResponse(response.getMessage(), false, HttpStatus.NOT_IMPLEMENTED, response.getData());
        }
    }

}
