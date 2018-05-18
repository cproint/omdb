package com.stem.qa.automation.util;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static org.testng.Assert.fail;

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

		public static JSONObject getMovieDetailsByIMDBId(String movieId) {
			
			logger.info("Running "+ new Exception().getStackTrace()[0].getMethodName());
			
			response = givenContentTypeAndAPIKey().params("i", movieId)
							.when().get(baseURI());
			
			//validate response code
			if (response.statusCode() == 200) {
				//Convert response as json object	
				jsonObject = new JSONObject(response.asString());
				//Validate some contents of response object
				//assertTrue(jsonObject.getInt("count") == 1);
				//assertTrue(jsonObject.getJSONArray("results").getJSONObject(0).getString("name").equals("R2-D2"));
				
				// Extract the chained variables 'secondEndpointURL and thirdEndpointURL' to validate in other Test Cases
				//secondEndpointURL = jsonObject.getJSONArray("results").getJSONObject(0).getString("homeworld");
				//thirdEndpointURL = jsonObject.getJSONArray("results").getJSONObject(0).getJSONArray("films").get(0).toString();
				logger.info("Completed: "+ new Exception().getStackTrace()[0].getMethodName());

			} else {
				logger.info("Completed with Exception: "+ new Exception().getStackTrace()[0].getMethodName());
				fail("This method is failed with response code: "+ response.statusCode() + " and with error message: " + response.asString());
			}
			return jsonObject;

		}

		
	    @Test(priority = 1, enabled = false, groups = { "restapi" })
		public void test()  {
		}
	    
}

