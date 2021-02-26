package com.runner;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;


@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/", 
					glue = {"com.stepDefinition"},
					plugin = { "pretty", "html:target/cucumber-reports" },
					monochrome = true,
					tags = {"@backend,@storefront"}
        )

public class RunTest {
	
	@AfterClass
	 public static void writeExtentReport() {
	 }
	
}


