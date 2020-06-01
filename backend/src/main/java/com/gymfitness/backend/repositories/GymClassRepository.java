package com.gymfitness.backend.repositories;

import java.util.List;

import com.gymfitness.backend.models.GymClass;
import com.gymfitness.backend.models.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GymClassRepository extends JpaRepository<GymClass, Long>{
    List<GymClass> findByUsersUserId(Long userid);

    List<GymClass> findByInstructor(User instructor);

    GymClass findByWaitlistWaitlistId(Long waitlistid);
}