package pages;

import com.microsoft.playwright.Page;
import utils.PlaywrightFactory;


public class HomePage {
    private final Page page;
    private final String linkAutomation="a[title='Automation']";
    private final String linkAI="//button[@title='AI']";
    private final String linkLI="a[title='Document Automation']";

    public HomePage(){
        page= PlaywrightFactory.getPage();
    }

    public void navigateToAutomationTab(){
        page.locator(linkAutomation).click();
    }

    public void navigateToAITab() throws InterruptedException {
        page.waitForSelector(linkAI);
        page.locator(linkAI).click();
        page.waitForSelector(linkLI);
        page.locator(linkLI).click();

    }

}
