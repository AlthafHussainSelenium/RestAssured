Feature: insert products using  DELETE API

  Scenario Outline: Validate Delete products api work correctly
    Given Hit the url of Delete products api endpoint
    When Pass the url of the delete product in the request with <ProductNumber>
    Then Verify the delete api status code as 200

    Examples:
      | ProductNumber |
      | 8             |