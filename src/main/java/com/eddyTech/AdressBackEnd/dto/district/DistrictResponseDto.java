package com.eddyTech.AdressBackEnd.dto.district;

import com.eddyTech.AdressBackEnd.model.District;
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
public class DistrictResponseDto {
    private UUID id;

    private String name;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createdAt;

    public DistrictResponseDto(District district) {
        this.setId(district.getId());
        this.setName(district.getName());
        this.setCreatedAt(district.getCreatedAt());
    }
}
