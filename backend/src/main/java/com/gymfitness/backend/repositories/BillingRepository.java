package com.gymfitness.backend.repositories;

import com.gymfitness.backend.models.Billing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillingRepository extends JpaRepository<Billing, Long>{
}