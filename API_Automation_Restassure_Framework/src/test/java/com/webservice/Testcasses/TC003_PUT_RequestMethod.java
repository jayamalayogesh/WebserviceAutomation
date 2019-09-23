package com.webservice.Testcasses;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.webservice.base.Testbase;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;

public class TC003_PUT_RequestMethod extends Testbase{
	
	static String responsebody;
	@BeforeClass
	public void putrequest() throws InterruptedException
	{
		RestAssured.baseURI="https://reqres.in";
		
		httpRequest=RestAssured.given();
		
		JSONObject requestparam=new JSONObject();
		
		requestparam.put("name", "dhyan");
		requestparam.put("job", "QA manager");
		
		httpRequest.header("Content-Type","text/plain");
		
		httpRequest.body(requestparam.toJSONString());
		
		response=httpRequest.request(Method.PUT,"/api/users/2");
		Thread.sleep(5000);
	}
	@Test(priority=1)
	public void getbody()
	{
		responsebody=response.getBody().asString();
		logger.info("Response body is "+responsebody );
	}
	@Test(priority=2)
	public void responsecode()
	{
		int responsecode=response.getStatusCode();
		logger.info("Response code is "+responsecode);
		
		Assert.assertEquals(responsecode, 200);
	}
	@Test(priority=3)
	public void getdatafromresponsebody()
	{
		JsonPath pathevaluator=response.jsonPath();
		
		String updatedstatus=pathevaluator.get("updatedAt");
		
		logger.info("Updated status is ---->"+updatedstatus);
	}
	@Test(priority=4)
    public void getallheader()
	{
		Headers allheaders=response.headers();
		for(Header header: allheaders)
		{
			System.out.println(header.getName()+"-----"+header.getValue());
		}
	}
	@AfterClass
	public void teardown()
	{
		logger.info("Delete request completed");
	}

}
