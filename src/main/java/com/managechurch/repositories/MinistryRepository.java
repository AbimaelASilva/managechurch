package com.managechurch.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.managechurch.entities.MinistryEntity;

@Repository
public interface MinistryRepository extends JpaRepository<MinistryEntity, Integer> {

    MinistryEntity findByName(String name);

}
