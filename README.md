# ombd public repo contains rest endpoint tests that are designed soley to use/evaluate for Test Automation Engineer interview. 

*copyright: Under NO circumstances should this framework/repo be used as production test code by any company without written consent of Murali Tulugu*

*A. Tech Stack*

Java 8/ TestNG / RestAssured / log4j / Maven /IDE (Eclipse)

*B. Framework / Commmon Infrastrcture*

1) This framework is designed to run rest endpoints

2) com.stem.qa.automation.base: TestBase.java loads config file with various key-value properties and log4j.properties file for logging statements. It also has getter methods.  

3) com.stem.qa.automation.config: config.properties defines key-value pairs

4) com.amsarmada.qa.automation.util: TestUtil.java contains common utility methodsand extends TestBase.java. Utility class contains common utility methods and are being called in test classes.

5) log4j.properties is used as logging framework and is located under src/main/resources
  

*C. RestAPI / Endpoint Tests:* 

1) These rest endpoint tests are designed using RestAssured framework

2) OMDbEndpointsTester.java is defined under com.stem.qa.automation.restapi.tests. This tester contains 3 test cases. 

*D. How to run Tests?* 

Pre-requisites:
1) Clone the http://github.com/cproint/omdb repo
2) Import the above Maven based Java project into any Java IDE (Eclipse or IntelliJ)
3) Note that pom.xml located under root of the project downloads all required libraries
 
3a) To Run: Right click on OMDbEndpointsTester.java (located under com.stem.qa.automation.restapi.tests) and click 'Run As' TestNG Test

 or

3b) To Run: Right click on testng_restapi_test_suite.xml (located under src/main/resources) and click 'Run As' TestNG Suite

*E. How to view Test Reports?* 

1) Every Test Run creates an Test Report file "emailable-report.html" located under test-output folder