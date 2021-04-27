package com.apilibrary.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.apilibrary.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
	@Query("SELECT i from Address i where i.status = 1 and i.type =:type")
	Address findAddressByStatusAndType(@Param("type") String type);
	
	@Query("SELECT i from Address i where i.status = 1 and i.accountId =:accountId")
	List<Address> findAddressByStatusAndAccountId(@Param("accountId")int accountId);
}
