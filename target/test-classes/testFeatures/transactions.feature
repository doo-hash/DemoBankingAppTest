@Transactions @all
Feature: transfer money from account to account

Background:
    Given user opens the application
		When user login with "john_andrew" and "John@1245"

	@transfer
  Scenario: money transaction
    Then user checks accounts
    And user clicks transfer funds link
		And user enters amount to transfer "400.00"
		And user selects accounts and clicks transfer button
		Then user checks transfer complete message
		Then user checks account balance in accounts overview
		Then user checks debited account details
		Then user checks credited account details
		Then user logsout
		
	@findAccTran	
	Scenario: find account transactions
    Then user clicks find transactions link
		Then user checks transaction results by amount
		Then user checks transaction results by date
		Then user checks transaction results by date range
		Then user gets transaction id
		Then user checks transaction results by transactionid
		Then user logsout
		
		