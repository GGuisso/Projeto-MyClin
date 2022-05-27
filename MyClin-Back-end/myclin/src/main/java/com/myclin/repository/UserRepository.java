package com.myclin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myclin.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
