package com.apilibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apilibrary.model.Demand;

@Repository
public interface DemandRepository extends JpaRepository<Demand, Integer> {
	
	
}
