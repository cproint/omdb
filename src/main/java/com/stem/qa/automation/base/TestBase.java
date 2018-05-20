package com.stem.qa.automation.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class TestBase {
	
	protected static final Logger logger = Logger.getLogger(TestBase.class.getName());
	
	public static Properties prop;

	protected static String baseURI;
	protected static String apiKey;
	
	
	public TestBase() {

		prop = new Properties();
		try {
			FileInputStream configFile = new FileInputStream (System.getProperty("user.dir") + "/src/main/java/com/stem/qa/automation/config/config.properties");
			prop.load(configFile);
			String log4jConfPath = System.getProperty("user.dir")+"/src/main/resources/log4j.properties";
			PropertyConfigurator.configure(log4jConfPath);
	
		} catch (FileNotFoundException e) {
	
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		
        // Read environment variable from property file
		baseURI = prop.getProperty("baseURI");
		apiKey = prop.getProperty("apiKey");

	}
    public static String getBaseURI() {
        return baseURI;
    }
    
    public static String getAPIKey() {
        return apiKey;
    }
}
