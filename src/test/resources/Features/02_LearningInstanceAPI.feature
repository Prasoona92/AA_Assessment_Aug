Feature: Learning Instance API validation

  Scenario: Create a learning instance and validate the response

    Given user login to the application
    Then user navigate to AI menu
    Then user configure the learning instance
    Then user create learning instance and fetch the response
    Then user verify the response status code is 200
    Then user verify the response body with the fields
    |id|
    |name|
    |status|