@all @reg
Feature: register an account 

	@pre
  Scenario: set database options
    Given user opens the application
    And user sets database options


	@regAcc
  Scenario Outline: register to check bank account details
    Given user opens the application
    And user clicks register link
    Then user was redirected to register page
    And user enters details "<username>" and "<password>"
    Then user gets confirmation message
		And user signsout of the app
		
		Examples:
		| username | password |
		| john_andrew | John@1245 |
		| Luce_Walker | Luce@1234 |
		| Sunflower | flower |