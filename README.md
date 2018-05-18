# ombd repo contains rest endpoint tests that are designed soley to use/evaluate for QE interview.

*A. Framework / Commmon Infrastrcture*

1) This framework is designed to run common framework for all different type of tests (UI, Backend, and Datavalidation tests). Some of the packages could be common across for all these types of these tests.

2) com.amsarmada.qa.automation.base: TestBase.java loads config file with various key-value properties. Every TestClass extends TestBase.java. 

3). com.amsarmada.qa.automation.base: DriverInit.java loads initializes Selenium Web Driver which is being used in Selenium UI Tests

4). com.amsarmada.qa.automation.config: config.properties defines key-value pairs

5). com.amsarmada.qa.automation.extentreports: ExtentReportListnerNG.java is used to create Extent Report at the end of each Test Run

6). com.amsarmada.qa.automation.retryanalyzer: RetryAnalyzer.java and RetryAnnotationTransformer.java are used to define retry logic (eg: Run every failed testcase n number of times before marking it as FAIL)

7). com.amsarmada.qa.automation.util: DataFromRestAPI.java is used to get data from RestAPI. SubstationDetails.java is being used to return more than one value(eg: more than one Substation related values) using old POJO style. Another way to return multiple values are using Map as defined in TestUtil.java.  

8). com.amsarmada.qa.automation.util: SubstationDetails.java is being used to return more than one value(eg: more than one Substation related values) using old POJO style. Another way to return multiple values are using Map as defined in TestUtil.java.  

9). com.amsarmada.qa.automation.util: TestUtil.java contains common utility methods.  

10). com.amsarmada.qa.automation.retryanalyzer: WebEventListner.java is used for loggin purpose after each click in Selenium UI Tests

*B. Selenium UI Tests:* 

1) These tests are designed using Page Object Model (POM) Pattern where each page of web app is considered as individual Page Object or Page Class

2) All Page Classes for HCP Web App are defined under com.amsarmada.qa.automation.hcp.pages. Each java class under this package refer to each web page as the name indicates

3) Each Page class defines all web elements and associated web actions. eg: For Submit button web element there is clickSubmitButton() method defined

4) Every Page Classes has corresponding Test Class and are defined under com.amsarmada.qa.automation.hcp.tests

5) Every Test Class can be run by right click and run as TestNG or as a suite using testng_hcp_test_suite.xml located under src/main/resources folder

6) Every Test Run creates an Extent Test Report file "ExtentReport.html" located under test-output folder

*C. RestAPI / Endpoint Tests:* 

1) These tests are designed using RestAssured framework

2) All endpoints relevant to each resource is created as separate java class. For example, all ServiceAccount endpoints are grouped in ServerAccountEndpoint.java class 

3) All endpoint testers are defined under com.amsarmada.qa.automation.restapi.tests

4) Every Test Class can be run by right click and run as TestNG or as a suite using testng_restapi_test_suite.xml located under src/main/resources folder

5) Every Test Run creates an Extent Test Report file "ExtentReport.html" located under test-output folder

*D. Cucumber BDD Tests:* 

1) The goal of Cucubmber BDD Tests are to run smoke/sanity tests on rest endpoints. These are sanity tests are defined using Cucumber because it will be useful as foundation for working in the Scrum Team where all tests should be in the format of Given-When-Then format where feature files can be written by Product Managers or Product Owners and implementations (Step Definions) can be implemented by QA Engineers.

2) All feature files are located under com.amsarmada.qa.automation.cucumber.bdd.features

3) All StepDefinion files are located under com.amsarmada.qa.automation.cucumber.bdd.stepdefinitions

4) All StepDefinion files are located under com.amsarmada.qa.automation.cucumber.bdd.testrunners

5) Every Test Class can be run by right click either JUnitRunner.java or TestNGRunner.java located under com.amsarmada.qa.automation.cucumber.bdd.testrunners

6) Every Test Run creates an Extent Test Report file "ExtentReport.html" located under test-output folder
