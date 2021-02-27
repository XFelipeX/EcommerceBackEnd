package com.apilibrary.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.apilibrary.model.Book;
import com.apilibrary.service.BookService;

@RestController
public class BookController {

	@Autowired
	private BookService service;

	@PostMapping("/book")
	public Book addBook(@RequestBody Book book) {
		return service.saveBook(book);
	}

	@GetMapping("/book")
	public List<Book> listBooks() {
		return service.listBooks();
	}

	@GetMapping("/book/{id}")
	public Book getBook(@PathVariable int id) {
		return service.getBookById(id);
	}

//	@GetMapping("/book/{name}")
//	public Book findBookByName(@PathVariable String nameBook) {
//		return service.getBookByName(nameBook);
//	}

	@PutMapping("/book")
	public Book updateBook(@RequestBody Book book) {
		return service.updateBook(book);
	}

	@DeleteMapping("/book/{id}")
	public String deleteBook(@PathVariable int id) {
		return service.deleteBook(id);
	}
}
