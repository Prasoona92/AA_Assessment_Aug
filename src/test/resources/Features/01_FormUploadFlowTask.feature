Feature: Form with Uplod Flow

  Scenario: Create Form from dropdown
    Given user login to the application
    When user navigate to automation menu
    When user click on create dropdown
    And user select the form
    And user fill all the mandatory details
      |name|FormTest|
      |Description|Testformcreation|
      |Folder     |Bots      |
    And user verify create button is enabled
    And user able to create the task successfully

  Scenario Outline:Add textbox to form canvas
    Given user drag and drop element <Element> to Canvas
    Then verify form element <Element> is added to canvas

    Examples:
    |Element|
    |Text Box|
    |Select File|
    |Select Folder|

  Scenario: Verify UI Interactions with Form element textbox

    Given user fill all the details for the textbox
    Then user save the form


