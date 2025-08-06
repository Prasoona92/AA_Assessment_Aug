package stepdefs;

import Models.Form_Textbox;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.*;

import java.util.List;
import java.util.Map;

public class StepDefinition {
    LoginPage loginPage = new LoginPage();
    HomePage homePage= new HomePage();
    AutomationPage automationPage= new AutomationPage();
    TaskBotPage taskBotPage= new TaskBotPage();
    LearningInstancePage learningInstancePage= new LearningInstancePage();
    FormPage formPage = new FormPage();

    @Given("user login to the application")
    public void userLoginToTheApplication() {
        loginPage.loginApplication();
    }

    @Given("^user navigate to (.*) menu$")
    public void userNavigateToMenu(String tab) throws InterruptedException {
        switch (tab.toLowerCase()){
            case "automation":
                homePage.navigateToAutomationTab();
                break;
            case "ai":
                homePage.navigateToAITab();
                Thread.sleep(20000);
                break;
        }


    }

    @When("user click on create dropdown")
    public void userClickOnCreateDropdown() {
        automationPage.clickCreate();
    }

    @And("user select the task bot")
    public void userSelectTheTaskBot() {
        automationPage.clickTaskBotdropdown();
    }

    @And("user select the form")
    public void userSelectTheForm() {
        automationPage.clickFormdropdown();
    }


    @And("user fill all the mandatory details")
    public void userFillAllTheMandatoryDetails(DataTable dataTable) {
        Map<String,String> data = dataTable.asMap();
        taskBotPage.userFillMandatoryDetails(data);
    }

    @And("user verify create button is enabled")
    public void userVerifyCreateButtonIsEnabled() {
        Assert.assertTrue(taskBotPage.isCreateButtonEnabled());
    }

    @And("user able to create the task successfully")
    public void userClickTheCreateButton() {
      taskBotPage.clickCreate();
      Assert.assertTrue(taskBotPage.verifySuccessMessage());
    }

    @Given("verify user can access Actions panel")
    public void verifyUserCanAccessActionsPanel() {
        Assert.assertTrue(taskBotPage.verifyActionsPanelVisible());
    }

    @Given("verify user can access Form panel")
    public void verifyUserCanAccessFormPanel() {
        Assert.assertTrue(formPage.verifyFormPanelVisible());
    }
    @When("^user search and add the action item (.*)$")
    public void userSearchAndAddTheItem(String action) {
        taskBotPage.searchActionItem(action);
        taskBotPage.addActionItem(action);
    }

    @Then("^verify action item (.*) is added to canvas$")
    public void verifyActionItemIsAddedToCanvas(String action) {
        Assert.assertTrue(taskBotPage.verifyActionItemAddedtoCanvas(action));
    }

    @And("user navigate to created taskbot")
    public void userNavigateToCreatedTaskbot() {
        homePage.navigateToAutomationTab();
        taskBotPage.navigeToTaskBotPage();
    }

    @Given("^verify (.*) field is available in the form$")
    public void verifyFieldIsAvailable(String UIElement) {
        taskBotPage.verifyUIElementPresent(UIElement);
    }

    @Then("^verify user able to add (.*) in (.*) field$")
    public void verifyUserAbleToAddInField(String text, String UIElement) {
        taskBotPage.verifyUserEnterData(text,UIElement);

    }

    @Then("verify user able to click the checkbox")
    public void verifyUserAbleToClickTheCheckbox() {
        taskBotPage.clickCloseCheckbox();
    }

    @Given("verify save button is enabled")
    public void verifySaveButtonIsEnabled() {
       Assert.assertTrue(taskBotPage.verifySaveButton());
    }

    @Then("user click the save configuration")
    public void userClickTheSaveConfiguration() {
        taskBotPage.clickSavebutton();
    }

    @Then("user configure the learning instance")
    public void userCreateInstance() throws InterruptedException {
        learningInstancePage.clickcreateLearningInstance();
        learningInstancePage.setName();
        learningInstancePage.clickNextButton();

    }

    @Then("user create learning instance and fetch the response")
    public void userCreateLearningInstanceAndFetchTheResponse() {
        learningInstancePage.clickCreateButton();
    }


    @Then("^user verify the response status code is (.*)$")
    public void userVerifyTheResponseStatusCodeIs(int statuscode) {
      Assert.assertEquals(learningInstancePage.getResponseStatusCode(),statuscode);
    }

    @Then("user verify the response body with the fields")
    public void userVerifyTheResponseBody(DataTable dataTable) {
        List<String> data = dataTable.asList();
        String response = learningInstancePage.getResponsebody();
        for(int i=0; i<data.size();i++){
            Assert.assertTrue(response.contains(data.get(i)));
        }
    }

    @When("^user drag and drop element (.*) to Canvas$")
    public void userDragAndDropElementTextboxToCanvas(String ele) {
        formPage.dragElementtoCanvas(ele);
    }

    @Then("^verify form element (.*) is added to canvas$")
    public void verifyFormElementTextBoxIsAddedToCanvas(String element) {
        formPage.verifyElementaddedtoCanvas(element);
    }

    @Given("user fill all the details for the textbox")
    public void userFillAllTheDetailsForTheTextbox() {
        formPage.clickElementincanvas("TextBox");
        Form_Textbox formTextbox = new Form_Textbox();
        formTextbox.fillelementlabel("Test Textbox");
        formTextbox.filldefval("Test Textbox");
        formTextbox.fillHint("Test Textbox");
        formTextbox.fillMaxlen("Test Textbox");
        formTextbox.fillMinlen("Test Textbox");
        formTextbox.fillTooltip("Test Textbox");
        formTextbox.clickcheckbox("Make field uneditable");

    }

    @Then("user save the form")
    public void userSaveTheForm() {
        formPage.clickSavebutton();
    }
}

