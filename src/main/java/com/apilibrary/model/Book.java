package com.apilibrary.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

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
	@GeneratedValue
	private Integer id;
	private String nameBook;
	private String description;
	private int isbn;
	private int stars;
	private Date publishDate;
	private String category;
	private float price;
	private float amount;
	private int available;
	private int authorId;
	private int publishCompanyId;
}
