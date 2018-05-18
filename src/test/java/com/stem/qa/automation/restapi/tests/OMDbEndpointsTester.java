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
		public void testSearchMovieDetailsEndpointByIMDBId() {
			
			logger.info("Starting Test Case "+ new Exception().getStackTrace()[0].getMethodName());
			
			jsonObject = getMovieDetailsByIMDBId("tt3896198");
			System.out.println(jsonObject.getString("Title"));
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
