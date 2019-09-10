package com.webservice.Testcasses;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.webservice.base.Testbase;

import io.restassured.RestAssured;
import io.restassured.http.Method;

/******************************************************
Test Name:Get all employees data
URI: http://dummy.restapiexample.com/api/v1/employees
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

public class TC001_GET_GET_AllEmployee extends Testbase {

	@BeforeClass
	public void getAllemployee() throws InterruptedException
	{
		logger.info("*****Get all employee started******");
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		httpRequest=RestAssured.given();
		
		response=httpRequest.request(Method.GET, "/employees");
		
		Thread.sleep(5000);
	}
	
	@Test(priority=1)
	public void getstatuscode()
	{
		logger.info("******Status code verification started**************");
		
		int statuscode=response.getStatusCode();
		logger.info("status code is"+statuscode);
		Assert.assertEquals(statuscode, 200);
	}
	
	@Test(priority=2)
	public void verifystatusline()
	{
		logger.info("*********Status line verification started************");
		String statusline=response.getStatusLine();
		logger.info("status line is"+statusline);
		Assert.assertEquals(statusline, "HTTP/1.1 200 OK");
	}
	
	@Test(priority=3)
	public void statusbody()
	{
		logger.info("*********Response body displyed is************");	
		
		String responsebody=response.getBody().asString();
		logger.info("response body is"+responsebody);
	}
	
	@Test(priority=4)
	public void getcontenttype()
	{
		logger.info("****Get content type excution started****");
		
		String getcontenttype=response.getHeader("Content-Type");
		logger.info("content type  is"+getcontenttype);
		Assert.assertEquals(getcontenttype, "text/html; charset=UTF-8");

	}
	
	@Test(priority=5)
	public void getcontentencoding()
	{
		logger.info("****Get contentencoding  excution started****");
		String getencoding=response.getHeader("Content-Encoding");
		logger.info("content type  is"+getencoding);
		Assert.assertEquals(getencoding, "gzip");

	}
	
	@Test(priority=6)
	public void getcontentlength()
	{
		logger.info("****Get content length  excution started****");
		String contentlength=response.getHeader("Content-Length");
		logger.info("content type  is"+contentlength);
		if(Integer.parseInt(contentlength)>800)
		{
			logger.warn("Content length is more");
		}
		Assert.assertTrue(Integer.parseInt(contentlength)<700);
	}
	
	@Test(priority=8)
	public void getcookie()
	{
		logger.info("****Get coockie details****");
		String cookie=response.getCookie("PHPSESSID");
		logger.info("cookie detail is:"+cookie);
		
	}
	@AfterClass
	public void teardown()
	{
		logger.info("********Get all employee data execution finished**************");
	}
	
}
