package com.spring.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.domain.BoardDTO;
import com.spring.domain.RemoveSeqList;

import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;


@Log4j2
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml",
						"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class BoardControllerTest {
	
	@Autowired
	private WebApplicationContext ctx;
	private MockMvc mockMvc;
	private ObjectMapper objectMapper;
	
	@Before
	public void before() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();

	}
	
	@Ignore @Test
	public void test() {
		log.info("test()");
	}
	
	@Ignore
	@Test
	public void testModify() throws Exception {
		log.info(mockMvc);
		BoardDTO dto = new BoardDTO();
		dto.setTitle("QWER");
		dto.setContent("This is content Test");
		log.info(
					mockMvc.perform(
							MockMvcRequestBuilders.put("/board/read")
								.param("title", String.valueOf(dto.getTitle()))
								.param("content", String.valueOf(dto.getContent()))
							
							
											)
								
								//.getModelAndView()
								//.getViewName()
								
				);
		mockMvc.perform(
				MockMvcRequestBuilders.put("/board/modify")
					.contentType(MediaType.APPLICATION_JSON)
//					.content(content)
				);
		
	}
	
	@Test
	public void testBoardReomveList() throws Exception {
		RemoveSeqList list = new RemoveSeqList();
		list.getList().add(7);
		list.getList().add(10);
		String content = objectMapper.writeValueAsString(list);
		log.info(content);
		mockMvc.perform(
				MockMvcRequestBuilders.put("/board/remove-list")
					.contentType(MediaType.APPLICATION_JSON)
					.content(content)
				)
		// then
		.andExpect(status().isOk())
		.andDo(print());
		
	}
}
