package com.spring.persistence.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.domain.BoardDTO;
import com.spring.domain.BoardEntity;
import com.spring.domain.UserDTO;
import com.spring.mapper.ProjectMapper;
import com.spring.persistence.LoginRepository;
import com.spring.util.PageMaker;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Repository
@RequiredArgsConstructor
public class LoginRepositoryImpl implements LoginRepository{
	
	//private final TestMapper mapper;
	private final ProjectMapper mapper;
	
	
	@Override
	public UserDTO selectUsernameAndPassword(UserDTO user) {
		log.info("repository() --------------- username : "+user.getUsername());
		return mapper.selectUsernameAndPassword(user);
	}


	@Override
	public int insertUser(UserDTO user) {
		return mapper.insertUser(user);
	}


	@Override
	public int selectDuplUsername(String username) {
		return mapper.selectDuplUsername(username);
	}



}
