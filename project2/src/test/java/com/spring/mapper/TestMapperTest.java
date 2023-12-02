//package com.spring.mapper;
//
//import static org.junit.Assert.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.Ignore;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.spring.domain.AccommodationDTO;
//import com.spring.domain.BoardEntity;
//import com.spring.domain.TestDTO;
//import com.spring.domain.UserDTO;
//import com.spring.util.PageMaker;
//
//import lombok.Setter;
//import lombok.extern.log4j.Log4j;
//
//
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
//@Log4j
//public class TestMapperTest {
//	
//	@Setter(onMethod = @__({@Autowired}))
//	ProjectMapper testMapper;
//	
//	@Ignore @Test
//	public void test() {
//		log.info(testMapper.selectTime());
//	}
//	
//	@Ignore @Test
//	public void testSelectOne() {
//		//log.info("여기");
//		int seq = 1;
//		TestDTO testDTO = testMapper.selectOne(seq);
//		log.info(testDTO);
//	}
//	@Ignore @Test
//	public void testSelectRecords() {
//		List<Integer> list = new ArrayList<>();
//		int startSeq = 2, endSeq=4;
//		for(int i=startSeq; i < endSeq+1; i++) {
//			list.add(i);
//		}
//		List<TestDTO> testDTOList = testMapper.selectRecords(list);
//		for(TestDTO dto : testDTOList) {
//			log.info(dto);
//		}
//	}
//	
//	@Ignore
//	@Test
//	public void testSelectAll() {
//		List<AccommodationDTO> testDTOList = testMapper.selectAllAccommodation();
//		for(AccommodationDTO dto : testDTOList) {
//			log.info(dto);
//		}
//	}
//	
//	@Ignore
//	@Test
//	public void testPageNation() {
//		PageMaker pageMaker = new PageMaker();
//		List<BoardEntity> testBoardlist = testMapper.selectBoardByPaging(pageMaker);
//	}
//	
//	@Ignore
//	@Test
//	public void loginTest() {
//		UserDTO user = new UserDTO();
//		user.setUsername("hello");
//		user.setPassword("1234");
//		testMapper.selectUsernameAndPassword(user);
//	}
//}
