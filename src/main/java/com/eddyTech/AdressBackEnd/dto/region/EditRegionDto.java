package com.eddyTech.AdressBackEnd.dto.region;

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
public class EditRegionDto {
    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Country is required")
    private UUID country_id;

    @NotNull(message = "Id is required")
    private UUID id;
}
