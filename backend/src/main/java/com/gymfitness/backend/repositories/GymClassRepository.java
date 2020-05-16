package com.gymfitness.backend.repositories;

import com.gymfitness.backend.models.GymClass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GymClassRepository extends JpaRepository<GymClass, Long>{
}