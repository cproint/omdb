package com.stem.qa.automation.util;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static org.testng.Assert.fail;

import java.util.HashMap;
import java.util.Map;

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
    
    static int totalResultsPerPage = 10;
	static int totalResults = 0;
	static int totalPages = 0;

	static String movieTitle;
	static String movieId;
	static Map<String, String> hmap = new HashMap<String, String>();

	
		public static RequestSpecification givenContentTypeAndAPIKey(){
	        if (request == null) {
	            request = given().contentType("application/json").param("apikey", getAPIKey());
	        }
	        return request;
	    }
	  


		public static Map<String, String> getAllMovieDetailsByTitleString(String movieTitleSearchString) {
			

			logger.info("Running "+ new Exception().getStackTrace()[0].getMethodName());		
			response = given().contentType("application/json").params("s",movieTitleSearchString).params("apikey",getAPIKey()).when().get(getBaseURI());
			//validate response code
			if (response.statusCode() == 200) {
				totalResults = Integer.parseInt(new JSONObject(response.asString()).getString("totalResults"));
				if (totalResults == 0) {
					logger.info("No results found");
					return hmap;
				} else if (totalResults <= 10) {
					totalPages = 1;
				} else if (totalResults % totalResultsPerPage == 0) {
					totalPages = totalResults / totalResultsPerPage; 
				} else {
					totalPages = totalResults / totalResultsPerPage + 1; 
				}
			

				for (int i = 1; i <= totalPages; i++) {	
						for (int j = 0; j < totalResultsPerPage; j++) {
							response = given().contentType("application/json").when().params("page",i).params("s",movieTitleSearchString).params("apikey",getAPIKey()).when().get(getBaseURI());
							jsonObject = new JSONObject(response.asString());

					movieTitle = jsonObject.getJSONArray("Search").getJSONObject(j).get("Title").toString();
					movieId = jsonObject.getJSONArray("Search").getJSONObject(j).get("imdbID").toString();
					
					if (movieTitle.equals("The STEM Journals") || movieTitle.equals("Activision: STEM - in the Videogame Industry")) {
					
						hmap.put(movieTitle, movieId);
						System.out.println("movieTitle: " + movieTitle);
						System.out.println("movieId: " + movieId);

						if (hmap.size() == 2) {
							return hmap;
						}
					}
				}

				}

			logger.info("Completed: "+ new Exception().getStackTrace()[0].getMethodName());

			} else {
				logger.info("Completed/Failed "+ new Exception().getStackTrace()[0].getMethodName());
				fail("This method is failed with response code: "+ response.statusCode() + " and with error message: " + response.asString());
			}
			return hmap;

		}
		
		public static JSONObject getMovieDetailsByIMDbId(String movieId) {
			
			logger.info("Running "+ new Exception().getStackTrace()[0].getMethodName());
			
			response = givenContentTypeAndAPIKey().params("i", movieId).params("type", "movie").params("r", "json")
							.when().get(getBaseURI());
			
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

		public static JSONObject getMovieDetailsByTitle(String movieTitle) {
			
			logger.info("Running "+ new Exception().getStackTrace()[0].getMethodName());
			
			response = givenContentTypeAndAPIKey().params("t", movieTitle)
							.when().get(getBaseURI());
			
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




		
	    @Test(priority = 1, enabled = true, groups = { "restapi" })
		public void test()  {
	    	getAllMovieDetailsByTitleString("stem");
		}
	    
}

