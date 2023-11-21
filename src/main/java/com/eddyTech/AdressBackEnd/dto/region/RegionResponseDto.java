package com.eddyTech.AdressBackEnd.dto.region;

import com.eddyTech.AdressBackEnd.model.Region;
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
public class RegionResponseDto {
    private UUID id;

    private String name;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createdAt;

    public RegionResponseDto(Region region) {

        this.setId(region.getId());
        this.setName(region.getName());
        this.setCreatedAt(region.getCreatedAt());
    }
}
