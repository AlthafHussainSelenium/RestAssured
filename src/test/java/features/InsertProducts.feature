
Feature: insert products using POST API

  Scenario Outline: Validate post products api work correctly
    Given I hit the url of post products api endpoint
    When I pass the request body of product title <ProductTitle>
    Then Verify the status code for post request is 200

    Examples:
      | ProductTitle |
      | shoes       |

  Scenario Outline: Validate post products api response body work correctly
    Given I hit the url of post products api endpoint
    When I pass the request body of product title <ProductTitle>
    Then Verify the status code for post request is 200
    And I receive the response body with id as <id>

    Examples:
      | ProductTitle | id |
      | shoes        | 21 |