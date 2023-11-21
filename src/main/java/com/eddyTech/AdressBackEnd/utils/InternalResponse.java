package com.eddyTech.AdressBackEnd.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InternalResponse {
    private String message;
    private Boolean isSuccess;
    private  Object data;
}
