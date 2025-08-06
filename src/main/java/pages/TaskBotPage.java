package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.cucumber.java.Scenario;
import io.cucumber.java.it.Ma;
import utils.PlaywrightFactory;
import utils.ScenarioContext;

import java.lang.invoke.SwitchPoint;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class TaskBotPage {

    private final Page page;
    private final String textName = "input[name='name']";
    private final String textDescription = "input[name='description']";
    private final String buttonChooseFilePath = "button[name='parentId.choose']";
    private final String selectFolderPath = "div[class='wrapped-items-container__item']";
    private final String buttonChooseFolder = "[title='Choose']";
    private final String buttonCreate = "[title='Create & edit']";
    private final String dataSuccessMessage = "[data-path='Toast']";
    private final String createdbotPage = "[data-path='EditorPage']";
    private final String actionPanel = "//div[@data-path='EditorPalette.section']//span[@data-text='Actions']";
    private final String canvasActionItem="//div[@class='editor-layout__canvas']//div[@data-path='TaskbotCanvasFlowPoint']";
    private final String childActionItem="//div[@class='editor-palette-group-container']//div[@class='editor-palette-group__children']";
    private final String textActionSearch="//input[@type='text']";
    private final String uiElementinputs="//div[@class='textinput-cell-input-control']";
    private final String uiElementTitle=uiElementinputs+"//div[@name='title']";
    private final String uiElementMessage=uiElementinputs+"//div[@name='content']";
    private final String uiElementScrollbar=uiElementinputs+"//div[@name='scrollLines']";
    private final String uiElementCloseCheckbox="//span[@role='checkbox']";
    private final String uiElementTimeout=uiElementinputs+"//div[@name='timeOut']";
    private final String buttonSave="//span[@title='Save']";

    ScenarioContext scenarioContext= new ScenarioContext();

    public TaskBotPage() {
        page = PlaywrightFactory.getPage();
    }

    public void userFillMandatoryDetails(Map<String, String> details) {

        for (String k : details.keySet()) {
            switch (k.toLowerCase()) {
                case "name":
                    Random random = new Random();
                    String dynamicname = details.get(k) + random.nextInt(2000);
                    scenarioContext.setContext("name",dynamicname);
                    page.locator(textName).fill(dynamicname);
                    break;
                case "description":
                    page.locator(textDescription).fill(details.get(k));
                    break;
                case "folder":
                    page.locator(buttonChooseFilePath).click();
                    String folderpath = ">[title='" + details.get(k) + "']";
                    page.locator(selectFolderPath + folderpath).click();
                    page.locator(buttonChooseFolder).click();
                    break;
            }
        }
    }

    public boolean isCreateButtonEnabled() {
        return page.locator(buttonCreate).isEnabled();
    }

    public void clickCreate()  {
        Locator loc=page.locator(buttonCreate);
        loc.click();
    }

    public boolean verifySuccessMessage() {
        boolean flag=page.locator(dataSuccessMessage).textContent().contains("successfully created");
        return flag;
    }

    public boolean verifyCreatedBotPageOpened() {
        page.waitForSelector(createdbotPage);
        return (page.locator(createdbotPage).isVisible());
    }

    public boolean verifyActionsPanelVisible() {
        page.waitForSelector(actionPanel);
        return (page.locator(actionPanel).isVisible());
    }

    public void addActionItem(String actionItem) {
        String actionlocator = childActionItem+"//span[@data-text='" + actionItem + "']";
        Locator locator=page.locator(actionlocator);
        locator.dblclick();
    }

    public boolean verifyActionItemAddedtoCanvas(String actionItem) {
        String actionItemLocator=canvasActionItem+"//div[contains(text(),'"+actionItem+"')]";
        page.waitForSelector(actionItemLocator);
        return page.locator(actionItemLocator).isVisible();
    }

    public void navigeToTaskBotPage() {
        String dname= scenarioContext.getContext("name").toString();
        String taskbotlocator="//a[@aria-label='"+dname+"']";
        page.locator(taskbotlocator).click();

    }

    public void searchActionItem(String action) {
        page.locator(textActionSearch).fill(action);
    }

    public boolean verifyUIElementPresent(String uiElement) {
        if(uiElement.contains("title")){
            return page.locator(uiElementTitle).isEditable();
        }
        if(uiElement.contains("message")){
            return page.locator(uiElementMessage).isEditable();
        }
        if(uiElement.contains("scrollbar")){
            return page.locator(uiElementScrollbar).isEditable();
        }
        if(uiElement.contains("closecheckbox")){
            return page.locator(uiElementCloseCheckbox).isEditable();
        }
        if(uiElement.contains("timeout")){
            return page.locator(uiElementTimeout).isEditable();
        }
        return false;
    }

    public void verifyUserEnterData(String text, String uiElement) {
        if(uiElement.contains("title")){
            page.locator(uiElementTitle).fill(text);
        }
        if(uiElement.contains("message")){
            page.locator(uiElementMessage).fill(text);
        }
        if(uiElement.contains("scrollbar")){
            page.locator(uiElementScrollbar).fill(text);
        }
        if(uiElement.contains("timeout")){
            page.waitForSelector(uiElementTimeout);
            page.locator(uiElementTimeout).fill(text);
        }
    }

    public void clickCloseCheckbox() {
        Page.ClickOptions options= new Page.ClickOptions();
        options.setForce(true);
        page.click(uiElementCloseCheckbox,options);
    }

    public boolean verifySaveButton() {
        return page.locator(buttonSave).isEnabled();
    }

    public void clickSavebutton() {
        page.locator(buttonSave).click();
    }
}