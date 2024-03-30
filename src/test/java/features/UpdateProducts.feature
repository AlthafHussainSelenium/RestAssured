Feature: insert products using  PUT API

  Scenario Outline: Validate put products api work correctly
    Given Hit the url of put products api endpoint
    When Pass the url of the product in the request with <ProductNumber>
    Then Verify the status code as 200

    Examples:
      | ProductNumber |
      | 6             |

#  Scenario Outline: Validate put products api response body work correctly
#    Given Hit the url of put products api endpoint
#    When Pass the request body of product title <ProductTitle>
#    Then Verify the status code for post request is 200
#    And I receive the response body with id as <id>
#
#    Examples:
#      | ProductTitle | id |
#      | Slippers     | 21 |