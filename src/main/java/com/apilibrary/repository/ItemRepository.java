package com.apilibrary.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apilibrary.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

}