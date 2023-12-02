package com.spring.persistence;

import com.spring.domain.UserDTO;


public interface LoginRepository {
	
	public UserDTO selectUsernameAndPassword(UserDTO user);

	public int insertUser(UserDTO user);

	public int selectDuplUsername(String username);
}