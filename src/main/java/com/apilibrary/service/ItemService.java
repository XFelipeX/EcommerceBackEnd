package com.apilibrary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.apilibrary.model.Demand;
import com.apilibrary.model.Item;
import com.apilibrary.repository.ItemRepository;

@Service
public class ItemService {
	@Autowired
	private ItemRepository repository;
	
	public Item saveItem(Item item) {
		return repository.save(item);
	}
	
	public Page<Item> listItens (Pageable pageable){
		return repository.findAll(pageable);
	}
	
	public Item getItemById(int id) {
		return repository.findById(id).orElse(null);
	}
	
	public List<Item> getItemByDemand(int id) {
		return repository.findItemByDemandId(id);
	}
	
	public String deleteItem(int id) {
		repository.deleteById(id);
		return "item deleted";
	}
	
	public Item uptadeItem(Item item) {
		Item existingItem = repository.findById(item.getId()).orElse(null);
		existingItem.setAmount(item.getAmount());
		existingItem.setBookId(item.getBookId());
		existingItem.setDemandId(item.getDemandId());
		
		return repository.save(existingItem);
	}
}
