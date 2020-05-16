package com.gymfitness.backend.repositories;

import com.gymfitness.backend.models.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance, Long>{
}