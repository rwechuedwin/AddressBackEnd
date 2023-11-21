package com.eddyTech.AdressBackEnd.dto.country;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateCountryDto {
   @NotBlank(message = "Name is required")
    private String name;

    @Nullable
    private String code;
}
