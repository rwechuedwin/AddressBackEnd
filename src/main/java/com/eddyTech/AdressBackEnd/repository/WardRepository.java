package com.eddyTech.AdressBackEnd.repository;

import com.eddyTech.AdressBackEnd.model.District;
import com.eddyTech.AdressBackEnd.model.Ward;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WardRepository  extends JpaRepository<Ward, UUID> {
    List<Ward> findAllByDistrict (District district);
    Optional<Ward> findFirstByNameAndDistrict(String name, District district);
}
