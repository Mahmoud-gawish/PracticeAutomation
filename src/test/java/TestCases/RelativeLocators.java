package TestCases;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class RelativeLocators {
    WebDriver driver;

    @BeforeMethod
    public void setuo(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/");

    }

    @Test
    public void testRelativeLOcator(){

        WebElement loginPanel = driver.findElement(By.id("logInPanelHeading"));
        WebElement credential = driver.findElement(RelativeLocator.with(
                        By.tagName("span"))
                .above(loginPanel));
        System.out.println(credential.getText());

    }

    @Test
    public void testListOfElements(){

        List<WebElement> allSocialMedia = driver.findElements(with(
                By.tagName("img"))
                .near(By.id("footer")));

        for(WebElement socialMedia : allSocialMedia){
            System.out.println(socialMedia.getAttribute("alt"));
        }
    }

    @AfterMethod
    public void teardown(){
        driver.quit();
    }
}
