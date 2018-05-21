package com.stem.qa.automation.util;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static org.testng.Assert.fail;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

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
    
    static int totalResultsPerPage;
	static int totalResults;
	static int totalPages;

	static String movieTitle;
	static String movieId;
	static Map<String, String> hmap = new HashMap<>();

	// This method copies movie tile and movie id into hash table
	//Note that key(in this case movie id) is unique
	public static Map<String, String> searchMovieDetailsByTitle(String movieTitleSearchString) {
			
			logger.info("calling utility method "+ new Exception().getStackTrace()[0].getMethodName());		
			response = given().contentType("application/json").params("s",movieTitleSearchString).params("apikey",getAPIKey()).when().get(getBaseURI());
			
			System.out.println(new JSONObject(response.asString()).getString("Response"));
			//validates response code
			//totalResults is total number of records as per search string
			//totalResultsPerPage - max records shown per page. in this case 10
			//Logic of if loop determines number of pages 
			if (response.statusCode() == 200 && new JSONObject(response.asString()).getString("Response").equals("True")) {
				totalResults = Integer.parseInt(new JSONObject(response.asString()).getString("totalResults"));
				totalResultsPerPage = new JSONObject(response.asString()).getJSONArray("Search").length();

				if (totalResults <= 10) {
					totalPages = 1;
				} else if (totalResults % totalResultsPerPage == 0) {
					totalPages = totalResults / totalResultsPerPage; 
				} else {
					totalPages = totalResults / totalResultsPerPage + 1; 
				}
				// Logic of for loop copies all matching movie ids and movie titles
				for (int i = 1; i <= totalPages; i++) {
					response = given().contentType("application/json").when().params("page",i).params("s",movieTitleSearchString).params("apikey",getAPIKey()).when().get(getBaseURI());
					jsonObject = new JSONObject(response.asString());
					totalResultsPerPage = jsonObject.getJSONArray("Search").length();

					for (int j = 0; j < totalResultsPerPage; j++) {
						movieTitle = jsonObject.getJSONArray("Search").getJSONObject(j).get("Title").toString();
						movieId = jsonObject.getJSONArray("Search").getJSONObject(j).get("imdbID").toString();

						hmap.put(movieId, movieTitle);
					
				}
				}

			logger.info("completed utility method: "+ new Exception().getStackTrace()[0].getMethodName());

			} else {
				logger.info("completed/failed utility method with exception: "+ new Exception().getStackTrace()[0].getMethodName());
				fail("This utility method is failed with response code: "+ response.statusCode() + " and with error message: " + response.asString());
			}
			
			return hmap;

		}
		
		//This method gets movie details by movieId and returns JSONObject 
		public static JSONObject getMovieDetailsByIMDbId(String movieId) {
			
			logger.info("calling utility method "+ new Exception().getStackTrace()[0].getMethodName());		
			
			response = given().contentType("application/json").params("i", movieId).params("type", "movie").params("r", "json").params("apikey",getAPIKey())
							.when().get(getBaseURI());
			
			//validate response code
			if (response.statusCode() == 200) {
				//Convert response as json object	
				jsonObject = new JSONObject(response.asString());
				logger.info("completed utility method: "+ new Exception().getStackTrace()[0].getMethodName());

			} else {
				jsonObject = null;
				logger.info("completed/failed utility method with exception: "+ new Exception().getStackTrace()[0].getMethodName());
				fail("This method is failed with response code: "+ response.statusCode() + " and with error message: " + response.asString());
			}
			return jsonObject;

		}
		
		//This method gets movie details by title and returns Response object 
		public static Response getMovieDetailsByTitle(String movieTitle) {
			
			logger.info("calling utility method "+ new Exception().getStackTrace()[0].getMethodName());		
					
			response = given().contentType("application/json").params("t", movieTitle).params("apikey",getAPIKey())
							.when().get(getBaseURI());
			//validate response code
			if (response.statusCode() == 200) {
				logger.info("completed utility method: "+ new Exception().getStackTrace()[0].getMethodName());

			} else {
				response = null;
				logger.info("completed/failed utility method with exception: "+ new Exception().getStackTrace()[0].getMethodName());
				fail("This method is failed with response code: "+ response.statusCode() + " and with error message: " + response.asString());
			}
			return response;

		}

		// This is utility method to retun key from value
		  public static Object getMovieIdFromTitle(Map<?, ?> hm, Object value) {
			    for (Object o : hm.keySet()) {
			      if (hm.get(o).equals(value)) {
			        return o;
			      }
			    }
			    return null;
		  }
}

