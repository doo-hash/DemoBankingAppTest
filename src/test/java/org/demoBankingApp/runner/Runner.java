package org.demoBankingApp.runner;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		tags="@all",
		features = "src/test/resources/testFeatures",
		plugin = {
				"pretty",
				"html:target/cucumberReports/htmlReport.html",
				"json:target/cucumberReports/jsonReport.json",
				"junit:target/cucumberReports/xmlReport.xml",
				},
		glue = {
				"org.demoBankingApp.stepDefs",
				"org.demoBankingApp.hooks"
		}
	)
public class Runner extends AbstractTestNGCucumberTests {
	
	@DataProvider(parallel = true)
	public Object[][] scenario(){
		return super.scenarios();
	}
}
