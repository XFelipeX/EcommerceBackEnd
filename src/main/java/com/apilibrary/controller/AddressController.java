package com.apilibrary.controller;

import java.util.GregorianCalendar;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apilibrary.model.Address;
import com.apilibrary.response.ErrorMessage;
import com.apilibrary.response.SuccessMessage;
import com.apilibrary.service.AddressService;

import javassist.NotFoundException;

@CrossOrigin
@RestController
public class AddressController {

	@Autowired
	private AddressService serviceAddress;

	@GetMapping("/address")
	public ResponseEntity<Object> listAddress() {
		List<Address> listAddress = serviceAddress.listAddress();

		if (listAddress == null) {
			return new ResponseEntity<Object>(null, HttpStatus.BAD_REQUEST);
		}
		SuccessMessage response = new SuccessMessage(listAddress);

		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

	@GetMapping("/address/{id}")
	public ResponseEntity<Object> getAddress(@PathVariable int id) throws NotFoundException {
		Address address = serviceAddress.getAddressById(id);

		if (address == null) {
			throw new NotFoundException("Not found for id " + id);
		}

		SuccessMessage response = new SuccessMessage(address);

		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

	@PostMapping("/address")
	public ResponseEntity<Object> addAddress(@RequestBody @Valid Address address, @RequestParam int typeAccount) {
		if (typeAccount != 2) {
			ErrorMessage error = new ErrorMessage(new GregorianCalendar(), "", "typeAccout is broken");
			return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);

		}
		Address ad = serviceAddress.saveAddress(address, typeAccount);

		if (ad == null) {
			ErrorMessage error = new ErrorMessage(new GregorianCalendar(), "", "has a error on create address");
			return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
		}

		SuccessMessage response = new SuccessMessage(ad);

		return new ResponseEntity<Object>(response, HttpStatus.CREATED);
	}

	@PutMapping("/address")
	public ResponseEntity<Object> updateAddress(@RequestBody Address address) {
		Address ad = serviceAddress.updateAddress(address);

		if (ad == null) {
			return new ResponseEntity<Object>(null, HttpStatus.BAD_REQUEST);
		}

		SuccessMessage response = new SuccessMessage(ad);

		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

	@DeleteMapping("/address/{id}")
	public ResponseEntity<Object> deleteAddress(@PathVariable int id) {
		String message = serviceAddress.deleteAddress(id);
		if (message == null) {
			return new ResponseEntity<Object>(null, HttpStatus.BAD_REQUEST);
		}
		SuccessMessage response = new SuccessMessage(null);

		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

}
