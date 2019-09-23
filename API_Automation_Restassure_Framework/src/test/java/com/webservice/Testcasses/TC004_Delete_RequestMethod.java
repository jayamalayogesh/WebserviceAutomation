package com.webservice.Testcasses;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.webservice.base.Testbase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import junit.framework.Assert;

public class TC004_Delete_RequestMethod extends Testbase{

	@BeforeClass
	public void deleterequest()
	{
		RestAssured.baseURI="https://reqres.in";
		httpRequest=RestAssured.given();
		response=httpRequest.request(Method.DELETE,"/api/users/2");
	}
	
	@Test(priority=1)
	public void checkstatuscode()
{
	int ststauscode=response.getStatusCode();
	Assert.assertEquals(ststauscode, 204);
}
	@Test(priority=2)
	public void responsebody()
	{
		String responsebody=response.getBody().asString();
		System.out.println("Response body is "+responsebody);
		Assert.assertEquals(responsebody, "");
	}
	@AfterClass
	public void teardown()
	{
		logger.info("**************Its terminated************");
	}
}
