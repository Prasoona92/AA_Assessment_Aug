package utils;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.microsoft.playwright.*;
import org.testng.Assert;

import java.util.function.Predicate;

public class PlaywrightFactory {

    private static Playwright playwright;
    private static Browser browser;
    private static BrowserContext context;
    private static Page page;

    public static void initPlaywright(String browserType) {
        if (playwright == null) {
            playwright = Playwright.create();

            switch (browserType.toLowerCase()) {
                case "firefox":
                    browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
                    break;
                case "webkit":  // ✅ WebKit (Safari)
                    browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
                    break;
                case "chrome":
                    browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
                    break;
                default:  // ✅ Default to Chromium (if browser is not specified)
                    browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
                    break;
            }

            context = browser.newContext();
            page = context.newPage();
            // Listen for the specific API request
            context.route("**/learninginstances/list", route -> {
                Request request = route.request();
                System.out.println("Intercepted API Request: " + request.url());
                route.request();
            });


//            Response response = page.onResponse("https://community.cloud.automationanywhere.digital/cognitive/v3/learninginstances/list",null);
//            System.out.println(response.text());
//            System.out.println(response.status());



        }
    }
    public static Page getPage() {
        if (page == null) {
            throw new RuntimeException("Playwright Page is NULL. Ensure Playwright is started before tests.");
        }
        return page;
    }

    public static void closePlaywright() {
        if (playwright != null) {
            playwright.close();
            playwright = null;
            browser = null;
            context = null;
            page = null;
        }
    }



}
