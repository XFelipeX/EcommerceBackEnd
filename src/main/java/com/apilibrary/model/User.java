package com.apilibrary.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_account")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	@NotBlank
	private String email;
	@NotNull
	@NotBlank
	@Length(min = 5)
	private String userName;
	@Length(min = 3)
	@NotNull
	@NotBlank
	private String userPassword;
	@Min(0)
	@Max(2)
	private int typeAccount;
	@Min(0)
	@Max(1)
	private int status;
	private int accountId;
}
