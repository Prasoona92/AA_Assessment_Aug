package pages;

import com.beust.ah.A;
import com.microsoft.playwright.Page;
import utils.PlaywrightFactory;

public class AutomationPage {

    private final Page page;
    private final String buttonCreate="[title='Create']";
    private final String dropdownTaskBot="[title='Task Bot…']";
    private final String dropdownForm="[title='Form…']";

    public AutomationPage(){
        page= PlaywrightFactory.getPage();
    }

    public void clickCreate(){
        page.locator(buttonCreate).click();
    }

    public void clickTaskBotdropdown(){
        page.locator(dropdownTaskBot).click();
    }

    public void clickFormdropdown(){
        page.locator(dropdownForm).click();
    }
}
