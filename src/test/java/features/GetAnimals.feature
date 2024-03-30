Feature: Get animals from the get Api

  Scenario: Get animals from the application
    Given I am an authenticated user
    When I hit the get animals api url
    Then I get animals in the response of the API

  Scenario: Get animals from the application
    Given I am un authenticated user
    When I hit the get animals api url
    Then I do not get animals in the response of the API