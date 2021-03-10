package com.apilibrary.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.apilibrary.model.Book;
import com.apilibrary.response.SuccessMessage;
import com.apilibrary.service.BookService;

import javassist.NotFoundException;

@CrossOrigin
@RestController
public class BookController {

	@Autowired
	private BookService service;
	
	@PostMapping("/book")
	public ResponseEntity<Object> addBook(@RequestBody @Valid Book book) {
		Book bk = service.saveBook(book);

		if (bk == null) {
			return new ResponseEntity<Object>(null, HttpStatus.BAD_REQUEST);
		}

		SuccessMessage response = new SuccessMessage(bk);

		return new ResponseEntity<Object>(response, HttpStatus.CREATED);
	}

	@GetMapping("/book")
	public ResponseEntity<Object> listBooks() {
		List<Book> books = service.listBooks();

		if (books == null) {
			return new ResponseEntity<Object>(null, HttpStatus.BAD_REQUEST);
		}

		SuccessMessage response = new SuccessMessage(books);

		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

	@GetMapping("/book/{id}")
	public ResponseEntity<Object> getBook(@PathVariable int id) throws NotFoundException {
		Book book = service.getBookById(id);

		if (book == null) {
			throw new NotFoundException("Not found for id " + id);
		}

		SuccessMessage response = new SuccessMessage(book);

		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

//	@GetMapping("/book/{name}")
//	public Book findBookByName(@PathVariable String nameBook) {
//		return service.getBookByName(nameBook);
//	}

	@PutMapping("/book")
	public ResponseEntity<Object> updateBook(@RequestBody Book book) {
		Book bk = service.updateBook(book);

		if (bk == null) {
			return new ResponseEntity<Object>(null, HttpStatus.BAD_REQUEST);
		}

		SuccessMessage response = new SuccessMessage(bk);

		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

	@DeleteMapping("/book/{id}")
	public ResponseEntity<Object> deleteBook(@PathVariable int id) {
		String message = service.deleteBook(id);
		if (message == null) {
			return new ResponseEntity<Object>(null, HttpStatus.BAD_REQUEST);
		}
		SuccessMessage response = new SuccessMessage(null);

		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
}
