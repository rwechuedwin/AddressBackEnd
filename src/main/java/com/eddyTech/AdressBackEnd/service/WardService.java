package com.eddyTech.AdressBackEnd.service;

import com.eddyTech.AdressBackEnd.dto.district.DistrictResponseDto;
import com.eddyTech.AdressBackEnd.dto.ward.CreateWardDto;
import com.eddyTech.AdressBackEnd.dto.ward.EditWardDto;
import com.eddyTech.AdressBackEnd.dto.ward.WardResponseDto;
import com.eddyTech.AdressBackEnd.model.Ward;
import com.eddyTech.AdressBackEnd.repository.DistrictRepository;
import com.eddyTech.AdressBackEnd.repository.WardRepository;
import com.eddyTech.AdressBackEnd.utils.InternalResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WardService {

    private final ModelMapper modelMapper;
    private final WardRepository wardRepository;

    private final DistrictRepository districtRepository;

    public InternalResponse fetchWards(UUID districtId){
        var district = districtRepository.findById(districtId);

        if (district.isEmpty()){
            return response("District does not exists", false, null);
        }
        var wards = wardRepository.findAllByDistrict(district.get());

        var resp = wards.stream().map(WardResponseDto::new).toList();

        return response("Wards list.", true, resp);
    }

    public InternalResponse createWard(CreateWardDto request){

        var district = districtRepository.findById(request.getDistrictId());

        if (district.isEmpty()){
            return response("District does not exists", false, null);
        }

        var checkIfExisting = wardRepository.findFirstByNameAndDistrict(request.getName(), district.get());

        if (checkIfExisting.isPresent()){
            return response("This ward already exists", false, null);
        }

        var ward = Ward.builder()
                .name(request.getName())
                .district(district.get())
                .build();

        wardRepository.save(ward);

        var resp = modelMapper.map(ward, WardResponseDto.class);

        return response("Ward created.", true, resp);

    }

    public InternalResponse editWard(EditWardDto request){

        var ward = wardRepository.findById(request.getId());

        if (ward.isEmpty()){
            return response("Ward does not exists", false, null);
        }

        var district = districtRepository.findById(request.getDistrictId());

        if (district.isEmpty()){
            return response("District does not exists", false, null);
        }


        var _ward = ward.get();
        _ward.setName(request.getName());
        _ward.setDistrict(district.get());

        _ward = wardRepository.save(_ward);

        var resp = modelMapper.map(_ward, DistrictResponseDto.class);

        return response("Ward updated.", true, resp);
    }


    private InternalResponse response(String message, Boolean status, Object object) {
        return InternalResponse.builder()
                .isSuccess(status)
                .message(message)
                .data(object)
                .build();
    }
}
