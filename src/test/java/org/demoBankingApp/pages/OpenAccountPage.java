package org.demoBankingApp.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class OpenAccountPage extends BasePage {

	public OpenAccountPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//div[@id='openAccountResult']//h1[@class='title']")
	WebElement hdingElement;
	
	@FindBy(id = "type")
	WebElement slctAccTypeEle;
	
	@FindBy(id = "fromAccountId")
	WebElement slctAccIdEle;
	
	@FindBy(xpath = "//input[@value='Open New Account']")
	WebElement btnOpenAcc;
	
	@FindBy(id = "newAccountId")
	WebElement lnkAccId;

	@FindBy(xpath = "//a[text()='Transfer Funds']")
	WebElement lnkTrnsfrfnds;
	
	@FindBy(xpath = "//a[text()='Accounts Overview']")
	WebElement lnkAccOvrvw;
	
	public String getPageHeading() {
		return hdingElement.getText();
	}
	
	public void setAccountType(String type) {
		Select selectAccType = new Select(slctAccTypeEle);
		selectAccType.selectByVisibleText(type);
	}
	
	public String setAccountId() {
		Select selectAccType = new Select(slctAccIdEle);
		selectAccType.selectByIndex(0);
		String accId = selectAccType.getAllSelectedOptions().get(0).getText();
		return accId;
	}
	
	public void clickOpenAcc() {
		btnOpenAcc.click();
	}
	
	public String getAccId() {
		String accId = lnkAccId.getText();
		return accId;
	}
	
	public void clickAccIdLink() {
		lnkAccId.click();
	}
	
	public void clickTransferFunds() {
		lnkTrnsfrfnds.click();
	}
	
	public void clickAccountsOverview() {
		lnkAccOvrvw.click();
	}
}
