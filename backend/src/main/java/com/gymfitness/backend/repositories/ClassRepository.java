package com.gymfitness.backend.repositories;

import com.gymfitness.backend.models.Class;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassRepository extends JpaRepository<Class, Long>{
}