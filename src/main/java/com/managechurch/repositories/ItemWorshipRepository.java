package com.managechurch.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.managechurch.entities.ItemWorshipEntity;

@Repository
public interface ItemWorshipRepository extends JpaRepository<ItemWorshipEntity, Integer> {

    ItemWorshipEntity findByName(String name);

}
