package com.gymfitness.backend.repositories;

import com.gymfitness.backend.models.MemberLevel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberLevelRepository extends JpaRepository<MemberLevel, Long>{
}