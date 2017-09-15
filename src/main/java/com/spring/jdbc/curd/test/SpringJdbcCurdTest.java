package com.spring.jdbc.curd.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.jdbc.curd.model.Author;
import com.spring.jdbc.curd.service.CurdService;

public class SpringJdbcCurdTest {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"com/spring/jdbc/curd/config/application-context.xml");
		CurdService service = (CurdService) context.getBean("curdService");
		// Save or update operation
		System.out.println(service.save(new Author(444, "Romio",
				"romio2gmail.com")));
		// Retrieve operation with condition
		System.out.println(service.getAuthor(444));
		// Retrieve all records
		System.out.println(service.getAuthors());
		// Delete Record
		System.out.println(service.delete(444));
		
		System.out.println(service.getEmails());
		
		System.out.println(service.getMetaDataCount());
		
		System.out.println(service.useMetaDataQuery());
	}
}
