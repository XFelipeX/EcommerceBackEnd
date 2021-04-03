package com.apilibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apilibrary.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	User findByEmail(String email);
}
