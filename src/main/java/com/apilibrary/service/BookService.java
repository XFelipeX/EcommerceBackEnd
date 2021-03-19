package com.apilibrary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.apilibrary.model.Book;
import com.apilibrary.repository.BookRepository;

@Service
public class BookService {
	@Autowired
	private BookRepository repository;

	public Book saveBook(Book book) {
		return repository.save(book);
	}

	public Page<Book> listBooks(Pageable pageable) {
		return repository.findAll(pageable);
	}

	public Book getBookById(int id) {
		return repository.findById(id).orElse(null);
	}

//	public Book getBookByName(String nameBook) {
//		return repositoryImp.findByName(nameBook);
//	}

	public String deleteBook(int id) {
		repository.deleteById(id);
		return "book deleted";
	}

	public Book updateBook(Book book) {
		Book existingBook = repository.findById(book.getId()).orElse(null);
		existingBook.setNameBook(book.getNameBook());
		existingBook.setDescription(book.getDescription());
		existingBook.setAmount(book.getAmount());
		existingBook.setAuthorId(book.getAuthorId());
		existingBook.setAvailable(book.getAvailable());
		existingBook.setCategory(book.getCategory());
		existingBook.setIsbn(book.getIsbn());
		existingBook.setStars(book.getStars());
		existingBook.setPrice(book.getPrice());
		existingBook.setPublishCompanyId(book.getPublishCompanyId());
		existingBook.setPublishDate(book.getPublishDate());

		return repository.save(existingBook);
	}
}
