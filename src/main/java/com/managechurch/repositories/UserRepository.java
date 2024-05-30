package com.managechurch.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.managechurch.entities.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    // UserEntity findByName(String name);

    // UserEntity findByLogin(String login);

}
