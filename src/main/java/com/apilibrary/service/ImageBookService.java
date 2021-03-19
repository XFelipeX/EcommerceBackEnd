package com.apilibrary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apilibrary.model.ImageBook;
import com.apilibrary.repository.ImageBookRepository;

@Service
public class ImageBookService {
	@Autowired
	private ImageBookRepository repository;

	public ImageBook saveImageBook(ImageBook imageBook) {
		return repository.save(imageBook);
	}

	public List<ImageBook> listImageBook() {
		return repository.findAll();
	}

	public ImageBook getImageBookById(int id) {
		return repository.findById(id).orElse(null);
	}
	
	public ImageBook getMainImageBookById(int id) {
		return repository.findImageBookByBookIdAndMain(id);
	}

	public String deleteImageBook(int id) {
		repository.deleteById(id);

		return "Image deleted";
	}
	
	public List<ImageBook> getAllImagesBook(int id) {
		return repository.findImagesBookByBookId(id);
	}

	public List<ImageBook> getAllImagesBook(int id) {
		return repository.findImagesBookByBookId(id);
	}

	public ImageBook updateImageBook(ImageBook imageBook) {
		ImageBook existingImageBook = repository.findById(imageBook.getId()).orElse(null);
		existingImageBook.setBookId(imageBook.getBookId());
		existingImageBook.setMain(imageBook.getMain());
		existingImageBook.setImg(imageBook.getImg());

		return repository.save(existingImageBook);
	}
}
