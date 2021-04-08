package com.apilibrary.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apilibrary.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	User findByEmail(String email);
	
	User findUserByAccountId(@Param("id") int id);

	User findUserByEmail(@Param("email") String email);

}
