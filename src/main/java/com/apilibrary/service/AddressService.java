package com.apilibrary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apilibrary.model.Address;
import com.apilibrary.repository.AddressRepository;

@Service
public class AddressService {
	@Autowired
	private AddressRepository repository;
	
	public Address saveAddress(Address address) {
		return repository.save(address);
	}
	
	public List<Address> listAddress(){
		return repository.findAll();
	}
	
	public Address getAddressById(int id) {
		return repository.findById(id).orElse(null);
	}
	
	public String deleteAddress(int id) {
		repository.deleteById(id);
		return "address deleted";
	}
	
	public Address updateAddress(Address address) {
		Address existingAddress = repository.findById(address.getId()).orElse(null);
		existingAddress.setPublicArea(address.getPublicArea());
		existingAddress.setCep(address.getCep());
		existingAddress.setUf(address.getUf());
		existingAddress.setNumber(address.getNumber());
		existingAddress.setLocal(address.getLocal());
		existingAddress.setComplement(address.getComplement());
		existingAddress.setType(address.getType());
		existingAddress.setStatus(address.getStatus());
		existingAddress.setAccountId(address.getAccountId());
		existingAddress.setAccountUser(address.getAccountUser());
		
		return repository.save(existingAddress);
	}
}
