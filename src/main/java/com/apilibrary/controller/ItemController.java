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

import com.apilibrary.model.Item;
import com.apilibrary.response.SuccessMessage;
import com.apilibrary.service.ItemService;

import javassist.NotFoundException;

@CrossOrigin
@RestController
public class ItemController {
	
	@Autowired
	private ItemService serviceItem;
	
	@PostMapping("/item")
	public ResponseEntity<Object> addItem(@RequestBody @Valid Item item){
		Item it = serviceItem.saveItem(item);
		
		if(it == null) {
			return new ResponseEntity<Object>(null, HttpStatus.BAD_REQUEST);
		}
		
		SuccessMessage response = new SuccessMessage(it);
		
		return new ResponseEntity<Object>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("/item")
	public Page<Item> listItens(Pageable pageable) {
		
		return serviceItem.listItens(pageable);
	}
	
	@GetMapping("/item/{id}")
	public ResponseEntity<Object> getItem(@PathVariable int id) throws NotFoundException{
		Item it = serviceItem.getItemById(id);
		
		if(it == null) {
			throw new NotFoundException("Not found for id" + id);
		}
		
		SuccessMessage response = new SuccessMessage(it);
		
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
	
	@PutMapping("/item")
	public ResponseEntity<Object> updateItem(@RequestBody Item item){
		Item it = serviceItem.uptadeItem(item);
		
		if(it == null) {
			return new ResponseEntity<Object>(null, HttpStatus.BAD_REQUEST);			
		}
		
		SuccessMessage response = new SuccessMessage(it);
		
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/item/{id}")
	public ResponseEntity<Object> deleteItem(@PathVariable int id){
		String message = serviceItem.deleteItem(id);
		
		if(message == null) {
			return new ResponseEntity<Object>(null, HttpStatus.BAD_REQUEST);
		}
		
		SuccessMessage response = new SuccessMessage(null);
		
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
}