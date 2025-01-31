package org.demoBankingApp.utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.demoBankingApp.pages.AccountsOverviewPage;
import org.demoBankingApp.pages.ActivityPage;
import org.demoBankingApp.pages.AdminPage;
import org.demoBankingApp.pages.BillPayPage;
import org.demoBankingApp.pages.FindTransPage;
import org.demoBankingApp.pages.HomePage;
import org.demoBankingApp.pages.OpenAccountPage;
import org.demoBankingApp.pages.RegisterPage;
import org.demoBankingApp.pages.TransactionsPage;
import org.demoBankingApp.pages.TransferPage;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AppUtilities {
	public static WebDriver driver; 
	public static WebDriverWait wait;

	public static Logger logger = LogManager.getLogger("DemoStoreTestng");
	public static ReadConfig config = new ReadConfig();
		
	public static HomePage homePage;
	public static AccountsOverviewPage accountsOverviewPage;
	public static OpenAccountPage openAccountPage;
	public static ActivityPage activityPage;
	public static TransactionsPage transactionsPage;
	public static AdminPage adminPage;
	public static RegisterPage registerPage;
	public static TransferPage transferPage;
	public static FindTransPage findTransPage;
	public static BillPayPage billPayPage;
	
	
	public String getRandomString() {
		return(RandomStringUtils.randomAlphabetic(5));
	}
	
	public String getRandomPhoneNum() {
		return(RandomStringUtils.randomNumeric(10));
	}
	
	public String getRandomNum() {
		return(RandomStringUtils.randomNumeric(6));
	}
	
	public double getTotalAmountNum(String total) {
		String numstr = total.substring(1);
		double num = Double.parseDouble(numstr);
		return num;
	}
	
	public void captureScreenshot() throws IOException {
		if(driver!= null) {
			TakesScreenshot screenshot = ((TakesScreenshot)driver);
			
			File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
			
			File destFile = new File(System.getProperty("user.dir") + "//Screenshots//test.png");
			
			FileUtils.copyFile(srcFile, destFile);
			logger.info("screenshot captured");
		}
	}
	
}

