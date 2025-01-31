package org.demoBankingApp.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(css = "input[name='username']")
	WebElement txtUsername;
	
	@FindBy(css = "input[name='password']")
	WebElement txtPassword;
	
	@FindBy(css = "input[value='Log In']")
	WebElement btnLogin;
	
	@FindBy(xpath = "//a[normalize-space()='Admin Page']")
	WebElement lnkAdmin;
	
	@FindBy(xpath = "//a[normalize-space()='Register']")
	WebElement lnkRegister;

	
	public void setUsername(String username) {
		txtUsername.sendKeys(username);
	}
	
	public void setPassword(String password) {
		txtPassword.sendKeys(password);
	}
	
	public void clickLoginButton() {
		btnLogin.click();;
	}
	
	public void clickAdmin() {
		lnkAdmin.click();
	}
	
	public void clickRegister() {
		lnkRegister.click();
	}

}
