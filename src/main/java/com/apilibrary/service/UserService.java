package com.apilibrary.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.apilibrary.model.User;
import com.apilibrary.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository repository;
	@Autowired
	private UserRepository userRepository;
	private BCryptPasswordEncoder encode;

	public User saveUser(User user) {
		if(verifyExistEmail(user.getEmail())) return null;
		encode = new BCryptPasswordEncoder();
		user.setUserPassword(encode.encode(user.getUserPassword()));
		return repository.save(user);
	}

	public Page<User> listAllUsers(Pageable pageable) {
		return repository.findAll(pageable);
	}

	public User getUserById(int id) {
		return repository.findById(id).orElse(null);
	}

	public User getUserByAccount(int id) {
		return repository.findUserByAccountIdAndTypeAccount(id);
	}

	public User getUserByEmail(String email) {
		return repository.findUserByEmail(email);
	}

	public String deleteUser(int id) {
		repository.deleteById(id);
		return "user deleted";
	}

	public User updateUser(User user) {
		encode = new BCryptPasswordEncoder();
		User existingUser = repository.findById(user.getId()).orElse(null);
		existingUser.setEmail(user.getEmail());
		existingUser.setUserPassword(encode.encode(user.getUserPassword()));
		existingUser.setTypeAccount(user.getTypeAccount());
		existingUser.setUserName(user.getUserName());
		existingUser.setStatus(user.getStatus());
		existingUser.setAccountId(user.getAccountId());

		return repository.save(existingUser);
	}

	public User updateStatus(int id) {
		User existingUser = repository.findUserByAccountIdAndTypeAccount(id);

		if (existingUser.getStatus() == 1)
			existingUser.setStatus(0);
		else
			existingUser.setStatus(1);

		return repository.save(existingUser);
	}
	
	private boolean verifyExistEmail(String email) {
		// verify if email exist on database
		User userExist = userRepository.findByEmail(email);
		if (userExist != null)
			return true;
		else
			return false;
	}
}
