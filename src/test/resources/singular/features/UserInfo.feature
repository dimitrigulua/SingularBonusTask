#language: en

Feature: User Info

  Background: Setup
    Given Setup RestAssured common parameters

  Scenario: Retrieve user information when the session is valid
    Given Token extracted from POST request to authorize endpoint
    When I retrieve user information
    Then The system returns status code of 10
    And I should view authorized user information

  Scenario: Retrieve user information when the session is not valid
    Given Invalid authorization token
    When I retrieve user information
    Then The system returns status code of 401
    And The system returns an error message

