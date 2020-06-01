package com.gymfitness.backend.repositories;

import java.util.List;

import com.gymfitness.backend.models.Waitlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WaitlistRepository extends JpaRepository<Waitlist, Long>{
    Boolean existsByUserUserIdAndGymClassClassId(Long userid, Long classid);

    Waitlist findByUserUserIdAndGymClassClassId(Long userid, Long classid);

    List<Waitlist> findByUserUserId(Long userid);

    List<Waitlist> findByGymClassClassId(Long classid);
}