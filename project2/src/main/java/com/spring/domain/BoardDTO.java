package com.spring.domain;

import lombok.Data;

@Data
public class BoardDTO extends BoardEntity{
	private String message;
	
	@Override
	public String toString(){
		String status = super.toString();
		
		return status + ", message= " + message;
	}
}
