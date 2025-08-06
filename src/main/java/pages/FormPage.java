package pages;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import utils.Commondefs;
import utils.PlaywrightFactory;

public class FormPage {


    private final Page page;
    private final String formPanel = "//div[@data-path='EditorPalette.section']//span[@data-text='Form']";
    private final String formElement="//div[@data-path='EditorPalette.item']";
    private final String canvasZone="//div[@class='formcanvas__leftpane']";
    private final String elementaddedCanvas="//div[@class='formcanvas-content']";
    private final String iframeLocator="//iframe[@class='modulepage-frame']";
    private final String buttonSave="//button[@name='save']";
    private FrameLocator iframe = null;

    public FormPage() {
        page = PlaywrightFactory.getPage();
    }
    public boolean verifyFormPanelVisible() {
        iframe = getFrame();
        return iframe.locator(formPanel).isVisible();
//        return (page.locator(formPanel).isVisible());
    }

    public FrameLocator getFrame(){
        Locator.WaitForOptions waitForOptions= new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE);
        page.locator(iframeLocator).waitFor(waitForOptions);
        if(page.locator(iframeLocator).isEnabled()){
            iframe = page.frameLocator(iframeLocator);
        }
        return iframe;
    }
    public void dragElementtoCanvas(String element){
        String iframeLoc="//iframe[@class='modulepage-frame']";
        FrameLocator iframe=null;
        String sourceselector = formElement+"//span[@data-text='"+element+"']";
        Locator.WaitForOptions waitForOptions= new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE);
        page.locator(iframeLoc).waitFor(waitForOptions);
        if(page.locator(iframeLoc).isEnabled()) {
            iframe = page.frameLocator(iframeLoc);
        }
        Locator srcloc= iframe.locator(sourceselector);
        Locator desloc= iframe.locator(canvasZone);
        Commondefs.dragAndDropElement(page,srcloc,desloc);

    }

    public boolean verifyElementaddedtoCanvas(String element){
        String locator=elementaddedCanvas+"//*[contains(text(),'"+element+"')]";
        FrameLocator iframe = getFrame();
        return iframe.locator(locator).isVisible();
    }

    public void clickElementincanvas(String element ){
        String locator=elementaddedCanvas+"//*[contains(text(),'"+element+"')]";
        FrameLocator iframe = getFrame();
        iframe.locator(locator).click();
    }


    public void clickSavebutton() {
        iframe = getFrame();
        iframe.locator(buttonSave).click();
    }
}
