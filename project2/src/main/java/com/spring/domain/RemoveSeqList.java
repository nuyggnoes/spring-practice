package com.spring.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class RemoveSeqList {
	private List<Integer> list;
	
	public RemoveSeqList(){
		list = new ArrayList<>();
	}
}
