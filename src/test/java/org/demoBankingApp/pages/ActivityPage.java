package org.demoBankingApp.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ActivityPage extends BasePage {

	public ActivityPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(css = "div[id='accountDetails'] h1[class='title']")
	WebElement hdingElement;
		
	@FindBy(id = "newAccountId")
	WebElement vtxtAccId;

	@FindBy(id = "accountType")
	WebElement vtxtAccType;
	
	@FindBy(id = "balance")
	WebElement vtxtAccBal;
	
	@FindBy(id = "availableBalance")
	WebElement vtxtAccAvlBal;

	@FindBy(xpath = "//table[@id='transactionTable']//tbody//tr//td[2]")
	WebElement vtxtAccCredit;
	
	@FindBy(xpath = "//table[@id='transactionTable']//tbody//tr//td[2]")
	WebElement vtxtAccDebit;
	
	@FindBy(xpath = "//table[@id='transactionTable']//tbody//tr//td[2]")
	WebElement lnkAccTrnsctns;
	
	@FindBy(xpath = "//table[@id='transactionTable']//tbody//tr//td[2]")
	WebElement vtxtAccDate;

	@FindBy(xpath = "//table[@id='transactionTable']/tbody/tr")
	List<WebElement> tblTrnRows;
		
	@FindBy(xpath = "//table[@id='transactionTable']/thead//th")
	List<WebElement> tblTrnColHds;
	
	public String getPageHeading() {
		return hdingElement.getText();
	}
	
	
	public String getAccountId() {
		String accId = vtxtAccId.getText();
		return accId;
	}
	
	public String getAccountType() {
		String accType = vtxtAccType.getText();
		return accType;
	}

	public String getAccountBal() {
		String accBal = vtxtAccBal.getText();
		return accBal;
	}

	public String getAccountAvlBal() {
		String accAvlBal = vtxtAccAvlBal.getText();
		return accAvlBal;
	}
	
	public String getAccountCredit() {
		String accCredit = vtxtAccCredit.getText();
		return accCredit;
	}

	public String getAccountDebit() {
		String accDebit = vtxtAccDebit.getText();
		return accDebit;
	}
	
	public String getAccountDate() {
		String accDate = vtxtAccDate.getText();
		return accDate;
	}
	
	public void clickAccTransactions() {
		lnkAccTrnsctns.click();
	}
	
	public int getColSize() {
		return tblTrnColHds.size();
	}

	public int getRowSize() {
		return tblTrnRows.size();
	}

	public String getColData(int row, int col) {
		return tblTrnRows.get(row).findElements(By.xpath("td")).get(col).getText();
	}
	
	public void clickColFundTransferLink(int row, int col) {
		tblTrnRows.get(row).findElements(By.xpath("td")).get(col).click();;
	}
	
}
