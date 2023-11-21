package com.eddyTech.AdressBackEnd.repository;

import com.eddyTech.AdressBackEnd.model.District;
import com.eddyTech.AdressBackEnd.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DistrictRepository extends JpaRepository<District, UUID> {

    List<District> findAllByRegion(Region region);
    Optional<District> findFirstByNameAndRegion(String name, Region region);
}
