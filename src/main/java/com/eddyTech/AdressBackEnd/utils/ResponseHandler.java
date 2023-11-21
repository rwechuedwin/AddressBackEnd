package com.eddyTech.AdressBackEnd.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {
    public static ResponseEntity<Object> generateResponse(String message, Boolean status, HttpStatus code, Object responseObj){
        Map<String, Object> map = new HashMap<>();

        map.put("message", message);
        map.put("success", status);
        map.put("code", code.value());
        map.put("data", responseObj);

        return new ResponseEntity<>(map, code);
    }
}
