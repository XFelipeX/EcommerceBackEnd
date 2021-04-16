package com.apilibrary.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.apilibrary.model.Account;
import com.apilibrary.repository.AccountRepository;

@Service
public class AccountService {
		
	@Autowired
	private AccountRepository repository;
	
	public Account saveAccount(Account account) {
		if(verifyExistCpf(account.getCpf())) return null;
		return repository.save(account);
	}
	
	public Page<Account> listAllAccounts(Pageable pageable){
		return repository.findAll(pageable);
	}
	
	public Account getAccountById(int id) {
		return repository.findById(id).orElse(null);
	}
	
	public String deleteAccount(int id) {
		repository.deleteById(id);
		return "account deleted";
	}
	
	public Account updateAccount(Account account) {
		if(verifyExistCpf(account.getCpf())) return null;
		Account existingAccount = repository.findById(account.getId()).orElse(null);
		existingAccount.setUserName(account.getUserName());
		existingAccount.setLastName(account.getLastName());
		existingAccount.setCep(account.getCep());
		existingAccount.setStreet(account.getStreet());
		existingAccount.setNumberIdent(account.getNumberIdent());
		existingAccount.setCpf(account.getCpf());
		
		return repository.save(existingAccount);
	}
	
	private boolean verifyExistCpf(String cpf) {
		// verify if cpf exist on database
		Account userExist = repository.findAccountByCpf(cpf);
		if (userExist != null)
			return true;
		else
			return false;
	}
}
