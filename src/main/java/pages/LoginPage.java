package pages;

import com.microsoft.playwright.Page;
import utils.ConfigFileReader;
import utils.PlaywrightFactory;

public class LoginPage {

    private final Page page;
    private final String textUserName="[name='username']";
    private final String textPassword="[name='password']";
    private final String buttonLogin="[name='submitLogin']";

    public LoginPage(){
        page=PlaywrightFactory.getPage();
    }

    public void loginApplication(){
        Page.NavigateOptions navigateOptions= new Page.NavigateOptions().setTimeout(20000);
        page.navigate(ConfigFileReader.getBaseUrl(), navigateOptions);
        page.locator(textUserName).fill("prasoonabtech@gmail.com");
        page.locator(textPassword).fill("Fortune@2808");
        page.locator(buttonLogin).click();
    }

}

