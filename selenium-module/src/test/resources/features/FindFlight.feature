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
Feature: Find A Flight

@FindFlightChrome
  Scenario: Basic Flight Search
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
   And I move to the Flights Page
   When I search for a "ROUNDTRIP" "BUSINESS" class flight with the following values:
    | Element        | Value       |
    | Departing City | New York    |
    | Departing Date | March 18    |
    | Arriving City  | London      |
    | Return Date    | April 14    |
   Then I should see flight results
   And take a screenshot and save it to "findFlightScreenshot_Chrome"
   Then I close the browser
  
  @FindFlightFirefox
  Scenario: Basic Flight Search FireFox
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
   And I move to the Flights Page
   When I search for a "ROUNDTRIP" "BUSINESS" class flight with the following values:
    | Element        | Value       |
    | Departing City | New York    |
    | Departing Date | March 30    |
    | Arriving City  | Paris       |
    | Return Date    | April 25    |
   Then I should see flight results
   And take a screenshot and save it to "findFlightScreenshot_Firefox"
   Then I close the browser