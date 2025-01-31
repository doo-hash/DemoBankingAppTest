package org.demoBankingApp.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminPage extends BasePage {

	public AdminPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(className = "title")
	WebElement vtxthding;
	
	@FindBy(xpath = "//button[normalize-space()='Clean']")
	WebElement btnClean;
	
	@FindBy(xpath = "//button[normalize-space()='Initialize']")
	WebElement btnInit;
	
	@FindBy(xpath = "//input[@value='Startup']")
	WebElement btnStartup;
	
	@FindBy(xpath = "//input[@value='Shutdown']")
	WebElement btnShutDwn;

	@FindBy(xpath = "(//input[@name='accessMode'])[4]")
	WebElement chkJdbc;
	
	@FindBy(xpath = "//input[@id='initialBalance']")
	WebElement txtInitBal;
	
	@FindBy(xpath = "//input[@id='minimumBalance']")
	WebElement txtMinBal;

	@FindBy(xpath = "//input[@value='Submit']")
	WebElement btnsbmt;
	
	@FindBy(xpath = "//div[@id='rightPanel']/p/b")
	WebElement vtxtMsg;
	
	@FindBy(xpath = "//a[normalize-space()='Register']")
	WebElement lnkRegister;
	
	public void clickClean() {
		btnClean.click();
	}
	
	public void clickInitialize() {
		btnInit.click();
	}
	
	public void setJdbc() {
		if(!chkJdbc.isSelected())	
			chkJdbc.click();
	}
	
	public void setInitBal(String bal) {
		txtInitBal.clear();
		txtInitBal.sendKeys(bal);
	}
	
	public void setMinBal(String bal) {
		txtMinBal.clear();
		txtMinBal.sendKeys(bal);
	}
	
	public void clickSubmit() {
		btnsbmt.click();
	}
	
	public boolean isMsgVisible() {
		return vtxtMsg.isDisplayed();
	}
	
	public void clickRegister() {
		lnkRegister.click();
	}
	
	public boolean ishdingVisible() {
		return vtxthding.isDisplayed();
	}
}
