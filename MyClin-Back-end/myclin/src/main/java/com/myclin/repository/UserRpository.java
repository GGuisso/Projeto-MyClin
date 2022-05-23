package com.myclin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myclin.entity.User;

public interface UserRpository extends JpaRepository<User, Integer> {

}
