package com.spring.persistence;

import com.spring.domain.UserDTO;


public interface UserRepository {

	public int updateUserInfo(UserDTO dto);

	public int deleteUser(UserDTO user);

	public void insertUserImg(UserDTO user);

	public void deleteUserImg(UserDTO dto);

}