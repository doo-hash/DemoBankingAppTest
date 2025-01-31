package org.demoBankingApp.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class BillPayPage extends BasePage {

	public BillPayPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//div[@id='billpayForm']//h1[@class='title']")
	WebElement hdingElement;
	
	@FindBy(xpath = "//div[@id='billpayResult']//h1[@class='title']")
	WebElement vtxtRsltMsg;
	
	@FindBy(xpath = "//div[@id='billpayResult']//p[1]")
	WebElement vtxtMsg;
	
	@FindBy(xpath = "//input[@name='payee.name']")
	WebElement txtFirstnname;
		
	@FindBy(xpath = "//input[@name='payee.address.street']")
	WebElement txtAddress;
	
	@FindBy(xpath = "//input[@name='payee.address.city']")
	WebElement txtCity;
	
	@FindBy(xpath = "//input[@name='payee.address.state']")
	WebElement txtState;
	
	@FindBy(xpath = "//input[@name='payee.address.zipCode']")
	WebElement txtZipcode;
	
	@FindBy(xpath = "//input[@name='payee.phoneNumber']")
	WebElement txtPhone;
	
	@FindBy(xpath = "//input[@name='payee.accountNumber']")
	WebElement txtAccount;
	
	@FindBy(xpath = "//input[@name='verifyAccount']")
	WebElement txtVrfyAcc;
	
	@FindBy(xpath = "//input[@name='amount']")
	WebElement txtAmnt;
	
	@FindBy(xpath = "//select[@name='fromAccountId']")
	WebElement slctFrmAcc;
	
	@FindBy(xpath = "//input[@value='Send Payment']")
	WebElement btnSndPymnt;
		
	@FindBy(xpath = "//a[text()='Accounts Overview']")
	WebElement lnkAccOvrvw;
	
	
	public void setName(String fn) {
		txtFirstnname.clear();
		txtFirstnname.sendKeys(fn);
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
	
	public void setAccount(String acc) {
		txtAccount.clear();
		txtAccount.sendKeys(acc);
	}
	
	public void setVerifyAccount(String acc) {
		txtVrfyAcc.clear();
		txtVrfyAcc.sendKeys(acc);
	}
	
	public void setAmount(String amount) {
		txtAmnt.clear();
		txtAmnt.sendKeys(amount);
	}
	
	public void setFromAccount(String acc) {
		Select slctFromAcc = new Select(slctFrmAcc);
		slctFromAcc.selectByVisibleText(acc);
	}
	
	public void clickSendPayment() {
		btnSndPymnt.click();
	}

	public String getHeading() {
		return hdingElement.getText();
	}
	
	public String getHeadingMessage() {
		return vtxtRsltMsg.getText();
	}
	
	public boolean getMessage(String acc, String amount, String payee) {
		return vtxtMsg.getText().equals("Bill Payment to "+payee+" in the amount of $"+ amount +" from account " + acc +" was successful.");
	}
	
	public void clickAccountsOverview() {
		lnkAccOvrvw.click();
	}
}
