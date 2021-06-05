package com.apilibrary.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.apilibrary.model.Author;
import com.apilibrary.response.SuccessMessage;
import com.apilibrary.service.AuthorService;

import javassist.NotFoundException;

@CrossOrigin
@RestController
public class AuthorController {

	@Autowired
	private AuthorService serviceAuthor;

	@GetMapping("/author")
	public ResponseEntity<Object> listAuthors() {

		List<Author> listAuthors = serviceAuthor.listAuthors();

		if (listAuthors == null) {
			return new ResponseEntity<Object>(null, HttpStatus.BAD_REQUEST);
		}

		SuccessMessage response = new SuccessMessage(listAuthors);

		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

	@GetMapping("/author/{id}")
	public ResponseEntity<Object> getAuthor(@PathVariable int id) throws NotFoundException {
		Author author = serviceAuthor.getAuthorById(id);

		if (author == null) {
			throw new NotFoundException("Not found for id " + id);
		}

		SuccessMessage response = new SuccessMessage(author);

		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

}
