package com.spring.domain;

import lombok.Data;

@Data
public class UserDTO {
	private int user_id;
	private String username;
	private String password;
	private String age;
	private String phone;
	private String address;
	private String email;
	private String message;
	private String profile_img;
}
