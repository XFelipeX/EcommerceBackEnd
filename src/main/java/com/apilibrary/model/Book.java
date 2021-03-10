package com.apilibrary.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.validation.constraints.NotBlank;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "book")
public class Book {
	@Id
	@GeneratedValue()
	private int id;
	@NotBlank
	@NotNull
	@Length(min = 3)
	private String nameBook;
	@NotBlank(message = "{name.not.blank}")
	@NotNull
	private String description;
	private int isbn;
	private int stars;
	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate publishDate;
	@NotBlank
	@NotNull
	private String category;
	private float price;
	private int amount;
	private int available;
	private int authorId;
	private int publishCompanyId;
}
