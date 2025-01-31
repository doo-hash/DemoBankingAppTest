package org.demoBankingApp.stepDefs;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.demoBankingApp.pages.AccountsOverviewPage;
import org.demoBankingApp.pages.ActivityPage;
import org.demoBankingApp.pages.FindTransPage;
import org.demoBankingApp.pages.OpenAccountPage;
import org.demoBankingApp.pages.TransactionsPage;
import org.demoBankingApp.pages.TransferPage;
import org.demoBankingApp.utilities.AppUtilities;
import org.testng.Assert;

import io.cucumber.java.en.Then;

public class TransactionsStepDef extends AppUtilities {

	String newAccId = "";
	String oldAccId = "";
	String TransId = "";
	double newAccBal = 0;
	double oldAccBal = 0;
	double amount = 400.00;
	
	@Then("user checks accounts")
	public void user_checks_accounts() throws InterruptedException {
		accountsOverviewPage = new AccountsOverviewPage(driver);
		openAccountPage = new OpenAccountPage(driver);
		transferPage = new TransferPage(driver);

		int rows = accountsOverviewPage.getRowSize();
		oldAccId = accountsOverviewPage.getColData(0, 0);
		String bal = accountsOverviewPage.getColData(0, 1);
		oldAccBal = getTotalAmountNum(bal);
		
		System.out.println(oldAccBal);
		Assert.assertTrue(oldAccBal == 5000.00);
		System.out.println(oldAccId);
		
		if(rows == 1) {
			accountsOverviewPage.clickOpenNewAccLink();
			Assert.assertTrue(driver.getTitle().equals("ParaBank | Open Account"));
			openAccountPage.setAccountType("SAVINGS");
			openAccountPage.setAccountId();
			openAccountPage.clickOpenAcc();
			Thread.sleep(1000);
			System.out.println("message: "+ openAccountPage.getPageHeading());
			Assert.assertTrue(openAccountPage.getPageHeading().equals("Account Opened!"));
			newAccId = openAccountPage.getAccId();
			System.out.println("new acc id: " + newAccId);
			openAccountPage.clickAccountsOverview();
			Assert.assertTrue(driver.getTitle().equals("ParaBank | Accounts Overview"));
			String accId = accountsOverviewPage.getColData(1, 0);
			System.out.println(accId);
			Assert.assertTrue(accId.equals(newAccId));
			String newbal = accountsOverviewPage.getColData(1, 1);
			newAccBal = getTotalAmountNum(newbal);
			System.out.println(newAccBal);
			Assert.assertTrue(newAccBal == 1000.0);
			String oldbal = accountsOverviewPage.getColData(0, 1);
			oldAccBal = getTotalAmountNum(oldbal);
			System.out.println(oldAccBal);
			Assert.assertTrue(oldAccBal == 4000.0);
		}else {
			newAccId = accountsOverviewPage.getColData(1, 0);
			String newbal = accountsOverviewPage.getColData(1, 1);
			newAccBal = getTotalAmountNum(newbal);
		}
		
		Assert.assertFalse(oldAccId.equals(newAccId));
	}
	
	@Then("user clicks transfer funds link")
	public void user_clicks_transfer_funds_link() {
		openAccountPage.clickTransferFunds();
		Assert.assertTrue(driver.getTitle().equals("ParaBank | Transfer Funds"));		
		Assert.assertEquals("Transfer Funds", transferPage.getPageHeading());
	}

	@Then("user enters amount to transfer {string}")
	public void user_enters_amount_to_transfer(String amount) {
		transferPage.setAmount(amount);		
	}
	
	@Then("user selects accounts and clicks transfer button")
	public void user_selects_accounts_and_clicks_transfer_button() throws InterruptedException {
		transferPage.setFromAccount(oldAccId);
		transferPage.setToAccount(newAccId);
		Assert.assertEquals(oldAccId, transferPage.getFrmAccId());
		Assert.assertEquals(newAccId, transferPage.getToAccId());
		transferPage.clickTransfer();
		Thread.sleep(500);
	}
	
	
	@Then("user checks transfer complete message")
	public void user_checks_transfer_complete_message() {
		transferPage.checkMessage(amount, oldAccId, newAccId);
	}
	
	
	@Then("user checks account balance in accounts overview")
	public void user_checks_account_balance_in_accounts_overview() {
		transferPage.clickAccOverviewLink();
		Assert.assertTrue(driver.getTitle().equals("ParaBank | Accounts Overview"));
		Assert.assertTrue(accountsOverviewPage.getPageHeading().equals("Accounts Overview"));
		
		double oldaccbalc = oldAccBal - amount;
		double newaccBalc = newAccBal + amount;
		
		double actoldAccBal = getTotalAmountNum(accountsOverviewPage.getColData(0, 1));
		double actnewAccBal = getTotalAmountNum(accountsOverviewPage.getColData(1, 1));
		
		Assert.assertEquals(actoldAccBal, oldaccbalc);
		Assert.assertEquals(actnewAccBal, newaccBalc);
	}
	
	@Then("user checks debited account details")
	public void user_checks_debited_account_details() {
		accountsOverviewPage.clickColAccLink(0, 0);
		activityPage = new ActivityPage(driver);
		Assert.assertTrue(driver.getTitle().equals("ParaBank | Account Activity"));
		int rows = activityPage.getRowSize();
		int cols = activityPage.getColSize();
		String amntstr = activityPage.getColData(rows - 1 , cols - 2);
		double debitAmnt = getTotalAmountNum(amntstr);
		Assert.assertEquals(debitAmnt, amount);
		activityPage.clickColFundTransferLink(rows - 1, cols -3);
		Assert.assertTrue(driver.getTitle().equals("ParaBank | Transaction Details"));
		transactionsPage = new TransactionsPage(driver);
		Assert.assertEquals("Transaction Details", transactionsPage.getPageHeading());
		double tranamnt = getTotalAmountNum(transactionsPage.getTransactionAmnt());
		System.out.println(tranamnt);
		System.out.println(amount);
		Assert.assertTrue(tranamnt == amount);
		Assert.assertEquals("Debit", transactionsPage.getAccountTranType());
	}

	@Then("user checks credited account details")
	public void user_checks_credited_account_details() {
		transactionsPage.clickAccOverview();
		accountsOverviewPage.clickColAccLink(1, 0);
		Assert.assertTrue(driver.getTitle().equals("ParaBank | Account Activity"));
		int rows = activityPage.getRowSize();
		int cols = activityPage.getColSize();
		String amntstr = activityPage.getColData(rows - 1 , cols - 1);
		double creditAmnt = getTotalAmountNum(amntstr);
		Assert.assertEquals(creditAmnt, amount);
		activityPage.clickColFundTransferLink(rows - 1, cols -3);
		Assert.assertTrue(driver.getTitle().equals("ParaBank | Transaction Details"));
		transactionsPage = new TransactionsPage(driver);
		Assert.assertEquals("Transaction Details", transactionsPage.getPageHeading());
		double tranamnt = getTotalAmountNum(transactionsPage.getTransactionAmnt());
		Assert.assertTrue(tranamnt == amount);
		Assert.assertEquals("Credit", transactionsPage.getAccountTranType());
	}

	
	@Then("user logsout")
	public void user_logsout() {
		if(driver.getTitle().equals("ParaBank | Find Transactions")) {
			findTransPage.clickLogout();
		}else {
			transactionsPage = new TransactionsPage(driver);
			transactionsPage.clickLogout();
		}
		Assert.assertTrue(driver.getTitle().equals("ParaBank | Welcome | Online Banking"));
	}
	
	@Then("user clicks find transactions link")
	public void user_clicks_find_transactions_link() throws InterruptedException {
		accountsOverviewPage = new AccountsOverviewPage(driver);
		findTransPage = new FindTransPage(driver);
		oldAccId = accountsOverviewPage.getColData(0, 0);
		newAccId = accountsOverviewPage.getColData(1, 0);
		accountsOverviewPage.clickFindTransactionsLink();
		Assert.assertEquals("ParaBank | Find Transactions", driver.getTitle());
	}

	@Then("user checks transaction results by amount")
	public void user_checks_transaction_results_by_amount() throws InterruptedException {
		findTransPage = new FindTransPage(driver);
		
		findTransPage.setAccount(oldAccId);
		findTransPage.setFindAmount("400");
		findTransPage.clickfindTransactions(3);
		Thread.sleep(1000);
		Assert.assertTrue(driver.getTitle().equals("ParaBank | Find Transactions"));
		Assert.assertEquals("Transaction Results", findTransPage.getTransRsltHding());
		int rows = findTransPage.getRowSize();
		int cols = findTransPage.getColSize();
		String bal = findTransPage.getColData(rows - 1, cols - 2);
		double actBal = getTotalAmountNum(bal);
		Assert.assertEquals(actBal, amount);
		driver.navigate().back();
		Assert.assertTrue(driver.getTitle().equals("ParaBank | Accounts Overview"));
	}

	@Then("user checks transaction results by date")
	public void user_checks_transaction_results_by_date() throws InterruptedException {
		LocalDate currDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
		String date = currDate.format(formatter);
		
		accountsOverviewPage.clickFindTransactionsLink();
		Assert.assertEquals("ParaBank | Find Transactions", driver.getTitle());
		findTransPage.setAccount(newAccId);
		findTransPage.setFindDate(date);
		findTransPage.clickfindTransactions(1);
		Thread.sleep(1000);
		Assert.assertTrue(driver.getTitle().equals("ParaBank | Find Transactions"));
		Assert.assertEquals("Transaction Results", findTransPage.getTransRsltHding());
		int rows = findTransPage.getRowSize();
		int cols = findTransPage.getColSize();
		String bal = findTransPage.getColData(rows - 1, cols - 1);
		double actBal = getTotalAmountNum(bal);
		Assert.assertEquals(actBal, amount);
		driver.navigate().back();
		Assert.assertTrue(driver.getTitle().equals("ParaBank | Accounts Overview"));
	}

	@Then("user checks transaction results by date range")
	public void user_checks_transaction_results_by_date_range() throws InterruptedException {
		LocalDate currDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
		String date = currDate.format(formatter);
		String fromDate = "08-01-2024";
		String toDate = "09-01-2024";
		accountsOverviewPage.clickFindTransactionsLink();
		Assert.assertEquals("ParaBank | Find Transactions", driver.getTitle());
		findTransPage.setAccount(newAccId);
		findTransPage.setFromDate(fromDate);
		findTransPage.setToDate(toDate);
		findTransPage.clickfindTransactions(2);
		Thread.sleep(1000);
		Assert.assertTrue(driver.getTitle().equals("ParaBank | Find Transactions"));
		Assert.assertEquals("Transaction Results", findTransPage.getTransRsltHding());
		int rows = findTransPage.getRowSize();
		int cols = findTransPage.getColSize();
		String bal = findTransPage.getColData(rows - 1, cols - 1);
		double actBal = getTotalAmountNum(bal);
		Assert.assertEquals(actBal, amount);
		driver.navigate().back();
		Assert.assertTrue(driver.getTitle().equals("ParaBank | Accounts Overview"));
	}
	
	@Then("user gets transaction id")
	public void user_gets_transaction_id() throws InterruptedException {		
		accountsOverviewPage.clickColAccLink(0,0);
		Thread.sleep(500);
		Assert.assertEquals("ParaBank | Account Activity", driver.getTitle());
		activityPage = new ActivityPage(driver);
		int rows = activityPage.getRowSize();
		int cols = activityPage.getColSize();
		activityPage.clickColFundTransferLink(rows - 1, cols - 3);
		Thread.sleep(500);
		Assert.assertEquals("ParaBank | Transaction Details", driver.getTitle());
		transactionsPage = new TransactionsPage(driver);
		TransId = transactionsPage.getTransactionId();
		transactionsPage.clickFindTransactions();
	}
	
	@Then("user checks transaction results by transactionid")
	public void user_checks_transaction_results_by_transactionid() throws InterruptedException {		
		Assert.assertEquals("ParaBank | Find Transactions", driver.getTitle());
		findTransPage.setAccount(oldAccId);
		findTransPage.setTransId(TransId);
		findTransPage.clickfindTransactions(0);
		Thread.sleep(1000);
		Assert.assertTrue(driver.getTitle().equals("ParaBank | Find Transactions"));
		Assert.assertEquals("Error!", findTransPage.getTransError());
	}
	
}
