package com.gymfitness.backend.repositories;

import java.util.List;

import com.gymfitness.backend.models.GymClass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GymClassRepository extends JpaRepository<GymClass, Long>{
    List<GymClass> findByUserUserId(Long userid);

    GymClass findByWaitlistWaitlistId(Long waitlistid);
}