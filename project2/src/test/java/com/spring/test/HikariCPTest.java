package com.spring.test;


import java.sql.SQLException;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zaxxer.hikari.HikariDataSource;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j2
public class HikariCPTest {
	
	@Setter(onMethod = @__({@Autowired}))
	private HikariDataSource dataSource;
	
	@Ignore @Test
	public void testSelectTime() {
		log.info("spring-test hikariCP dataSource => " + dataSource);
		try {
			java.sql.Connection conn = dataSource.getConnection();
			log.info("connection -> " + conn);
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
