package com.eddyTech.AdressBackEnd.dto.ward;

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
public class EditWardDto {
    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "District is required")
    private UUID district_id;

    @NotNull(message = "Id is required")
    private UUID id;

}
