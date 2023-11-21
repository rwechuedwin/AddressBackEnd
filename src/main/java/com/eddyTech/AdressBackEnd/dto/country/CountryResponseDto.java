package com.eddyTech.AdressBackEnd.dto.country;

import com.eddyTech.AdressBackEnd.model.Country;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CountryResponseDto {
    private UUID id;
    private String name;
    private String code;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;

    public CountryResponseDto(Country country){
        this.setId(country.getId());
        this.setName(country.getName());
        this.setCode(country.getCode());
        this.setCreatedAt(country.getCreatedAt());
    }
}
