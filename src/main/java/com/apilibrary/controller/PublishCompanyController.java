package com.apilibrary.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.apilibrary.model.PublishCompany;
import com.apilibrary.response.SuccessMessage;
import com.apilibrary.service.PublishCompanyService;

import javassist.NotFoundException;

@CrossOrigin
@RestController
public class PublishCompanyController {

	@Autowired
	private PublishCompanyService servicePublishCompany;

	@GetMapping("/publishCompany")
	public List<PublishCompany> listPublishCompanys(){
		return servicePublishCompany.listPublishCompanys();
	}

	@GetMapping("/publishCompany/{id}")
	public ResponseEntity<Object> getPublishCompany(@PathVariable int id) throws NotFoundException {
		PublishCompany publishCompany = servicePublishCompany.getPublishCompanyById(id);

		if (publishCompany == null) {
			throw new NotFoundException("Not found for id " + id);
		}

		SuccessMessage response = new SuccessMessage(publishCompany);

		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

}
