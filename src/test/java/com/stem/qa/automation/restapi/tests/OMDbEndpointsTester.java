package com.stem.qa.automation.restapi.tests;

import static io.restassured.RestAssured.given;

import org.json.JSONObject;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import org.testng.annotations.Test;

import com.stem.qa.automation.util.TestUtil;

public class OMDbEndpointsTester extends TestUtil {
	
		private static String secondEndpointURL = null;
		private static String thirdEndpointURL = null;

		@Test(priority = 1, enabled = true, groups = { "sanity", "regression" })		
		public void testPeopleEndpointBySearchParameter() {
			
			logger.info("Starting Test Case "+ new Exception().getStackTrace()[0].getMethodName());
			
			response = givenContentTypeAndAPIKey().params("i", "tt3896198")
							.when().get(baseURI());
			
			System.out.println(response.asString());
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
				logger.info("Ending Test Case "+ new Exception().getStackTrace()[0].getMethodName());

			} else {
				logger.info("Ending Test Case "+ new Exception().getStackTrace()[0].getMethodName());
				fail("This TestCase is failed with response code: "+ response.statusCode() + " and with error message: " + response.asString());
			}

		}
		
		@Test(priority = 2, enabled = false, groups = { "sanity" })
		public void testHomeworldEndpoint() {
			logger.info("Starting Test Case "+ new Exception().getStackTrace()[0].getMethodName());

			//Got secondEndpointURL from above as chained variable and only status code has been validated 
			given().contentType("application/json")
				.when().get(secondEndpointURL)
					.then().assertThat().statusCode(200);

			logger.info("Ending Test Case "+ new Exception().getStackTrace()[0].getMethodName());
			
	}
	
		@Test(priority = 3, enabled = false, groups = { "sanity" })
		public void testFilmsEndpoint() {
			logger.info("Starting Test Case "+ new Exception().getStackTrace()[0].getMethodName());

			//Got thirdEndpointURL from above as chained variable and only status code has been validated 
			given().contentType("application/json")
				.when().get(thirdEndpointURL)
					.then().assertThat().statusCode(200);
			
			logger.info("Ending Test Case "+ new Exception().getStackTrace()[0].getMethodName());

	}
		
	
		
}
