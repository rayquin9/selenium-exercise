#Author: jeffrey.bryan@publicissapient.com

@RegisterUser
Feature: Register The User

  @SmokeTest
  Scenario: A user should be able to register suceesfully
    Given the User is on the registration page
    And they log into the application with the following values:
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
   Then the User should be on the Registered User Page
   #And the browser gets closed