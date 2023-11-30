package com.eddyTech.AdressBackEnd.controller;

import com.eddyTech.AdressBackEnd.dto.district.CreateDistrictDto;
import com.eddyTech.AdressBackEnd.dto.district.EditDistrictDto;
import com.eddyTech.AdressBackEnd.service.DistrictService;
import com.eddyTech.AdressBackEnd.utils.ResponseHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/address/district")
@RequiredArgsConstructor
public class DistrictController {
    private final DistrictService districtService;

    @GetMapping(value = "{regionId}" )
    public ResponseEntity<Object> fetchDistricts(@PathVariable UUID regionId){
        var response = districtService.fetchDistricts(regionId);

        if(response.getIsSuccess()){
            return ResponseHandler.generateResponse(response.getMessage(), true, HttpStatus.OK, response.getData());
        }else {
            return ResponseHandler.generateResponse(response.getMessage(), false, HttpStatus.NOT_IMPLEMENTED, response.getData());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createDistrict(@RequestBody @Valid CreateDistrictDto request){
        var response = districtService.createDistrict(request);
        if(response.getIsSuccess()){
            return ResponseHandler.generateResponse(response.getMessage(), true, HttpStatus.OK, response.getData());
        }else {
            return ResponseHandler.generateResponse(response.getMessage(), false, HttpStatus.NOT_IMPLEMENTED, response.getData());
        }
    }
    @PostMapping("/edit")
    public ResponseEntity<Object> editDistrict(@RequestBody @Valid EditDistrictDto request){
        var response = districtService.editDistrict(request);
        if (response.getIsSuccess()){
            return ResponseHandler.generateResponse(response.getMessage(),true,HttpStatus.OK, response.getData());
        }else {
            return ResponseHandler.generateResponse(response.getMessage(),false,HttpStatus.NOT_IMPLEMENTED, response.getData());
        }
    }
}
