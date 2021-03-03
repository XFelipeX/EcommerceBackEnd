package com.apilibrary.controller;

import java.util.Base64;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.apilibrary.model.ImageBook;
import com.apilibrary.response.SuccessMessage;
import com.apilibrary.service.ImageBookService;

import javassist.NotFoundException;

@CrossOrigin
@RestController
public class ImageBookController {
	@Autowired
	private ImageBookService service;

	@PostMapping("/image_book")
	public ResponseEntity<Object> addBook(@RequestBody @Valid ImageBook imageBook) {
		imageBook.setImg(Base64.getDecoder().decode(imageBook.getBase().getBytes()));
		imageBook.setBase("");

		ImageBook obj = service.saveImageBook(imageBook);

		if (obj == null) {
			return new ResponseEntity<Object>(null, HttpStatus.BAD_REQUEST);
		}

		SuccessMessage response = new SuccessMessage(obj);

		return new ResponseEntity<Object>(response, HttpStatus.CREATED);
	}

	@GetMapping("/image_book")
	public ResponseEntity<Object> listBooks() {
		List<ImageBook> listImageBook = service.listImageBook();

		if (listImageBook == null) {
			return new ResponseEntity<Object>(null, HttpStatus.BAD_REQUEST);
		}

		SuccessMessage response = new SuccessMessage(listImageBook);

		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

	@GetMapping("/image_book/{id}")
	public ResponseEntity<Object> getImageBook(@PathVariable int id) throws NotFoundException {
		ImageBook imageBook = service.getImageBookById(id);

		if (imageBook == null) {
			throw new NotFoundException("Not found for id " + id);
		}

		SuccessMessage response = new SuccessMessage(imageBook);

		imageBook.setBase(new String(Base64.getEncoder().encode(imageBook.getImg())));
		imageBook.setImg(null);
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

	@PutMapping("/image_book")
	public ResponseEntity<Object> updateImageBook(@RequestBody ImageBook imgBook) {
		ImageBook imageBook = service.updateImageBook(imgBook);

		if (imageBook == null) {
			return new ResponseEntity<Object>(null, HttpStatus.BAD_REQUEST);
		}

		SuccessMessage response = new SuccessMessage(imageBook);

		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

	@DeleteMapping("/image_book/{id}")
	public ResponseEntity<Object> deleteImageBook(@PathVariable int id) {
		String message = service.deleteImageBook(id);
		if (message == null) {
			return new ResponseEntity<Object>(null, HttpStatus.BAD_REQUEST);
		}
		SuccessMessage response = new SuccessMessage(null);

		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
}
