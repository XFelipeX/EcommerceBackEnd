package com.apilibrary.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.apilibrary.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
	@Query("SELECT i from Item i where i.demandId =:id")
	List<Item> findItemByDemandId(@Param("id") int id);
}