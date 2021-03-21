package com.apilibrary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.apilibrary.model.Author;
import com.apilibrary.repository.AuthorRepository;

@Service
public class AuthorService {
	@Autowired
	private AuthorRepository repository;
	
	public Author saveAuthor(Author author) {
		return repository.save(author);
	}
	
//	public Page<Author> listAuthors(Pageable pageable){
//		return repository.findAll(pageable);
//	}
	
	public List<Author> listAuthors(){
		return repository.findAll();
	}
	
	public Author getAuthorById(int id) {
		return repository.findById(id).orElse(null);
	}
	
	public String deleteAuthor(int id) {
		repository.deleteById(id);
		return "author deleted";
	}
	
	public Author updateAuthor(Author author) {
		Author existingAuthor = repository.findById(author.getId()).orElse(null);
		existingAuthor.setNameAuthor(author.getNameAuthor());
		existingAuthor.setCpf(author.getCpf());
		
		return repository.save(existingAuthor);
	}

}
