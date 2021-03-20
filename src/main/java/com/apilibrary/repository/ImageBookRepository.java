package com.apilibrary.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.apilibrary.model.ImageBook;

@Repository
@Transactional
public interface ImageBookRepository extends JpaRepository<ImageBook, Integer> {
	List<ImageBook> findImagesBookByBookId(@Param("bookId") Integer bookId);
	
	@Query("SELECT i from ImageBook i where i.bookId =:id and i.main=1")
	ImageBook findImageBookByBookIdAndMain(@Param("id") int id);
}
