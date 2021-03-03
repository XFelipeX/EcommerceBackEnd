package com.apilibrary.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "image_book")
public class ImageBook {
	@Id
	@GeneratedValue
	private int id;
	@NotNull
	private byte[] img;
	@Max(1)
	private int main;
	private int bookId;
	private String base;
}
