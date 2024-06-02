package com.managechurch.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.managechurch.entities.PeriodWorshipEntity;

@Repository
public interface PeriodWorshipRepository extends JpaRepository<PeriodWorshipEntity, Integer> {

    PeriodWorshipEntity findByName(String name);

}
