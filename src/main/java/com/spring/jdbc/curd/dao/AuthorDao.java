package com.spring.jdbc.curd.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.spring.jdbc.curd.model.Author;

@Repository
public class AuthorDao {
	@Autowired
	private JdbcTemplate template;

	public String saveAuthor(Author author) {

		final String sql = "INSERT INTO AUTHORS(id, name, email) VALUES(?, ?, ?)";
		int value = template.update(
				sql,
				new Object[] { author.getId(), author.getName(),
						author.getEmail() });
		if (1 == value)
			return "Author creation is SUCCESS";
		else
			return "Author creation is FAILURE";
	}

	public Author getAuthorById(int id) {
		final String sql = "select * from authors where id=?";
		return template.queryForObject(sql, new Object[] { id },
				new BeanPropertyRowMapper<Author>(Author.class));
	}

	public List<Author> getAllAuthors() {
		final String sql = "SELECT * FROM AUTHORS";
		return template.query(sql, new BeanPropertyRowMapper<Author>(
				Author.class));
	}

	public String deleteAuthor(int id) {
		final String sql = "delete from authors where id=" + id;
		template.execute(sql);
		return "Record Deleted .";
	}
}
