package org.demoBankingApp.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountsOverviewPage extends BasePage {

	public AccountsOverviewPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//h1[normalize-space()='Accounts Overview']")
	WebElement hdingElement;
	
	@FindBy(xpath = "//a[text()='Open New Account']")
	WebElement lnkOpenNewAcc;
	
	@FindBy(xpath = "//a[text()='Transfer Funds']")
	WebElement lnkTrnsfrFnds;

	@FindBy(xpath = "//a[text()='Find Transactions']")
	WebElement lnkFindTrnsan;

	@FindBy(xpath = "//a[text()='Bill Pay']")
	WebElement lnkBillPay;
	
	@FindBy(xpath = "//table[@id='accountTable']/tbody/tr")
	List<WebElement> tblAccRows;
	
	@FindBy(xpath = "//table[@id='accountTable']/thead/tr/th")
	List<WebElement> tblAccCols;
	
	@FindBy(xpath = "//table[@id='accountTable']/tbody/tr[last()]/td[2]")
	WebElement tblTotalAmnt;
	
	public String getPageHeading() {
		return hdingElement.getText();
	}
	
	public void clickOpenNewAccLink() {
		lnkOpenNewAcc.click();
	}
	
	public void clickTransferFundsLink() {
		lnkTrnsfrFnds.click();
	}
	
	public void clickFindTransactionsLink() {
		lnkFindTrnsan.click();
	}
	
	public void clickBillPayLink() {
		lnkBillPay.click();
	}
	
	public int getRowSize() {
		return(tblAccRows.size() - 1);
	}
	
	public int getColSize() {
		return(tblAccCols.size());
	}
	
	public String getColHeading(int index) {
		return(tblAccCols.get(index).getText());
	}
	
	public String getColData(int rowIndex,int colIndex){
		return(tblAccRows.get(rowIndex).findElements(By.xpath("td")).get(colIndex).getText());
	}
	
	public void clickColAccLink(int rowIndex,int colIndex){
		tblAccRows.get(rowIndex).findElements(By.xpath("td/a")).get(colIndex).click();
	}
	
	public String getTotalAmount() {
		return tblTotalAmnt.getText();
	}
}
