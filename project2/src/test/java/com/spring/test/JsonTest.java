package com.spring.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import lombok.extern.log4j.Log4j;

@Log4j
public class JsonTest {
	
	@Test
	public void jsonMade() {
		log.info("jsonMade()");
		
		JsonArray studentList = new JsonArray();
		JsonObject student = new JsonObject();
		student.addProperty("name", "임성균");
		student.addProperty("age", 24);
		studentList.add(student);
		student = new JsonObject();
		student.addProperty("name", "이수연");
		student.addProperty("age", 24);
		studentList.add(student);
		int length = (int) Math.floor(Math.random() * 100);
		
		for(int i = 0; i < length; i++) {
			studentList.add(setStudent());
		}
		
		
		JsonObject jsonClass = new JsonObject();
		
		jsonClass.add("goodStudent", getStudent((int) Math.floor(Math.random() * 100)
													,studentList));
		jsonClass.add("list", studentList);
		
		System.out.println(new Gson().toJson(jsonClass));
	}
	public JsonObject getStudent(int index, JsonArray studentList) {
		
		return studentList.get(index).getAsJsonObject();
	}
	
	public JsonObject setStudent() {
		JsonObject student = new JsonObject();
		
		student.addProperty("name", "Kim-" + Math.round(Math.random()*100));
		student.addProperty("age", Math.round(Math.random()*100));
		return student;
	}
	
}
