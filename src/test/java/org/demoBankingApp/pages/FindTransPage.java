package org.demoBankingApp.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class FindTransPage extends BasePage {

	public FindTransPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//div[@id='formContainer']//h1[@class='title']")
	WebElement hdingElement;	
	
	@FindBy(xpath = "//div[@id='resultContainer']//h1[@class='title']")
	WebElement hdingTransRslt;
	
	@FindBy(xpath = "//div[@id='errorContainer']//h1[@class='title']")
	WebElement hdingTransErr;
	
	@FindBy(id = "accountId")
	WebElement slctAcc;
	
	@FindBy(id = "transactionId")
	WebElement txtTranId;
		
	@FindBy(id = "transactionDate")
	WebElement txtTranDate;
	
	@FindBy(id = "fromDate")
	WebElement txtFromDate;
	
	@FindBy(id = "toDate")
	WebElement txtToDate;

	@FindBy(id = "amount")
	WebElement txtTranAmnt;
	
	@FindBy(xpath = "//button[normalize-space()='Find Transactions']")
	List<WebElement> btnFindTran;	

	@FindBy(xpath = "//table[@id='transactionTable']/thead//th")
	List<WebElement> tblTransRowHd;
	
	@FindBy(xpath = "//table[@id='transactionTable']/tbody/tr")
	List<WebElement> tblTransRows;
	
	@FindBy(xpath = "//a[normalize-space()='Log Out']")
	WebElement lnkLogout;
	
	
	public String getPageHeading() {
		return hdingElement.getText();
	}
	
	public void setAccount(String acc) {
		Select selectAcc = new Select(slctAcc);
		selectAcc.selectByVisibleText(acc);
	}
	
	
	public void clickfindTransactions(int index) {
		btnFindTran.get(index).click();
	}

	public void setTransId(String id) {
		txtTranId.sendKeys(id);
	}
	
	public void setFindAmount(String amount) {
		txtTranAmnt.sendKeys(amount);
	}
	
	public void setFindDate(String date) {
		txtTranDate.sendKeys(date);
	}
	
	public void setFromDate(String date) {
		txtFromDate.sendKeys(date);
	}
	
	public void setToDate(String date) {
		txtToDate.sendKeys(date);
	}
	
	public int getRowSize() {
		return tblTransRows.size();
	}

	public int getColSize() {
		return tblTransRowHd.size();
	}

	public String getColData(int row, int col) {
		return tblTransRows.get(row).findElements(By.xpath("td")).get(col).getText();
	}

	public String getTransRsltHding() {
		return hdingTransRslt.getText();
	}
	
	public String getTransError() {
		return hdingTransErr.getText();
	}
	
	public void clickLogout() {
		lnkLogout.click();
	}
	
}
