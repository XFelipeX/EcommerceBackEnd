package com.apilibrary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apilibrary.model.Book;
import com.apilibrary.repository.BookRepositoryCustom;

@Service
public class BookService {
	@Autowired
	private BookRepositoryCustom repositoryImp;

	public Book saveBook(Book book) {
		return repositoryImp.save(book);
	}

	public List<Book> listBooks() {
		return repositoryImp.findAll();
	}

	public Book getBookById(int id) {
		return repositoryImp.findById(id).orElse(null);
	}

//	public Book getBookByName(String nameBook) {
//		return repositoryImp.findByName(nameBook);
//	}

	public String deleteBook(int id) {
		repositoryImp.deleteById(id);
		return "book deleted";
	}

	public Book updateBook(Book book) {
		Book existingBook = repositoryImp.findById(book.getId()).orElse(null);
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

		return repositoryImp.save(existingBook);
	}
}
