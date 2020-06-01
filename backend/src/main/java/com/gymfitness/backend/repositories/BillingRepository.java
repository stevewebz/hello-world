package com.gymfitness.backend.repositories;

import java.util.List;

import com.gymfitness.backend.models.Billing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillingRepository extends JpaRepository<Billing, Long>{
    List<Billing> findByUserUserId(Long userid);

    Billing findByBillingId(long billingid);
}