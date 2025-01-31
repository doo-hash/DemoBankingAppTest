package org.demoBankingApp.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TransactionsPage extends BasePage {

	public TransactionsPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(css = "div[id='rightPanel'] h1[class='title']")
	WebElement hdingElement;
		
	@FindBy(css = "tbody tr:nth-child(1) td:nth-child(2)")
	WebElement vtxtAccTranId;

	@FindBy(css = "tbody tr:nth-child(4) td:nth-child(2)")
	WebElement vtxtTranType;
	
	@FindBy(css = "tbody tr:nth-child(5) td:nth-child(2)")
	WebElement vtxtAccTran;
			
	@FindBy(css = "tbody tr:nth-child(2) td:nth-child(2)")
	WebElement vtxtAccDate;

	@FindBy(xpath = "//a[normalize-space()='Log Out']")
	WebElement lnkLogout;

	@FindBy(xpath = "//a[text()='Accounts Overview']")
	WebElement lnkAccOvrvw;

	@FindBy(xpath = "//a[text()='Find Transactions']")
	WebElement lnkFindTrans;

	
	public String getPageHeading() {
		return hdingElement.getText();
	}
	
	
	public String getTransactionId() {
		String accTranId = vtxtAccTranId.getText();
		return accTranId;
	}
	
	public String getAccountTranType() {
		String accTranType = vtxtTranType.getText();
		return accTranType;
	}

	public String getTransactionAmnt() {
		String accBal = vtxtAccTran.getText();
		return accBal;
	}

	public String getAccountTranDate() {
		String accDate = vtxtAccDate.getText();
		return accDate;
	}
	
	public void clickLogout() {
		lnkLogout.click();
	}
	
	public void clickAccOverview() {
		lnkAccOvrvw.click();
	}
	
	
	public void clickFindTransactions() {
		lnkFindTrans.click();
	}
	
}
