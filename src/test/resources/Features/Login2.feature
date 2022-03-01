Feature: Login Functionality

  In order to do Internet banking
  As a para bank customer
  I want to Login successfully

  Scenario Outline: Login succeful

    Given  i am in the login page of the para bank Application
    When  I enter a valid <username> and <password> with <userFullName>
    Then I should be taken to the Overview page

    Examples:
      |username|password|userFullName|
      |"mahmoudtest"|"123123"|"test yyyy"|