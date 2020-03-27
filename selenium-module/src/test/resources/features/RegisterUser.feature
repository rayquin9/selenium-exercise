#Author: jeffrey.bryan@publicissapient.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@RegisterUser
Feature: Register The User

  @Chrome
  Scenario: Basic Registration
    Given I am in "Chrome"
    And I log into the application with the following values:
    | Element    | Value                      |
    | First Name | Jeff                       |
    | Last Name  | Bryan                      |
    | Phone      | 7036197971                 |
    | Email      | testemail@gmail.com        |
    | Address    | 1515 North CourtHouse Road |
    | City       | Arlington                  |
    | State      | VA                         |
    | Zip        | 22210                      |
    | UserId     | testUser9                  |
    | Password   | testUser9                  |
   Then I check that I am on the Registered User Page
   Then I close the browser
  
  @Firefox
  Scenario: Basic Registration
    Given I am in "Firefox"
    And I log into the application with the following values:
    | Element    | Value                      |
    | First Name | Jeff                       |
    | Last Name  | Bryan                      |
    | Phone      | 7036197971                 |
    | Email      | testemail@gmail.com        |
    | Address    | 1515 North CourtHouse Road |
    | City       | Arlington                  |
    | State      | VA                         |
    | Zip        | 22210                      |
    | UserId     | testUser9                  |
    | Password   | testUser9                  |
   Then I check that I am on the Registered User Page
   Then I close the browser