package com.gymfitness.backend.repositories;

import java.util.Optional;

import com.gymfitness.backend.models.EnumLevel;
import com.gymfitness.backend.models.UserLevel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLevelRepository extends JpaRepository<UserLevel, Long>{
    Optional<UserLevel> findByLevelName(EnumLevel name);
}