package com.spring.domain;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class BoardEntity {
	private int id;
	private String title;
	private String content;
	private String name;
	private String reg_date; 
    private String up_date;
    
    //page_nation
    private int totalDataCount;
	
    public BoardEntity() {
		
	}
}
