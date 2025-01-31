Feature: register an account 

	@register
  Scenario Outline: register to check bank account details
    Given user opens the application
    And user sets database options
    And user clicks register link
    Then user was redirected to register page
    And user enters details
    Then user gets confirmation message

    Examples: 
      | name  | value | status  |
      | name1 |     5 | success |
      | name2 |     7 | Fail    |
