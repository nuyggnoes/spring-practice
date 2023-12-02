package com.spring.service.imple;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.domain.UserDTO;
import com.spring.persistence.LoginRepository;
import com.spring.service.LoginService;
import com.spring.util.PageMaker;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginServiceImple implements LoginService{
	
	//private final BoardRepository repository;
	private final LoginRepository loginRepository;
	
	@Override
	public UserDTO findUsernameAndPassword(UserDTO user) {
		return loginRepository.selectUsernameAndPassword(user);
	}

	@Override
	public int registerUser(UserDTO user) {
		log.info("service() registerUser -------------------------");
		return loginRepository.insertUser(user);
	}

	@Override
	public boolean checkUsername(String username) {
		log.info("service() checkname -------------------------");
		int result = loginRepository.selectDuplUsername(username);
		if(result == 0) {
			return false;
		}
		return true;
	}
	
	
	
}
