package org.demoBankingApp.stepDefs;

import org.demoBankingApp.pages.AccountsOverviewPage;
import org.demoBankingApp.pages.ActivityPage;
import org.demoBankingApp.pages.BillPayPage;
import org.demoBankingApp.utilities.AppUtilities;
import org.testng.Assert;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BillPayStepDef extends AppUtilities {

	String fromAcc = "";
	String payee = getRandomString();
	String payAcc = getRandomNum();
	String amount = "400.00";
	double amountinNum = Double.parseDouble(amount);
	
	@When("user clicks pay bill link")
	public void user_clicks_pay_bill_link() {
		accountsOverviewPage = new AccountsOverviewPage(driver);
		billPayPage = new BillPayPage(driver);
		fromAcc = accountsOverviewPage.getColData(0, 0);
		accountsOverviewPage.clickBillPayLink();
		Assert.assertEquals("ParaBank | Bill Pay", driver.getTitle());
		Assert.assertEquals("Bill Payment Service", billPayPage.getHeading());
	}

	@When("user enters payee details")
	public void user_enters_payee_details() {
		billPayPage.setName(payee);
		billPayPage.setAddress(getRandomString());
		billPayPage.setCity(getRandomString());
		billPayPage.setState(getRandomString());
		billPayPage.setZipcode(getRandomString());
		billPayPage.setPhone(getRandomPhoneNum());
		billPayPage.setAccount(payAcc);
		billPayPage.setVerifyAccount(payAcc);
		billPayPage.setAmount(amount);
		billPayPage.setFromAccount(fromAcc);
		billPayPage.clickSendPayment();
	}

	@Then("user gets payment confirmation message")
	public void user_gets_payment_confirmation_message() throws InterruptedException {
		Thread.sleep(1000);
		Assert.assertEquals("ParaBank | Bill Payment Complete", driver.getTitle());
		Assert.assertEquals("Bill Payment Complete", billPayPage.getHeadingMessage());
		Assert.assertTrue(billPayPage.getMessage(fromAcc, amount, payee));

		billPayPage.clickAccountsOverview();
		Assert.assertEquals("ParaBank | Accounts Overview", driver.getTitle());
		Assert.assertEquals("Accounts Overview", accountsOverviewPage.getPageHeading());
	}

	@Then("user checks transactions")
	public void user_checks_transactions() {
		accountsOverviewPage.clickColAccLink(0, 0);
		Assert.assertEquals("ParaBank | Account Activity", driver.getTitle());
		activityPage = new ActivityPage(driver);
		String bal = activityPage.getColData(0, 2);
		double balNum = getTotalAmountNum(bal);
		System.out.println(amountinNum);
		System.out.println(balNum);
		Assert.assertTrue(amountinNum == balNum);
		activityPage.clickColFundTransferLink(0, 1);
	}

}
