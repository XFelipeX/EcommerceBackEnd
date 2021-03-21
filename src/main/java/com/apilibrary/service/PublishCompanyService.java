package com.apilibrary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apilibrary.model.PublishCompany;
import com.apilibrary.repository.PublishCompanyRepository;

@Service
public class PublishCompanyService {

	@Autowired
	private PublishCompanyRepository repository;

	public PublishCompany savePublishCompany(PublishCompany publishCompany) {
		return repository.save(publishCompany);
	}
	
	public List<PublishCompany> listPublishCompanys(){
		return repository.findAll();
	}
	
	public PublishCompany getPublishCompanyById(int id) {
		return repository.findById(id).orElse(null);
	}
	
	public String deletePublishCompany(int id) {
		repository.deleteById(id);
		return "publish_Company deleted";
	}
	
	public PublishCompany updatePublishCompany(PublishCompany publishCompany) {
		PublishCompany existingPublishCompany = repository.findById(publishCompany.getId()).orElse(null);
		existingPublishCompany.setDescriptionCompany(publishCompany.getDescriptionCompany());
		existingPublishCompany.setAddress(publishCompany.getAddress());
		existingPublishCompany.setCnpj(publishCompany.getCnpj());
		existingPublishCompany.setCep(publishCompany.getCep());
		existingPublishCompany.setFullDescription(publishCompany.getFullDescription());
		existingPublishCompany.setPhoneNumber(publishCompany.getPhoneNumber());
		
		return repository.save(existingPublishCompany);
	}
}
