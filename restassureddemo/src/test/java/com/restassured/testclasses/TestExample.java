package com.restassured.testclasses;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import net.minidev.json.JSONObject;

import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

public class TestExample {

	@Test
	public void test_1() {
		Response response = get("https://reqres.in/api/users?page=2");

		System.out.println("statuscode :" + response.getStatusCode());
		System.out.println("time :" + response.getTime());

		Assert.assertEquals(response.getStatusCode(), 200);

	}

	@Test
	public void test_2() {
		baseURI = "https://reqres.in/api";

		given().get("/users?page=2").then().statusCode(200).body("data[1].id", equalTo(8)).log().all();

	}
	
	@Test
	public void testGet() {
		baseURI = "https://reqres.in/api";
		
		given().get("/users?page=2").then().statusCode(200).body("data.first_name", hasItems("George","Rachel"));
		
	}
	
	@Test
	public void testPost() {
		
		/*
		 * Map<String , Object> map=new HashMap<>(); map.put("name", "sankar");
		 * map.put("job", "software");
		 */
		
		
		JSONObject jsonobj=new JSONObject();
		jsonobj.put("name", "sankar");
		jsonobj.put("job", "software");
		System.out.println(jsonobj.toJSONString());
		
		baseURI = "https://reqres.in/api";
		
		given().body(jsonobj.toJSONString()).when().post("/users").
		then().statusCode(201).log().all();
		
		
	}

}
