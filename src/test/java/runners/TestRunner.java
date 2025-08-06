package runners;


import com.microsoft.playwright.Page;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import utils.ConfigFileReader;
import utils.PlaywrightFactory;
//import org.testng.annotations.Parameters;


@CucumberOptions(
        features = "src/test/resources/Features",
        glue = {"stepdefs"},
        plugin = {
                "pretty",
                "json:target/cucumber-reports/cucumber.json",
                "html:target/cucumber-reports/cucumber.html"
        },
        monochrome = true

)
public class TestRunner extends AbstractTestNGCucumberTests {


//    @Parameters("browser")
    @BeforeSuite
    public void setup() throws InterruptedException {
        String browser = "Chrome";
        System.out.println("🖥 Running tests on browser: " + browser);
        PlaywrightFactory.initPlaywright(browser);
        if (PlaywrightFactory.getPage() == null) {
            throw new RuntimeException("Playwright is not initialized properly");
        }
//        Page page = PlaywrightFactory.getPage();
//        page.waitForURL(ConfigFileReader.getBaseUrl());
//        page.navigate(ConfigFileReader.getBaseUrl());

//        Thread.sleep(6000);
//        page.waitForSelector("[alt='Automation Anywhere']", new Page.WaitForSelectorOptions().setTimeout(60000)); // 60 seconds
    }
//    @AfterAll
    public void tearDown() {
        PlaywrightFactory.closePlaywright();
    }


}
