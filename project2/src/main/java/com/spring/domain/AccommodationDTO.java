package com.spring.domain;

import lombok.Data;

@Data
public class AccommodationDTO {
	private int acc_id;
	private int user_id;
	private String name;
	private String category;
	private int price;
	private String location;
//	private	Boolean isReserv;
	private int roomCnt;
	private int toilCnt;
	private String image_name;
}
