package com.gymfitness.backend.repositories;

import com.gymfitness.backend.models.Waitlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WaitlistRepository extends JpaRepository<Waitlist, Long>{
}