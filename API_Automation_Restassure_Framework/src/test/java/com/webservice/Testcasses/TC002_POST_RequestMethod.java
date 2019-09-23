package com.webservice.Testcasses;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.gson.JsonObject;
import com.webservice.base.Testbase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ResponseBody;

public class TC002_POST_RequestMethod extends Testbase{
	
	static String responsebody;
	@BeforeClass
	public void createemployee() throws InterruptedException
	{
		
		logger.info("Create the data with request");
		RestAssured.baseURI="https://reqres.in";
		
		httpRequest=RestAssured.given();
		
		//create the request
		JSONObject reqparam= new JSONObject();
		
		reqparam.put("name","jayamalap");
		reqparam.put("job","QA");
		
		//add the content type to header
		httpRequest.header("Content-Type" ,"text/plain");
		
		//add the request to header
		httpRequest.body(reqparam.toJSONString());
		
		//post the request
		response=httpRequest.request(Method.POST,"/api/users");
		
		Thread.sleep(5000);
		
		
	}
	
	@Test(priority=1)
	public void responsebody()
	{
		logger.info("Getting response body");
		 responsebody=response.getBody().asString();
		logger.info(responsebody);
	}
	
	@Test(priority=2)
	public void checkresponsecode()
	{
		int responsecode=response.getStatusCode();
		logger.info("ststus code is --->"+responsecode );
		Assert.assertEquals(responsecode, 201);
	}
	
	@Test(priority=3)
	public void responsebodyvalues()
	{
		boolean id=responsebody.contains("id");
		System.out.println(id);
		System.out.println(responsebody);
	Assert.assertTrue(responsebody.contains("id"));
	Assert.assertTrue(responsebody.contains("createdAt"));
	}
	@Test(priority=4)
	public void getvaluesonebyone()
	{
		JsonPath pathevaluator=response.jsonPath();
		
		System.out.println("id is---->"+pathevaluator.get("id"));
		System.out.println("CreatedAt is----->"+pathevaluator.get("createdAt"));
	}
	@AfterClass
	public void teardown()
	{
		logger.info("Create request is completed");
	}

}
