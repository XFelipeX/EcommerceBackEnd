package com.apilibrary.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "account_user")
public class Account {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull 
	@NotBlank @Length(min = 5)
	private String userName;
	@NotNull 
	@NotBlank
	private String lastName;
	@NotNull 
	@NotBlank
	private String street;
	private int numberIdent;
	@NotNull 
	@NotBlank
	private String cpf;
	@NotNull 
	@NotBlank
	private String cep;
	
}	
