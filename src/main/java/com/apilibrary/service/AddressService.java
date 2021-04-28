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

	public Address saveAddress(Address address,int typeAccount) {
		if (verifyTypeAccount(typeAccount)
				&& (address.getType().equals("C") || address.getType().equals("F"))) {
			verifyIfExistAddressActive(address.getType(), address.getStatus());
			return repository.save(address);
		}
		return null;
	}

	public List<Address> listAddress() {
		return repository.findAll();
	}

	public Address getAddressById(int id) {
		return repository.findById(id).orElse(null);
	}
	
	public List<Address> getAddressByStatusAndAccountId(int id) {
		return repository.findAddressByStatusAndAccountId(id);
	}
	
	public List<Address> getAddressByAccountId(int id, String type) {
		return repository.findAddresByAccoountId(id,type);
	}

	public String deleteAddress(int id) {
		repository.deleteById(id);
		return "address deleted";
	}

	public Address updateAddress(Address address) {
		verifyIfExistAddressActive(address.getType(), address.getStatus());
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

		return repository.save(existingAddress);
	}

	private void verifyIfExistAddressActive(String type, int status) {
		if (status == 1) {
			Address addressActive = repository.findAddressByStatusAndType(type);
			if (addressActive != null) {
				addressActive.setStatus(0);
				repository.save(addressActive);
			}
		}

	}

	private boolean verifyTypeAccount(int accountId) {
		if (accountId != 2)
			return false;
		else
			return true;
	}
}
