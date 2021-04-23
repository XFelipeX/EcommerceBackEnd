package com.apilibrary.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apilibrary.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	User findByEmail(String email);
	@Query("SELECT i FROM User i where i.accountId =:id and i.typeAccount != 2")
	User findUserByAccountIdAndTypeAccount(@Param("id") int id);

	User findUserByEmail(@Param("email") String email);

}
