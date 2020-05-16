package com.gymfitness.backend.repositories;

import com.gymfitness.backend.models.UserLevel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLevelRepository extends JpaRepository<UserLevel, Long>{
}