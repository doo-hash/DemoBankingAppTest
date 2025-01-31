package org.demoBankingApp.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class TransferPage extends BasePage {

	public TransferPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(css = "div[id='showForm'] h1[class='title']")
	WebElement hdingElement;

	@FindBy(xpath = "//div[@id='showResult']/p[1]")
	WebElement vtxtMsg;
	
	@FindBy(id = "amount")
	WebElement txtAmnt;
	
	@FindBy(id = "fromAccountId")
	WebElement slctFrmAcc;
	
	@FindBy(id = "toAccountId")
	WebElement slctToAcc;
	
	@FindBy(xpath = "//input[@value='Transfer']")
	WebElement btnTrnsferAcc;
	
	@FindBy(xpath = "//a[text()='Accounts Overview']")
	WebElement lnkAccOvrvw;
	
	@FindBy(xpath = "//a[text()='Transfer Funds']")
	WebElement lnkTrnsfrfnds;
	
	public String getPageHeading() {
		return hdingElement.getText();
	}
	
	public void setAmount(String amount) {
		txtAmnt.sendKeys(amount);
	}
	
	public void setFromAccount(String acc) {
		Select selectFrmAcc = new Select(slctFrmAcc);
		selectFrmAcc.selectByVisibleText(acc);
	}
	
	public void setToAccount(String acc) {
		Select selectToAcc = new Select(slctToAcc);
		selectToAcc.selectByVisibleText(acc);
	}
	
	public void clickTransfer() {
		btnTrnsferAcc.click();
	}
	
	public String getFrmAccId() {
		Select selectFrmAcc = new Select(slctFrmAcc);
		String accId = selectFrmAcc.getAllSelectedOptions().get(0).getText();
		return accId;
	}
	
	public String getToAccId() {
		Select selectToAcc = new Select(slctToAcc);
		String accId = selectToAcc.getAllSelectedOptions().get(0).getText();
		return accId;
	}
	
	public void clickAccOverviewLink() {
		lnkAccOvrvw.click();
	}
	
	public void clickTransferFunds() {
		lnkTrnsfrfnds.click();
	}
	
	public boolean checkMessage(double amount, String fromAcc, String toAcc) {
		return vtxtMsg.getText().equals("$"+amount+" has been transferred from account #"+fromAcc+" to account #"+toAcc+".");
	}
}
