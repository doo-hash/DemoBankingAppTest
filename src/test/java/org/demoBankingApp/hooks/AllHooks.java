package org.demoBankingApp.hooks;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;

import org.demoBankingApp.utilities.AppUtilities;
import org.demoBankingApp.utilities.ReadConfig;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class AllHooks extends AppUtilities {

	ReadConfig config = new ReadConfig();

	@Before(order = 0)
	public void setup() {
		String browserName = config.getBrowserName();
		ArrayList<String> options = new ArrayList<String>();
		options.add("start-maximized");
		options.add("--headless=new");

		switch (browserName.toLowerCase()) {
			case "chrome":
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.addArguments(options);
				driver = new ChromeDriver(chromeOptions);
				break;
			case "edge":
				EdgeOptions edgeOptions = new EdgeOptions();
				edgeOptions.addArguments(options);
				driver = new EdgeDriver(edgeOptions);
				break;
			case "firefox":
				driver = new FirefoxDriver();
				break;
			default:
				logger.info("Invalid browser name");
				return;
		}

		logger.info("Scenario execution started");
	}

	@Before(order = 1)
	public void urlSetup() {
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	
//	@AfterStep
//	public void stepScreenshot(Scenario scenario) throws IOException {
//		final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
//		scenario.attach(screenshot,"image/png", scenario.getName());
//
//		logger.info("Captured screenshot for step: " + scenario.getName());
//	}
	
	@After(order = 1)
	public void closeWindow(Scenario scenario) throws IOException {
		if(scenario.isFailed()) {
			captureScreenshot();
			logger.debug("Captured screenshot for failed scenario: " + scenario.getName());
		}
		
//		logger.info("Closed browser window");
//		driver.close();
	}

	@After(order = 0)
	public void tearDown() {
		if(driver != null) {
			logger.info("Scenario execution over");
			driver.quit();			
		}
	}
}
