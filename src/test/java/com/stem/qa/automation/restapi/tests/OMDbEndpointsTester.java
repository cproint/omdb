package com.stem.qa.automation.restapi.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.json.JSONObject;
import org.testng.annotations.Test;

import com.stem.qa.automation.util.TestUtil;

/**
 * @author mtulugu
 *
 */

public class OMDbEndpointsTester extends TestUtil {
	
	/**
	 * This Test Case verifies unique movie records by Id, verifies if the list contains
	 * The STEM Journals and Activision: STEM - in the Videogame Industry
	 */
		@Test(priority = 1, enabled = true)		
		public void testTotalUniqueMoviesAndMovieByTitle() {
			
			logger.info("***starting test case "+ new Exception().getStackTrace()[0].getMethodName());			

			assertTrue(searchMovieDetailsByTitle("stem").size() >= 30); 
		    assertTrue(searchMovieDetailsByTitle("stem").containsValue("The STEM Journals"));
		    assertTrue(searchMovieDetailsByTitle("stem").containsValue("Activision: STEM - in the Videogame Industry"));

			logger.info("***completed test case "+ new Exception().getStackTrace()[0].getMethodName());
		}

		/**
		 * This Test Case verifies Release date and director name for 'Activision: STEM - in the Videogame Industry'
		 */
		@Test(priority = 2, enabled = true)		
		public void testMovieDetailsByOMDBId() {
			String movieId;

			logger.info("***starting test case "+ new Exception().getStackTrace()[0].getMethodName());			

			movieId = (String) getMovieIdFromTitle(searchMovieDetailsByTitle("stem"),"Activision: STEM - in the Videogame Industry");
			jsonObject = getMovieDetailsByIMDbId(movieId);

			assertTrue(jsonObject.getString("Released").equals("23 Nov 2010"));
			assertTrue(jsonObject.getString("Director").equals("Mike Feurstein"));

			logger.info("***completed test case "+ new Exception().getStackTrace()[0].getMethodName());

		}
		
		/**
		 * This Test Case verifies if response contains 'Science, Technology, Engineering and Math' 
		 * and length of the movie 'The STEM Journals'
		 */
		@Test(priority = 3, enabled = true)		
		public void testSearchMovieDetailsEndpointByTitle() {
			
			logger.info("***starting test case "+ new Exception().getStackTrace()[0].getMethodName());
			
			response = getMovieDetailsByTitle("The STEM Journals");

			assertTrue(response.asString().contains("Science, Technology, Engineering and Math"));
			assertTrue(new JSONObject(response.asString()).getString("Runtime").equals("22 min"));
	
			logger.info("***completed test case "+ new Exception().getStackTrace()[0].getMethodName());

		}

}
