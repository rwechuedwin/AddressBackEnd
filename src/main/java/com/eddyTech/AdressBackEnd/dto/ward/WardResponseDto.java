package com.eddyTech.AdressBackEnd.dto.ward;

import com.eddyTech.AdressBackEnd.model.Ward;
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
public class WardResponseDto {
    private UUID id;

    private String name;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createdAt;

    public WardResponseDto(Ward ward) {
        this.setId(ward.getId());
        this.setName(ward.getName());
        this.setCreatedAt(ward.getCreatedAt());
    }
}
