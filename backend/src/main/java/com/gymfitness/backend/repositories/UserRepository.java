package com.gymfitness.backend.repositories;

import com.gymfitness.backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{
}