package com.apilibrary.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.apilibrary.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
	Page<Book> findAllByOrderByIdDesc(Pageable pageable);
	
	@Query("SELECT i FROM Book i WHERE i.available =:available")
	Page<Book> findAllByAvailable(Pageable pageable, int available);
}
