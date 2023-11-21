package com.eddyTech.AdressBackEnd.repository;

import com.eddyTech.AdressBackEnd.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CountryRepository extends JpaRepository<Country, UUID> {

    Optional<Country> findFirstByName(String name);
}
