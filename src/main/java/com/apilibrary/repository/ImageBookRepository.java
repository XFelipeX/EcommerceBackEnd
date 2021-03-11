package com.apilibrary.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.apilibrary.model.ImageBook;

@Repository
public interface ImageBookRepository extends JpaRepository<ImageBook, Integer> {
	List<ImageBook> findImagesBookByBookId(@Param("bookId") Integer bookId);
}
