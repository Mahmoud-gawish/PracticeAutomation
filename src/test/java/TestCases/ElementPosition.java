package TestCases;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Properties;

public class ElementPosition {
    WebDriver driver;
    public Properties properties;

    @BeforeMethod
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://testautomationu.applitools.com/learningpaths.html");
    }

    @Test
    public void getPositionDimension(){
        WebElement logoTAU = driver.findElement(By.xpath("//div[@id='app']//header/a/img"));
        Rectangle rectTAULogo = logoTAU.getRect();
        System.out.println("x: " + rectTAULogo.getX());
        System.out.println("y: " + rectTAULogo.getY());
        System.out.println("Width: " + rectTAULogo.getWidth());
        System.out.println("Hight: " + rectTAULogo.getHeight());
    }
}
