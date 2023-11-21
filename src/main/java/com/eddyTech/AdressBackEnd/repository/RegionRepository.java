package com.eddyTech.AdressBackEnd.repository;

import com.eddyTech.AdressBackEnd.model.Country;
import com.eddyTech.AdressBackEnd.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RegionRepository extends JpaRepository<Region, UUID> {
    List<Region> findAllByCountry(Country country);
    Optional<Region> findFirstByNameAndCountry(String name, Country country);
}
