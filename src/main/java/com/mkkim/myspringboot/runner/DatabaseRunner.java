package com.mkkim.myspringboot.runner;

import java.sql.Connection;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class DatabaseRunner implements ApplicationRunner{
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		try(Connection conn = dataSource.getConnection()){
			System.out.println(conn.getMetaData().getURL());
			System.out.println(conn.getMetaData().getUserName());
			
			/*Statement statement = conn.createStatement();
			String sql = "CREATE TABLE USER (ID INTEGER NOT NULL, name VARCHAR(255),PRIMARY\r\nKEY(id))";
			statement.executeUpdate(sql);
			jdbcTemplate.execute("insert into user values(1,'mkkim')");*/
		}
	}
	
	
}
