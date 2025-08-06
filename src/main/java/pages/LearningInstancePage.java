package pages;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitForSelectorState;
import utils.PlaywrightFactory;
import java.util.Random;

public class LearningInstancePage {
    private Page page;
    private final String buttonCreateLI="//button[@name='create-button']";
    private final String textName="//input[@name='name']";
    private final String buttonNext="//button[@name='submit']";
    private final String buttonCreate="//button[@aria-label='Create']";
    private final String iframeLocator="//iframe[@class='modulepage-frame']";
    private String LIname="";
    private Response responseend;

    public LearningInstancePage(){
        page= PlaywrightFactory.getPage();
    }

    public void clickcreateLearningInstance() throws InterruptedException {
        System.out.println("Inside creating");
        page.waitForSelector(iframeLocator);
        Locator.WaitForOptions waitForOptions= new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE);
        page.locator(iframeLocator).waitFor(waitForOptions);
        if(page.locator(iframeLocator).isEnabled()){
            FrameLocator iframe = page.frameLocator("//iframe[@class='modulepage-frame']");
            System.out.println(iframe);
            iframe.locator(buttonCreateLI).click();
        }
    }

    public void setName(){
        Random random = new Random();
        LIname="test"+random.nextInt(2000);
        page.waitForSelector(iframeLocator);
        Locator.WaitForOptions waitForOptions= new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE);
        page.locator(iframeLocator).waitFor(waitForOptions);
        if(page.locator(iframeLocator).isEnabled()){
            FrameLocator iframe = page.frameLocator("//iframe[@class='modulepage-frame']");
            iframe.locator(textName).fill(LIname);
        }
    }

    public void clickNextButton()  {
        Locator.WaitForOptions waitForOptions= new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE);
        page.locator(iframeLocator).waitFor(waitForOptions);
        if(page.locator(iframeLocator).isEnabled()){
            FrameLocator iframe = page.frameLocator("//iframe[@class='modulepage-frame']");
            iframe.locator(buttonNext).click();
        }
    }

    public void clickCreateButton() {
        Page.WaitForResponseOptions waitForResponseOptions = new Page.WaitForResponseOptions().setTimeout(20000);
        responseend = page.waitForResponse(response -> response.url().endsWith("/learninginstances"), waitForResponseOptions, () -> {
            Locator.WaitForOptions waitForOptions = new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE);
            page.locator(iframeLocator).waitFor(waitForOptions);
            if (page.locator(iframeLocator).isEnabled()) {
                FrameLocator iframe = page.frameLocator("//iframe[@class='modulepage-frame']");
                iframe.locator(buttonCreate).click();
            }

        });
    }

    public int getResponseStatusCode(){
      return responseend.status();

    }
    public String getResponsebody(){
       return responseend.text();
    }

}
