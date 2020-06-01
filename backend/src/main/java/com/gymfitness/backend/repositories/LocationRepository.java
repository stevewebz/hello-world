package com.gymfitness.backend.repositories;

import com.gymfitness.backend.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
    Location findByLocationId(Long locationid);
}