package com.eddyTech.AdressBackEnd.dto.country;

import jakarta.annotation.Nullable;
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
public class EditCountryDto {
    @NotNull(message = "Id is required")
    private UUID id;

    @NotBlank(message = "Name is required")
    private String name;

    @Nullable
    private String code;
}
