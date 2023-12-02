package com.spring.service;

import java.util.List;

import com.spring.domain.UserDTO;
import com.spring.util.PageMaker;

public interface UserService {

	public void updateUserInfo(UserDTO dto);

	public String removeUser(UserDTO user);

	public void setUserImg(UserDTO user);

	public void removeUserImg(UserDTO dto);

}
