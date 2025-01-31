@all
Feature: open a new bank account

Background:
	Given user opens the application
	
Scenario Outline: user opens new account
	When user login with "<username>" and "<password>"
	And user was redirected to accounts overview page
	And user clicks open new account link
	Then user was redirected to open account page
	And user selects type of account to open "<type>"
	And user clicks open new account button
	Then user checks for account number
	And user clicks account number link
	Then user checks account details
	And user clicks funds transfer received
	Then user checks transaction details
	And user logsout of the application

Examples:
	| username | password | type |
	| john_andrew | John@1245 | CHECKING |
	| john_andrew | John@1245 | SAVINGS |