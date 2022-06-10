package utils;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.testng.Assert.fail;


import org.openqa.selenium.Alert;


public class BrowserActions {

    static WebDriver driver;

    public enum ConfirmAlertType {
        ACCEPT, DISMISS;
    }

    public enum CookieBuilderType {
        ADD, DELETE;
    }

    // @Step("Navigate to url : [{url}]")
    public static void navigateToUrl(WebDriver driver ,String url ){
        try {
            Logger.logMessage("[Browser Action] Navigate to URL [" + url + "]");
            driver.get(url);
            ((JavascriptExecutor) driver).executeScript("return document.readState").equals("complete");

        }catch (Exception e){
            Logger.logMessage(e.getMessage());
            fail(e.getMessage());
        }
    }

    @Step("Close All Opened Browser Windows")
    public static void closeAllOpenedBrowserWindows(WebDriver driver){
       Logger.logMessage("[Browser Action] close all opened Browser Windows");
        if(driver != null){
            try {
                driver.quit();

            }catch (WebDriverException rootCauseException){
                Logger.logMessage(rootCauseException.getMessage());
            } finally {
                driver = null;
            }
        }else {
            Logger.logMessage("Window are already closed and the driver object is null.");
        }
    }

    @Step("Maximize the Browser Window")
    public static void maximizeWindow(WebDriver deiver){

        try {
            Logger.logMessage("[Browser Action] Maximize the Browser Window");
            deiver.manage().window().maximize();
        }catch (Exception e){
            Logger.logMessage(e.getMessage());
        }
    }

    @Step("Set the Window Resolution")
    public static void setWindowResolution(WebDriver driver){
        String width = PropertiesReader.getProperty("liveproject.properties","width");
        String height = PropertiesReader.getProperty("liveproject.properties","height");
        try {
            Logger.logMessage("[Browser Action] set Window Resolution as Width["+ width +"] and height["+ height +"]");
            Dimension dimension = new Dimension(Integer.parseInt(width), Integer.parseInt(height));
            driver.manage().window().setSize(dimension);
        }catch (Exception e){

            Logger.logMessage(e.getMessage());
        }

    }

    public static void confirmAlert(WebDriver driver, ConfirmAlertType confirmAlerType) {
        Helper.getExplicitWait(driver).until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        switch (confirmAlerType) {
            case ACCEPT:
                alert.accept();
                break;
            case DISMISS:
                Helper.getExplicitWait(driver).until(ExpectedConditions.alertIsPresent());
                alert.dismiss();
                break;
        }
    }


    public static void cookieBuilder(WebDriver driver, CookieBuilderType cookieBuilderType, String name, String value,
                                     String domain) {
        Cookie cookie = new Cookie.Builder(name, value).domain(domain).build();

        switch (cookieBuilderType) {
            case ADD:
                driver.manage().addCookie(cookie);
                break;
            case DELETE:
                driver.manage().deleteCookie(cookie);
                break;
        }
    }


}
