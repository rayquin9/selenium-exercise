#Author: jeffrey.bryan@publicissapient.com
Feature: Find A Flight

  @FindFlightChrome
  Scenario: Preform a Basic Flight Search and see the results on a new screen
    Given the User logs into the application
    And moves to the Flights Page
    When the User searches for a "ROUNDTRIP" "BUSINESS" class flight with the following values:
      | Element        | Value    |
      | Departing City | New York |
      | Departing Date | March 18 |
      | Arriving City  | London   |
      | Return Date    | April 14 |
    Then they should see flight results
    #And the browser gets closed
