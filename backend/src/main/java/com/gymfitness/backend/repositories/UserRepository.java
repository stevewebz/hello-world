package com.gymfitness.backend.repositories;

import java.util.Optional;

import com.gymfitness.backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{
    User findByUserId(Long userid);

    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);

    Boolean existsByUserIdAndGymClassesClassId(Long userid, Long classid);
}