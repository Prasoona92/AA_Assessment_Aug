package Models;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import pages.FormPage;
import utils.PlaywrightFactory;

import java.sql.Struct;

public class Form_Textbox {

    // Locators
    private String inputElementLabel = "//input[@name='label']"; // Replace with actual selector
    private String inputdefvalue = "//input[@name='defaultValue']"; // Replace with actual selector
    private String inputMinLength = "//input[@name='minLength']"; // Replace with actual selector
    private String inputMaxLength = "//input[@name='maxLength']";
    private String inputHint =  "//input[@name='hintText']";
    private String inputTooltip= "//textarea[@name='toolTip']";
    private String buttonSave="//button[@name='save']";

    private Page page;
    private FrameLocator frame;
    FormPage formPage= new FormPage();
    public Form_Textbox(){
        page = PlaywrightFactory.getPage();
        frame= formPage.getFrame();

    }
    // Methods to interact with the form
    public boolean fillelementlabel(String name) {
        if(frame.locator(inputElementLabel).isEnabled()){
            frame.locator(inputElementLabel).fill(name);
            return true;
        }
        return false;
    }

    public boolean filldefval(String data) {
        if(frame.locator(inputdefvalue).isEnabled()){
            frame.locator(inputdefvalue).fill(data);
            return true;
        }
        return false;
    }
    public boolean fillMinlen(String data) {
        if(frame.locator(inputMinLength).isEnabled()){
            frame.locator(inputMinLength).fill(data);
            return true;
        }
        return false;
    }

    public boolean fillMaxlen(String data) {
        if(frame.locator(inputMaxLength).isEnabled()){
            frame.locator(inputMaxLength).fill(data);
            return true;
        }
        return false;
    }

    public boolean fillHint(String data) {
        if(frame.locator(inputHint).isEnabled()){
            frame.locator(inputHint).fill(data);
            return true;
        }
        return false;
    }

    public boolean fillTooltip(String data) {
        if(frame.locator(inputTooltip).isEnabled()){
            frame.locator(inputTooltip).fill(data);
            return true;
        }
        return false;
    }

    public boolean clickcheckbox(String data) {
        String checkboxloc="//span[@aria-label='"+data+"']";
        Locator.ClickOptions options= new Locator.ClickOptions().setForce(true);
        if(frame.locator(checkboxloc).isEnabled()){
            frame.locator(checkboxloc).click(options);
            return true;
        }
        return false;
    }

}
