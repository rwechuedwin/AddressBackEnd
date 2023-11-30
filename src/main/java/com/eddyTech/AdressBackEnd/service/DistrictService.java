package com.eddyTech.AdressBackEnd.service;

import com.eddyTech.AdressBackEnd.dto.district.CreateDistrictDto;
import com.eddyTech.AdressBackEnd.dto.district.DistrictResponseDto;
import com.eddyTech.AdressBackEnd.dto.district.EditDistrictDto;
import com.eddyTech.AdressBackEnd.model.District;
import com.eddyTech.AdressBackEnd.repository.DistrictRepository;
import com.eddyTech.AdressBackEnd.repository.RegionRepository;
import com.eddyTech.AdressBackEnd.utils.InternalResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DistrictService {

    private final ModelMapper modelMapper;
    private final DistrictRepository districtRepository;

    private final RegionRepository regionRepository;

    public InternalResponse fetchDistricts(UUID regionId){
        var region = regionRepository.findById(regionId);

        if (region.isEmpty()){
            return response("Region does not exists", false, null);
        }
        var districts = districtRepository.findAllByRegion(region.get());

        var resp = districts.stream().map(DistrictResponseDto::new).toList();

        return response("District list.", true, resp);
    }

    public InternalResponse createDistrict(CreateDistrictDto request){

        var region = regionRepository.findById(request.getRegion_id());

        if (region.isEmpty()){
            return response("Region does not exists", false, null);
        }

        var checkIfExisting = districtRepository.findFirstByNameAndRegion(request.getName(), region.get());

        if (checkIfExisting.isPresent()){
            return response("This district already exists", false, null);
        }

        var district = District.builder()
                .name(request.getName())
                .region(region.get())
                .build();

        districtRepository.save(district);

        var resp = modelMapper.map(district, DistrictResponseDto.class);

        return response("District created.", true, resp);

    }

    public InternalResponse editDistrict(EditDistrictDto request){

        var district = districtRepository.findById(request.getId());

        if (district.isEmpty()){
            return response("District does not exists", false, null);
        }

        var region = regionRepository.findById(request.getRegion_id());

        if (region.isEmpty()){
            return response("Region does not exists", false, null);
        }


        var _district = district.get();
        _district.setName(request.getName());
        _district.setRegion(region.get());

        _district = districtRepository.save(_district);

        var resp = modelMapper.map(_district, DistrictResponseDto.class);

        return response("District updated.", true, resp);
    }


    private InternalResponse response(String message, Boolean status, Object object) {
        return InternalResponse.builder()
                .isSuccess(status)
                .message(message)
                .data(object)
                .build();
    }
}
