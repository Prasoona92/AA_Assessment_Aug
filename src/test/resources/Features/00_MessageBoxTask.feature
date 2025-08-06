Feature: Message Box Feature Validation


  Scenario:Create Task Bot from dropdown
    Given user login to the application
    When user navigate to automation menu
    When user click on create dropdown
    And user select the task bot
    And user fill all the mandatory details
    |name|Testbot|
    |Description|Testbotcreation|
    |Folder     |Bots      |
    And user verify create button is enabled
    And user able to create the task successfully


  Scenario: Add Message box to task bot

    Given verify user can access Actions panel
    When user search and add the action item Message box
    Then verify action item Message box is added to canvas

  Scenario Outline: Verify UI element interaction for the message box

    Given verify <UIElement> field is available in the form
    Then verify user able to add <Text> in <UIElement> field

    Examples:
    |UIElement|Text|
    |window title|AATitle    |
    |message     |AATextMessage    |
    |scrollbar   |10    |

  Scenario: Verify closemessage checkbox UI element interaction for the message box

    Given verify user able to click the checkbox
    Then verify timeout field is available in the form
    Then verify user able to add 10 in timeout field

  Scenario: Verify Save Configuration
      Given verify save button is enabled
      Then user click the save configuration

