
## Rest Assured Framework Demo- Assurity consulting

This is a sample Rest API test Hybrid framework for sample endpoints available in https://api.tmsandbox.co.nz/v1/Categories/6327/Details.json?catalogue=false The published APIs represent a get call.

Tests are written using a combination of RestAssured, MAven & testng and Extent Report.
## Tech Stack

Java
Testng
Maven
RestAssured
Extent Report


## Prerequisites
Java 1.8 - Java Dev Kit 
Maven setup - Dependency Manager


## The framework directory structure

src
  + main
    + java                          
      + webservice Base             Base Test class to run all prerequisites before running any Testcase
      + Reports                     Extent reporting utility
      + utilities                   utility methods and constants
src
  + test
    + java                          
      + assertionmethods            Validation methods to test endpoint response
      + wspages
	    +carbonCredit               POM class per module (here carbon credit)
        +wsCommon					JSON response - related constants 
	  + wstestCases
		+wsCarbonCredits            Test cases
      + utils                       Webservice methods (e.g. GET, POST etc.)
      + resources
      + captionbundle				JSON response - .properties file location
+resources
      +Configuration.properties     Environment related - .properties file
	  +endPointDetails.Properties   Endpoints related - .properties file
	  
## Installation and Test Execution

Open the project in any IDE Eclipse/IntelliJ/CMD(on project location). Run the following command in Terminal and build the project. It will automatically download all the required dependencies.

$ mvn clean install




## Running Tests

Run the following command in Terminal to execute tests.

$ mvn clean test


## Test Report

You can find the Serenity reports in the following directory of the Project.

{project path}\extentReports\

In extentReports directory, open latest folder to view 'Report.html' file to view the report.