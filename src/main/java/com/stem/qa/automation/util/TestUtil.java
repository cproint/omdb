package com.stem.qa.automation.util;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.json.JSONArray;
import org.json.JSONObject;

import org.testng.annotations.Test;

import com.stem.qa.automation.base.TestBase;

/**
 * @author mtulugu
 *
 */
public class TestUtil extends TestBase {
	
 
    protected static RequestSpecification request;
    protected static JSONObject jsonObject;
    protected static JSONArray jsonArray;
    protected static Response response;

		public static RequestSpecification givenContentTypeAndAPIKey(){
	        if (request == null) {
	            request = given().contentType("application/json").param("apikey", apiKey());
	        }
	        return request;
	    }
	  
	    public static String baseURI() {
	        return baseURI;
	    }
	    
	    public static String apiKey() {
	        return apiKey;
	    }

	    @Test(priority = 1, enabled = false, groups = { "restapi" })
		public void test()  {
		}
	    
}

