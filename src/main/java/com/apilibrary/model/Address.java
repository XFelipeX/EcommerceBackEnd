package com.apilibrary.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "address")
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull @NotBlank
	private String publicArea;
	@NotNull @NotBlank
	private String cep;
	@NotNull @NotBlank
	private String uf;
	@NotNull @NotBlank
	private int number;
	@NotNull @NotBlank
	private String local;
	private String complement;
	@NotNull @NotBlank
	private String type;
	@NotNull @NotBlank
	private int status;
	@NotNull @NotBlank
	private int accountId;
	private int accountUser;
}
