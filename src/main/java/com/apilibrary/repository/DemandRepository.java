package com.apilibrary.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.apilibrary.model.Demand;

@Repository
public interface DemandRepository extends JpaRepository<Demand, Integer> {
	
	@Query("SELECT i FROM Demand i WHERE i.accountId =:id ORDER BY i.id DESC")
	List<Demand> findDemandByAccountId(@Param("id") int id);
}
