@all
Feature: check all accounts and its details

  Scenario: user checks balance in all accounts
		Given user opens the application
		When user login with "john_andrew" and "John@1245"
		And user was redirected to accounts overview page
    Then user checks accounts balance amount
		And user logsout of the application

