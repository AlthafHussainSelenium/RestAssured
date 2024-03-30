Feature: Get All products from the API

  Scenario: Verify the given API for the Products
    Given I hit the url of get products api endpoint
    When I pass the url of products in the request
    Then I receive the response code of 200

  Scenario Outline: Verify the rate of the first product is correct
    Given I hit the url of get products api endpoint
    When I pass the url of products in the request
    Then I verify that the rate of the product is <FirstProductRate>
    Examples:
      |FirstProductRate|
      |3.9            |


