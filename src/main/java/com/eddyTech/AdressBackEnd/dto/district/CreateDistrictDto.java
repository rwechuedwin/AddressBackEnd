package com.eddyTech.AdressBackEnd.dto.district;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateDistrictDto {
    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Region is required")
    private UUID region_id;

}
