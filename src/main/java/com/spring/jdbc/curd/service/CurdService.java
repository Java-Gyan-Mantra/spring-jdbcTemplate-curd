package com.spring.jdbc.curd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.jdbc.curd.dao.AuthorDao;
import com.spring.jdbc.curd.model.Author;

@Service
public class CurdService {
	@Autowired
	private AuthorDao dao;

	public String save(Author author) {
		return dao.saveAuthor(author);
	}

	public Author getAuthor(int id) {
		return dao.getAuthorById(id);
	}

	public List<Author> getAuthors() {
		return dao.getAllAuthors();
	}

	public String delete(int id) {
		return dao.deleteAuthor(id);
	}
}
