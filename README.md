
## NUIX SDET DEMO

This is a sample Selenium and java based Hybrid framework for sample calculator operations 
Tests are written using a combination of selenium, java, MAven & testng and Extent Report.
## Tech Stack

Java
Testng
Maven
Extent Report


## Prerequisites
Java 1.8 - Java Dev Kit 
Maven setup - Dependency Manager


	  
## Installation and Test Execution

Open the project in any IDE Eclipse/CMD(on project location). Run the following command in Terminal and build the project. It will automatically download all the required dependencies.

$ Run CalcTest - As TestNG

Run the Following testng file to execute tests.

$ TestNg.xml


## Test Report

You can find the Extent reports in the following directory of the Project.

{project path}\extentReports\

In extentReports directory, open latest folder to view 'Report.html' file to view the report.


## Major issues found on Staging (extent report attached for the same) :

Number 5 takes value as 6.
Multiplication is failing e.g. 7*4 is showing as 30
Devision failing in case odd number is devided by even e.g. 9/2 is showing 5
Any operation with decimal number outputs wrong result.
