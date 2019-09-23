package com.webservice.Testcasses;

import org.apache.http.HttpRequest;
import org.junit.AfterClass;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.webservice.base.Testbase;

import io.restassured.RestAssured;
import io.restassured.http.Method;

/******************************************************
Test Name:Get a single employee data
URI: http://dummy.restapiexample.com/api/v1/employee/{id}
Request Type: GET
Request Payload(Body): NA
********* Validations **********
Status Code : 200
Status Line : HTTP/1.1 200 OK
Content Type : text/html; charset=UTF-8
Server Type :  nginx/1.14.1
Content Encoding : gzip
Content Length <800
 *********************************************************/
public class TC005_GET_Single_Record extends Testbase {

	@BeforeClass
	public void getsinglerecord()
	{
		RestAssured.baseURI="https://reqres.in";
		httpRequest=RestAssured.given();
		response=httpRequest.request(Method.GET, "/api/unknown/2");
	}
	@Test(priority=1)
	public void getstatuscode()
	{
	logger.info("******Single record execution started****");
	int statuscode=response.getStatusCode();
	logger.info("Status code is" +statuscode);
	Assert.assertEquals(statuscode, 200);
	}
	
	@AfterClass
	public void teardown()
	{
		logger.info("Execution completed");
	}
}
