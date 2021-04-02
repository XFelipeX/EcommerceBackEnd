package com.apilibrary.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import com.apilibrary.model.User;
import com.apilibrary.response.SuccessMessage;
import com.apilibrary.service.UserService;

import javassist.NotFoundException;

@CrossOrigin
@RestController
public class UserController {
	@Autowired
	private UserService serviceUser;

	@PostMapping("/user")
	public ResponseEntity<Object> addUser(@RequestBody @Valid User user) {
		User responseUser = serviceUser.saveUser(user);

		if (responseUser == null) {
			return new ResponseEntity<Object>(null, HttpStatus.BAD_REQUEST);
		}

		SuccessMessage response = new SuccessMessage(user);

		return new ResponseEntity<Object>(response, HttpStatus.CREATED);
	}

	@GetMapping("/user")
	public Page<User> listUsers(Pageable pageable) {

		return serviceUser.listAllUsers(pageable);
	}

	@GetMapping("/user/{id}")
	public ResponseEntity<Object> getUser(@PathVariable int id) throws NotFoundException {
		User user = serviceUser.getUserById(id);

		if (user == null) {
			throw new NotFoundException("Not found for id " + id);
		}

		SuccessMessage response = new SuccessMessage(user);

		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

	@PutMapping("/user")
	public ResponseEntity<Object> updateBook(@RequestBody User user) {
		User responseUser = serviceUser.updateUser(user);

		if (responseUser == null) {
			return new ResponseEntity<Object>(null, HttpStatus.BAD_REQUEST);
		}

		SuccessMessage response = new SuccessMessage(responseUser);

		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

	@DeleteMapping("/user/{id}")
	public ResponseEntity<Object> deleteBook(@PathVariable int id) {
		String message = serviceUser.deleteUser(id);
		if (message == null) {
			return new ResponseEntity<Object>(null, HttpStatus.BAD_REQUEST);
		}
		SuccessMessage response = new SuccessMessage(null);

		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
}
