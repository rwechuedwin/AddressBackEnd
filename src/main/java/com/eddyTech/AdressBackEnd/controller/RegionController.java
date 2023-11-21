package com.eddyTech.AdressBackEnd.controller;

import com.eddyTech.AdressBackEnd.dto.country.EditCountryDto;
import com.eddyTech.AdressBackEnd.dto.region.CreateRegionDto;
import com.eddyTech.AdressBackEnd.dto.region.EditRegionDto;
import com.eddyTech.AdressBackEnd.service.RegionService;
import com.eddyTech.AdressBackEnd.utils.ResponseHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/address/region")
@RequiredArgsConstructor
public class RegionController {
    private final RegionService regionService;

    @GetMapping(value = "{countryId}")
    public ResponseEntity<Object> fetchRegion(@PathVariable UUID countryId){
        var response = regionService.fetchRegions(countryId);

        if (response.getIsSuccess()){
            return ResponseHandler.generateResponse(response.getMessage(), true, HttpStatus.OK, response.getData());
        }else{
            return ResponseHandler.generateResponse(response.getMessage(), true, HttpStatus.NOT_IMPLEMENTED,response.getData());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createRegion(@RequestBody @Valid CreateRegionDto request){
        var response = regionService.createRegion(request);
        if (response.getIsSuccess()){
            return ResponseHandler.generateResponse(response.getMessage(),true, HttpStatus.OK, response.getData());
        }else {
            return ResponseHandler.generateResponse(response.getMessage(), false, HttpStatus.NOT_IMPLEMENTED, response.getData());
        }
    }

    @PostMapping("/edit")
    public ResponseEntity<Object> editRegion(@RequestBody @Valid EditRegionDto request){
        var response = regionService.editRegion(request);
        if (response.getIsSuccess()){
            return ResponseHandler.generateResponse(response.getMessage(), true, HttpStatus.OK, response.getData());
        }else {
            return ResponseHandler.generateResponse(response.getMessage(), false, HttpStatus.NOT_IMPLEMENTED, response.getData());
        }
    }
}
