package com.gymfitness.backend.repositories;

import com.gymfitness.backend.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long>{
}