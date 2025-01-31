package org.demoBankingApp.stepDefs;

import static org.testng.Assert.assertTrue;

import org.demoBankingApp.pages.AccountsOverviewPage;
import org.demoBankingApp.pages.ActivityPage;
import org.demoBankingApp.pages.HomePage;
import org.demoBankingApp.pages.OpenAccountPage;
import org.demoBankingApp.pages.TransactionsPage;
import org.demoBankingApp.utilities.AppUtilities;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CreateAccountStepDef extends AppUtilities {

	String accNum = "";
	
	@Given("user opens the application")
	public void user_opens_the_application() {
		driver.get("https://parabank.parasoft.com/parabank/index.htm");
		String title = driver.getTitle();
		
		try {
			if(title.equals("ParaBank | Welcome | Online Banking")) {
				logger.info("application opened");
				assertTrue(true);
			}else {
				logger.error("application has not opened");
				assertTrue(false);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}

	@When("user login with {string} and {string}")
	public void user_login_with_and(String username, String password) {
		homePage = new HomePage(driver);
		homePage.setUsername(username);
		logger.info("Entered username: " + username);
		homePage.setPassword(password);
		logger.info("Entered password: " + password);
		homePage.clickLoginButton();
		logger.info("Clicked login button");
	}

	@When("user was redirected to accounts overview page")
	public void user_was_redirected_to_accounts_overview_page() {
		accountsOverviewPage = new AccountsOverviewPage(driver);
		logger.info(driver.getCurrentUrl());
		String title = driver.getTitle();
		
		String heading = accountsOverviewPage.getPageHeading();
		try {
			if(title.equals("ParaBank | Accounts Overview") && heading.equals("Accounts Overview")) {
				logger.info("Accounts overview page opened");
				assertTrue(true);
			}else {
				logger.error("Login failed");
				assertTrue(false);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}

	@When("user clicks open new account link")
	public void user_clicks_open_new_account_link() {
		accountsOverviewPage.clickOpenNewAccLink();
		logger.info("Clicked open new account");
	}

	@Then("user was redirected to open account page")
	public void user_was_redirected_to_open_account_page() {
		openAccountPage = new OpenAccountPage(driver);
		logger.info(driver.getCurrentUrl());
		String title = driver.getTitle();
		try {
			if(title.equals("ParaBank | Open Account")) {
				logger.info("Open Account page opened");
				assertTrue(true);
			}else {
				logger.error("Failed to open account page");
				assertTrue(false);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}

	@Then("user selects type of account to open {string}")
	public void user_selects_type_of_account_to_open(String type) {
		openAccountPage.setAccountType(type);
		logger.info("Selected account type: " + type);
		accNum = openAccountPage.setAccountId();
		logger.info("Given account id: " + accNum);
	}

	@Then("user clicks open new account button")
	public void user_clicks_open_new_account_button() {
		openAccountPage.clickOpenAcc();
		logger.info("Clicked open new account button");
	}

	@Then("user checks for account number")
	public void user_checks_for_account_number() {
		String heading = openAccountPage.getPageHeading();
		logger.info("Account success message: " + heading);
		String accId = openAccountPage.getAccId();
		
		if(accId.equals(accNum)) {
			logger.info("Checked account number");			
		}else {
			logger.fatal("Account not opened");
		}
	}

	@Then("user clicks account number link")
	public void user_clicks_account_number_link() {
		openAccountPage.clickAccIdLink();
		logger.info("Clicked account number link");
	}

	@Then("user checks account details")
	public void user_checks_account_details() {
		activityPage = new ActivityPage(driver);

		String url = driver.getCurrentUrl();
		if(url.contains(accNum)) {
			String accId = activityPage.getAccountId();
			String acctype = activityPage.getAccountType();
			String bal = activityPage.getAccountBal();
			String avlBal = activityPage.getAccountAvlBal();
			String accDate = activityPage.getAccountDate();
			String accCredit = activityPage.getAccountCredit();
			Assert.assertTrue(accId.equals(accNum));
			logger.info("Account Number: " + accId);
			logger.info("Account Type: " + acctype);
			logger.info("Account Balance: " + bal);
			logger.info("Account Available Balance: " + avlBal);
			logger.info("Account created date: " + accDate);
			Assert.assertTrue(bal.equals(accCredit));
			Assert.assertTrue(avlBal.equals(accCredit));
			logger.info("Account Credited amount: " + accCredit);
			logger.info("Checked account details");	
		}else {
			logger.error("Account page not found");
		}
	}

	@Then("user clicks funds transfer received")
	public void user_clicks_funds_transfer_received() {
		activityPage.clickAccTransactions();
		logger.info("Clicked fund transfer received link");
	}

	@Then("user checks transaction details")
	public void user_checks_transaction_details() {
		transactionsPage = new TransactionsPage(driver);
		String url = driver.getCurrentUrl();
		String heading = transactionsPage.getPageHeading();
		Assert.assertTrue(heading.equals("Transaction Details"));
		
		String tranId = transactionsPage.getTransactionId();
		if(url.contains(tranId)) {
			logger.info("Transaction date: " + transactionsPage.getAccountTranDate());
			logger.info("Transaction type: " + transactionsPage.getAccountTranType());
			logger.info("Transaction Amount: " + transactionsPage.getTransactionAmnt());
			logger.info("Checked account transaction details");	
		}else {
			logger.error("Transactions page not found");
		}
	}

	@Then("user logsout of the application")
	public void user_logsout_of_the_application() {
		transactionsPage = new TransactionsPage(driver);
		transactionsPage.clickLogout();
		if(driver.getTitle().equals("ParaBank | Welcome | Online Banking")) {
			logger.info("Logs out of the application");
		}else {
			logger.fatal("Unable to logout of the application");
		}
	}

	
}
