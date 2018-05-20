package com.stem.qa.automation.restapi.tests;

import static org.testng.Assert.assertTrue;

import java.util.ArrayList;

import org.json.JSONObject;
import org.testng.annotations.Test;

import com.stem.qa.automation.util.TestUtil;

public class OMDbEndpointsTester extends TestUtil {
	
	
	ArrayList<String> listOfMovieTitles;

		@Test(priority = 1, enabled = true, groups = { "sanity", "regression" })		
		public void testAllMovieDetailsByTitleString() {
			
			logger.info("Starting Test Case "+ new Exception().getStackTrace()[0].getMethodName());			
			jsonObject = getAllMovieDetailsByTitleString("water");
			
			System.out.println(jsonObject.getJSONArray("Search").getJSONObject(0).get("Title"));
			
/*			listOfMovieTitles = response.path("Search");
			
			listOfMovieTitles.stream().forEach(System.out::println);
			
			jsonObject = new JSONObject(response.asString());

			for (int i = 0; i <= jsonArray.length() - 1; i++) {
				// advanced usage
				if (jsonObject.getJSONObject("values").has("carbon.grid")) {

					carbon_grid_daily = carbon_grid_daily
							+ jsonArray.getJSONObject(i).getJSONObject("values").getDouble("carbon.grid");

				}

			}*/
			
			
			assertTrue(Integer.parseInt(jsonObject.getString("totalResults")) >= 30);
			logger.info("Completed Test Case "+ new Exception().getStackTrace()[0].getMethodName());
		}

		
		@Test(priority = 2, enabled = false, groups = { "sanity", "regression" })		
		public void testSearchMovieDetailsEndpointByIMDBId() {
			
			logger.info("Starting Test Case "+ new Exception().getStackTrace()[0].getMethodName());			
			jsonObject = getMovieDetailsByIMDbId("tt3896198");
			assertTrue("Guardians of the Galaxy Vol. 2".equals(jsonObject.getString("Title")));
			logger.info("Completed Test Case "+ new Exception().getStackTrace()[0].getMethodName());

		}
		
		@Test(priority = 3, enabled = false, groups = { "sanity", "regression" })		
		public void testSearchMovieDetailsEndpointByTitle() {
			
			logger.info("Starting Test Case "+ new Exception().getStackTrace()[0].getMethodName());
			
			jsonObject = getMovieDetailsByTitle("Guardians of the Galaxy Vol. 2");
			assertTrue("Guardians of the Galaxy Vol. 2".equals(jsonObject.getString("Title")));
			logger.info("Completed Test Case "+ new Exception().getStackTrace()[0].getMethodName());

		}

}
