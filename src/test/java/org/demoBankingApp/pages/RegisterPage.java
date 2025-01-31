package org.demoBankingApp.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage extends BasePage {

	public RegisterPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(className = "title")
	WebElement vtxtHdingMsg;
	
	@FindBy(xpath = "//input[@id='customer.firstName']")
	WebElement txtFirstnname;
	
	@FindBy(xpath = "//input[@id='customer.lastName']")
	WebElement txtLastnname;
	
	@FindBy(xpath = "//input[@id='customer.address.street']")
	WebElement txtAddress;
	
	@FindBy(xpath = "//input[@id='customer.address.city']")
	WebElement txtCity;
	
	@FindBy(xpath = "//input[@id='customer.address.state']")
	WebElement txtState;
	
	@FindBy(xpath = "//input[@id='customer.address.zipCode']")
	WebElement txtZipcode;
	
	@FindBy(xpath = "//input[@id='customer.phoneNumber']")
	WebElement txtPhone;
	
	@FindBy(xpath = "//input[@id='customer.ssn']")
	WebElement txtSsn;
	
	@FindBy(xpath = "//input[@id='customer.username']")
	WebElement txtUsername;
	
	@FindBy(xpath = "//input[@id='customer.password']")
	WebElement txtPsd;
	
	@FindBy(xpath = "//input[@id='repeatedPassword']")
	WebElement txtConfirmpsd;
	
	@FindBy(xpath = "//input[@value='Register']")
	WebElement btnReg;
	
	@FindBy(xpath = "//a[normalize-space()='Log Out']")
	WebElement btnlogOut;
	
	public void setFirstName(String fn) {
		txtFirstnname.clear();
		txtFirstnname.sendKeys(fn);
	}
	
	public void setLastName(String ln) {
		txtLastnname.clear();
		txtLastnname.sendKeys(ln);
	}
	
	public void setAddress(String add) {
		txtAddress.clear();
		txtAddress.sendKeys(add);
	}
	
	public void setCity(String city) {
		txtCity.clear();
		txtCity.sendKeys(city);
	}
	
	public void setState(String state) {
		txtState.clear();
		txtState.sendKeys(state);
	}
	
	public void setZipcode(String zip) {
		txtZipcode.clear();
		txtZipcode.sendKeys(zip);
	}

	public void setPhone(String phone) {
		txtPhone.clear();
		txtPhone.sendKeys(phone);
	}
	
	public void setSsn(String ssn) {
		txtSsn.clear();
		txtSsn.sendKeys(ssn);
	}
	
	public void setUserName(String un) {
		txtUsername.clear();
		txtUsername.sendKeys(un);
	}
	
	public void setPassword(String psd) {
		txtPsd.clear();
		txtPsd.sendKeys(psd);
	}
	
	public void setConfirmPsd(String psd) {
		txtConfirmpsd.clear();
		txtConfirmpsd.sendKeys(psd);
	}
	
	public void clickRegister() {
		btnReg.click();
	}

	public String getMessage() {
		return vtxtHdingMsg.getText();
	}
	
	public boolean isMessageVisible() {
		return vtxtHdingMsg.isDisplayed();
	}
	
	public void clickLogout() {
		btnlogOut.click();
	}
}


