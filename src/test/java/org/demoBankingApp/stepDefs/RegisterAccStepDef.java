package org.demoBankingApp.stepDefs;

import org.demoBankingApp.pages.AdminPage;
import org.demoBankingApp.pages.HomePage;
import org.demoBankingApp.pages.RegisterPage;
import org.demoBankingApp.utilities.AppUtilities;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class RegisterAccStepDef extends AppUtilities {

	String name;
	
	@Given("user sets database options")
	public void user_sets_database_options() {
		logger.info("User sets database options");
		homePage = new HomePage(driver);
		homePage.clickAdmin();
		Assert.assertTrue(driver.getTitle().equals("ParaBank | Administration"));
		adminPage = new AdminPage(driver);
		Assert.assertTrue(adminPage.ishdingVisible());

		adminPage.clickClean();
		adminPage.clickInitialize();
		adminPage.setJdbc();
		adminPage.setInitBal("5000");
		adminPage.setMinBal("1000");
		adminPage.clickSubmit();
		Assert.assertTrue(adminPage.isMsgVisible());
	}

	@Given("user clicks register link")
	public void user_clicks_register_link() {
		homePage = new HomePage(driver);
		homePage.clickRegister();
		logger.info("User clicked register link");
	}

	@Then("user was redirected to register page")
	public void user_was_redirected_to_register_page() {
		Assert.assertTrue(driver.getTitle().equals("ParaBank | Register for Free Online Account Access"));
		logger.info(driver.getCurrentUrl());
		logger.info("Register page is opened");
	}

	@Then("user enters details {string} and {string}")
	public void user_enters_details_and(String username, String password) {
		registerPage = new RegisterPage(driver);

		name = username;
		registerPage.setFirstName(getRandomString());
		registerPage.setLastName(getRandomString());
		registerPage.setAddress(getRandomString());
		registerPage.setCity(getRandomString());
		registerPage.setState(getRandomString());
		registerPage.setZipcode(getRandomNum());
		registerPage.setPhone(getRandomPhoneNum());
		registerPage.setSsn(getRandomNum());
		registerPage.setUserName(name);
		registerPage.setPassword(password);
		registerPage.setConfirmPsd(password);
		registerPage.clickRegister();
		logger.info("User enters details");
	}

	@Then("user gets confirmation message")
	public void user_gets_confirmation_message() {
		Assert.assertTrue(driver.getTitle().equals("ParaBank | Customer Created"));
		Assert.assertTrue(registerPage.isMessageVisible());
		String msg = registerPage.getMessage();
		Assert.assertTrue(msg.contains(name));
		logger.info("Account created successfully");
	}

	@And("user signsout of the app")
	public void user_signsout_of_the_app() {
		registerPage.clickLogout();
		logger.info("user logs out successfully");
	}
}
