package com.apilibrary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.apilibrary.model.User;
import com.apilibrary.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository repository;
	
	public User saveUser(User user) {
		return repository.save(user);
	}
	
	public Page<User> listAllUsers(Pageable pageable){
		return repository.findAll(pageable);
	}
	
	public User getUserById(int id) {
		return repository.findById(id).orElse(null);
	}
	
	public String deleteUser(int id) {
		repository.deleteById(id);
		return "user deleted";
	}
	
	public User updateUser(User user) {
		User existingUser = repository.findById(user.getId()).orElse(null);
		existingUser.setEmail(user.getEmail());
		existingUser.setUserPassword(user.getUserPassword());
		existingUser.setTypeAccount(user.getTypeAccount());
		existingUser.setUserName(user.getUserName());
		
		return repository.save(existingUser);
	}
}
