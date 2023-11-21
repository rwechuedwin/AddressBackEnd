package com.eddyTech.AdressBackEnd.service;

import com.eddyTech.AdressBackEnd.dto.region.CreateRegionDto;
import com.eddyTech.AdressBackEnd.dto.region.EditRegionDto;
import com.eddyTech.AdressBackEnd.dto.region.RegionResponseDto;
import com.eddyTech.AdressBackEnd.model.Region;
import com.eddyTech.AdressBackEnd.repository.CountryRepository;
import com.eddyTech.AdressBackEnd.repository.RegionRepository;
import com.eddyTech.AdressBackEnd.utils.InternalResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RegionService {

    private final ModelMapper modelMapper;
    private final RegionRepository regionRepository;

    private final CountryRepository countryRepository;

    public InternalResponse fetchRegions(UUID countryId){
        var country = countryRepository.findById(countryId);

        if (country.isEmpty()){
            return response("Country does not exists", false, null);
        }
        var regions = regionRepository.findAllByCountry(country.get());

        var resp = regions.stream().map(RegionResponseDto::new).toList();

        return response("Regions list.", true, resp);
    }

    public InternalResponse createRegion(CreateRegionDto request){
        var country = countryRepository.findById(request.getCountry_id());

        if (country.isEmpty()){
            return response("Country does not exists", false, null);
        }

        var checkIfExisting = regionRepository.findFirstByNameAndCountry(request.getName(), country.get());

        if (checkIfExisting.isPresent()){
            return response("This region already exists", false, null);
        }

        var region = Region.builder()
                .name(request.getName())
                .country(country.get())
                .build();

        regionRepository.save(region);

        var resp = modelMapper.map(region, RegionResponseDto.class);

        return response("Region created.", true, resp);

    }

    public InternalResponse editRegion(EditRegionDto request){

        var region = regionRepository.findById(request.getId());

        if (region.isEmpty()){
            return response("Region does not exists", false, null);
        }

        var country = countryRepository.findById(request.getCountryId());

        if (country.isEmpty()){
            return response("Country does not exists", false, null);
        }


        var _region = region.get();
        _region.setName(request.getName());
        _region.setCountry(country.get());

        _region = regionRepository.save(_region);

        var resp = modelMapper.map(_region, RegionResponseDto.class);

        return response("Region updated.", true, resp);
    }


    private InternalResponse response(String message, Boolean status, Object object) {
        return InternalResponse.builder()
                .isSuccess(status)
                .message(message)
                .data(object)
                .build();
    }
}
