package com.spring.service.imple;

import java.util.List;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.spring.domain.ResultDTO;
import com.spring.domain.UserDTO;
import com.spring.persistence.LoginRepository;
import com.spring.persistence.UserRepository;
import com.spring.service.LoginService;
import com.spring.service.UserService;
import com.spring.util.PageMaker;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImple implements UserService{
	
	//private final BoardRepository repository;
	private final UserRepository userRepository;

	@Override
	public void updateUserInfo(UserDTO user) {
		String message="";
		
		int result = userRepository.updateUserInfo(user);
		if(result > 0) {
			message = "정상적으로 수정되었습니다.";
		}else {
			message=result + "개가 정상적으로 수정되지 않았습니다.";
		}
		user.setMessage(message);
	}

	@Override
	public String removeUser(UserDTO user) {
		String message = "";
		int result = userRepository.deleteUser(user);
		if(result > 0) {
			message = "성공적으로 회원탈퇴가 완료되었습니다.";
		}else {
			message = "회원탈퇴가 완료되지 않았습니다./n다시시도하시기 바랍니다.";
		}
		ResultDTO dto = new ResultDTO();
		dto.setMessage(message);
		dto.setResult(result);
		return new Gson().toJson(dto);
	}

	@Override
	public void setUserImg(UserDTO user) {
		userRepository.insertUserImg(user);
	}

	@Override
	public void removeUserImg(UserDTO dto) {
		userRepository.deleteUserImg(dto);
	}
}
