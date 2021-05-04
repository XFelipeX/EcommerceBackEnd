package com.apilibrary.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;

import com.apilibrary.model.Demand;
import com.apilibrary.response.SuccessMessage;
import com.apilibrary.service.DemandService;

import javassist.NotFoundException;

public class DemandController {
	
	@Autowired
	private DemandService serviceDemand;
	
	@PostMapping("/demand")
	public ResponseEntity<Object> addDemand(@RequestBody @Valid Demand demand){
		Demand dm = serviceDemand.saveDemand(demand);
		
		if(dm == null) {
			return new ResponseEntity<Object>(null, HttpStatus.BAD_REQUEST);
		}
		
		SuccessMessage response = new SuccessMessage(dm);
		
		return new ResponseEntity<Object>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("/demand")
	public Page<Demand> listDemands(Pageable pageble) {
		
		return serviceDemand.listDemands(pageble);
	}
	
	@GetMapping("/demand/{id}")
	public ResponseEntity<Object> getDemand(@PathVariable int id) throws NotFoundException {
		Demand dm = serviceDemand.getDemandId(id);
		
		if (dm == null) {
			throw new NotFoundException("Not found for id" + id);
		}
		
		SuccessMessage response = new SuccessMessage(dm);
		
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
	
	@PutMapping("/demand")
	public ResponseEntity<Object> updateDemand(@RequestBody Demand demand){
		Demand dm = serviceDemand.updateDemand(demand);
		
		if(dm == null) {
			return new ResponseEntity<Object>(null, HttpStatus.BAD_REQUEST);
		}
		
		SuccessMessage response = new SuccessMessage(dm);
		
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
	
	public ResponseEntity<Object> deleteDemand(@PathVariable int id) {
		String message = serviceDemand.deleteDemand(id);
		
		if(message == null) {
			return new ResponseEntity<Object>(null, HttpStatus.BAD_REQUEST);
		}
		
		SuccessMessage response = new SuccessMessage(null);
		
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
}
