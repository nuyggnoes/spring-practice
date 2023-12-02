package com.spring.controller;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;


@Log4j2
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml",
						"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class TestControllerTest {
	
	@Autowired
	private WebApplicationContext ctx;
	private MockMvc mockMvc;
	
	@Before
	public void before() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();

	}
	
	@Ignore @Test
	public void test() {
		log.info("test()");
	}
	
	@Test
	public void testString() throws Exception {
		log.info(mockMvc);
		log.info(
					mockMvc.perform(
							MockMvcRequestBuilders.get("/test/")
											)
								.andReturn()
								.getModelAndView()
								
								//.getModelAndView()
								//.getViewName()
								
				);
	}
}
