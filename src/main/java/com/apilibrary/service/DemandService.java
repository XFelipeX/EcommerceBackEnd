package com.apilibrary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.apilibrary.model.Demand;
import com.apilibrary.repository.DemandRepository;

@Service
public class DemandService {
	@Autowired
	private DemandRepository repository;
	
	public Demand saveDemand(Demand demand) {
		return repository.save(demand);
	}
	
	public Page<Demand> listDemands(Pageable pageable){
		return repository.findAll(pageable);
	}
	
	public Demand getDemandById(int id) {
		return repository.findById(id).orElse(null);
	}
	
	public String deleteDemand(int id) {
		repository.deleteById(id);
		return "demand deleted";
	}
	
	public Demand updateDemand(Demand demand) {
		Demand existingDemand = repository.findById(demand.getId()).orElse(null);
		existingDemand.setDate(demand.getDate());
		existingDemand.setStatus(demand.getStatus());
		existingDemand.setPayment(demand.getPayment());
		existingDemand.setShipping(demand.getShipping());
		existingDemand.setTotal(demand.getTotal());
		existingDemand.setAccountId(demand.getAccountId());
		existingDemand.setAddressId(demand.getAddressId());
		
		return repository.save(existingDemand);		
	}
}
