@all
Feature: paying a bill from account

  @billPay
  Scenario: user pays bill from account 
		Given user opens the application
		When user login with "Luce_Walker" and "Luce@1234"
		And user was redirected to accounts overview page
    And user clicks pay bill link
    And user enters payee details
    Then user gets payment confirmation message
    And user checks transactions
    And user logsout
