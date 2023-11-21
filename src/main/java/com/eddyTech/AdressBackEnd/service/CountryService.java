package com.eddyTech.AdressBackEnd.service;


import com.eddyTech.AdressBackEnd.dto.country.CountryResponseDto;
import com.eddyTech.AdressBackEnd.dto.country.CreateCountryDto;
import com.eddyTech.AdressBackEnd.dto.country.EditCountryDto;
import com.eddyTech.AdressBackEnd.model.Country;
import com.eddyTech.AdressBackEnd.repository.CountryRepository;
import com.eddyTech.AdressBackEnd.utils.InternalResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CountryService {

    private final ModelMapper modelMapper;
    private final CountryRepository countryRepository;

    //Get all Countries
    public InternalResponse fetchCountries(){
        var operation = countryRepository.findAll();

        var resp = operation.stream().map(CountryResponseDto::new).toList();

        //return InternalResponse.builder().isSuccess(true).message("Country List.").data(resp).build();
        return  response("Country List.", true, resp);
    }

    //Create or Add Country
    public InternalResponse createCountry(CreateCountryDto request){
        var checkIfExist = countryRepository.findFirstByName(request.getName());

        if (checkIfExist.isPresent()){
            return response("This country already exists", false, null);
        }

        var country = Country.builder()
                        .name(request.getName())
                        .code(request.getCode())
                        .build();

        countryRepository.save(country);

        var resp = modelMapper.map(country, CountryResponseDto.class);

        return response("Country created.", true, resp);

    }

    //Edit or Update Country
    public InternalResponse editCountry(EditCountryDto request){
        var country = countryRepository.findById(request.getId());

        if (country.isEmpty()){
            return response("Country does not exist", false, null);
        }

        var _country = country.get();
        _country.setName(request.getName());
        _country.setCode(request.getCode());

        _country = countryRepository.save(_country);

        var resp = modelMapper.map(_country, CountryResponseDto.class);

        return response("Country Updated", true, resp);
    }

    private InternalResponse response(String message, Boolean status, Object object){
        return InternalResponse.builder().isSuccess(status).message(message).data(object).build();
    }
}
