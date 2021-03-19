package com.apilibrary.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apilibrary.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

}
