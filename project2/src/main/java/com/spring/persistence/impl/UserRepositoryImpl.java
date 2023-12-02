package com.spring.persistence.impl;


import org.springframework.stereotype.Repository;

import com.spring.domain.UserDTO;
import com.spring.mapper.ProjectMapper;
import com.spring.persistence.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository{
	
	//private final TestMapper mapper;
	private final ProjectMapper mapper;

	@Override
	public int updateUserInfo(UserDTO user) {
		return mapper.updateUserInfo(user);
	}

	@Override
	public int deleteUser(UserDTO user) {
		return mapper.deleteUser(user);
	}

	@Override
	public void insertUserImg(UserDTO user) {
		mapper.insertUserImg(user);
	}

	@Override
	public void deleteUserImg(UserDTO dto) {
		mapper.deleteUserImg(dto);
	}


}
