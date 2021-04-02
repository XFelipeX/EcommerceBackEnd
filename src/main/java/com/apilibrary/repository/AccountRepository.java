package com.apilibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apilibrary.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>{

}
