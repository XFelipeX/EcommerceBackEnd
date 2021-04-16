package com.apilibrary.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.apilibrary.model.Address;
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
		
		if(address == null) {
			throw new NotFoundException("Not found for id " + id);		
		}
		
		SuccessMessage response = new SuccessMessage(address);
		
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
}
