package com.apilibrary.controller;

import java.util.GregorianCalendar;

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

import com.apilibrary.model.Account;
import com.apilibrary.response.ErrorMessage;
import com.apilibrary.response.SuccessMessage;
import com.apilibrary.service.AccountService;

import javassist.NotFoundException;

@CrossOrigin
@RestController
public class AccountController {
	@Autowired
	private AccountService serviceAccount;

	@PostMapping("/account")
	public ResponseEntity<Object> addAccount(@RequestBody @Valid Account account) {
		Account responseAccount = serviceAccount.saveAccount(account);

		if (responseAccount == null) {
			ErrorMessage error = new ErrorMessage(new GregorianCalendar(),"", "(email,cpf) is broken");
			return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
		}

		SuccessMessage response = new SuccessMessage(responseAccount);

		return new ResponseEntity<Object>(response, HttpStatus.CREATED);
	}
	
	
	@GetMapping("/account")
	public Page<Account> listAccount(Pageable pageable) {

		return serviceAccount.listAllAccounts(pageable);
	}

	@GetMapping("/account/{id}")
	public ResponseEntity<Object> getAccount(@PathVariable int id) throws NotFoundException {
		Account account = serviceAccount.getAccountById(id);

		if (account == null) {
			throw new NotFoundException("Not found for id " + id);
		}

		SuccessMessage response = new SuccessMessage(account);

		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

	@PutMapping("/account")
	public ResponseEntity<Object> updateAccount(@RequestBody Account account) {
		Account responseAccount = serviceAccount.updateAccount(account);

		if (responseAccount == null) {
			return new ResponseEntity<Object>(null, HttpStatus.BAD_REQUEST);
		}

		SuccessMessage response = new SuccessMessage(responseAccount);

		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

	@DeleteMapping("/account/{id}")
	public ResponseEntity<Object> deleteAccount(@PathVariable int id) {
		String message = serviceAccount.deleteAccount(id);
		if (message == null) {
			return new ResponseEntity<Object>(null, HttpStatus.BAD_REQUEST);
		}
		SuccessMessage response = new SuccessMessage(null);

		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
}
