package com.apilibrary.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.apilibrary.model.Demand;

@Repository
public interface DemandRepository extends JpaRepository<Demand, Integer> {
	
	@Query("SELECT i from Demand i where i.accountId =:id")
	List<Demand> findDemandByAccountId(@Param("id") int id);
}
