package com.apilibrary.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "demand")
public class Demand {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Timestamp date;
	private String status;
	@NotNull @NotBlank
	private String payment;
	@NotNull @NotBlank
	private double shipping;
	@NotNull @NotBlank
	private double total;
	private int accountId;
	private int addressId;
	
}
