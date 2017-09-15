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
	
		// it will return all data from table with column name as key and value as
	// columndata
	public List<Map<String, Object>> useResultSetMetaData() {
		String SQL = "SELECT * FROM AUTHORS";
		return template.queryForList(SQL);

	}

	public List<String> getEmails() {
		String SQL = "Select EMAIL from AUTHORS";
		return template.queryForList(SQL, String.class);
	}
	
       public List<Map<String, Object>> useMetaDataQuery() {
		String SQL = "SELECT * FROM AUTHORS";
		List<Map<String, Object>> recordList = null;
		recordList = template.query(SQL, new RowMapper<Map<String, Object>>() {

			@Override
			public Map<String, Object> mapRow(ResultSet rs, int index)
					throws SQLException {
				Map<String, Object> recordMap = new HashMap<>();
				ResultSetMetaData metaData = rs.getMetaData();
				int columnCount = metaData.getColumnCount();
				for (int i = 1; i <= columnCount; i++) {
					String columnName = metaData.getColumnName(i);
					recordMap.put(columnName, rs.getObject(columnName));
				}
				return recordMap;
			}
		});
		return recordList;
	}
}
