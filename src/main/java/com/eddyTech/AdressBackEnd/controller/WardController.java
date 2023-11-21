package com.eddyTech.AdressBackEnd.controller;

import com.eddyTech.AdressBackEnd.dto.ward.CreateWardDto;
import com.eddyTech.AdressBackEnd.dto.ward.EditWardDto;
import com.eddyTech.AdressBackEnd.service.WardService;
import com.eddyTech.AdressBackEnd.utils.ResponseHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/address/ward")
@RequiredArgsConstructor
public class WardController {
    private final WardService wardService;

    @GetMapping(value = "{districtId}")
    public ResponseEntity<Object> fetchWards(@PathVariable UUID districtId){
        var response = wardService.fetchWards(districtId);

        if (response.getIsSuccess()){
            return ResponseHandler.generateResponse(response.getMessage(), true, HttpStatus.OK, response.getData());
        }else {
            return ResponseHandler.generateResponse(response.getMessage(), false, HttpStatus.NOT_IMPLEMENTED, response.getData());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createWard(@RequestBody @Valid CreateWardDto request){
        var response = wardService.createWard(request);
        if (response.getIsSuccess()){
            return ResponseHandler.generateResponse(response.getMessage(), true, HttpStatus.OK, response.getData());
        }else {
            return ResponseHandler.generateResponse(response.getMessage(), false, HttpStatus.NOT_IMPLEMENTED, response.getData());
        }
    }

    @PostMapping("/edit")
    public ResponseEntity<Object> editWard(@RequestBody @Valid EditWardDto request){
        var response = wardService.editWard(request);
        if (response.getIsSuccess()){
            return ResponseHandler.generateResponse(response.getMessage(), true, HttpStatus.OK, response.getData());
        }else {
            return ResponseHandler.generateResponse(response.getMessage(), false, HttpStatus.NOT_IMPLEMENTED, response.getData());
        }
    }

}
