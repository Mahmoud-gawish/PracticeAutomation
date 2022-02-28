package TestCases;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

public class WindowManagement {
    WebDriver driver;
    public static Properties properties;
    static {
        properties = ReadProperties("./src/test/resources/Properties/testData.properties");
    }

    public static Properties ReadProperties(String FilePath) {

        try {
            FileInputStream testProperties = new FileInputStream(FilePath);
            properties = new Properties();
            properties.load(new InputStreamReader(testProperties, Charset.forName("ISO-8859-1")));
            return properties;
        } catch (FileNotFoundException e) {
            //logger.error("Test base Constractor " + e.getMessage());
        } catch (IOException e) {
            //logger.error("Test base Constractor " + e.getMessage());
        }
        return properties;
    }
    @BeforeMethod
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://automationpractice.com/index.php");
        System.out.println("Title " + driver.getTitle());

    }

    @Test
    public void tetNewWindowTap(){
        WebDriver newWindow = driver.switchTo().newWindow(WindowType.WINDOW);
        newWindow.get("http://automationpractice.com/index.php?controller=prices-drop");
        System.out.println("Title: " + driver.getTitle());

    }

    @Test
    public void testWorkingInBothWindow(){
        // Automatically open & Switch To The New Window ot Tap
        driver.switchTo().newWindow(WindowType.TAB)
                .get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
        System.out.println("Title: " + driver.getTitle());
        // Work In The New Window Or Tap
        driver.findElement(By.id("email_create")).sendKeys(properties.getProperty("email"));
        driver.findElement(By.id("SubmitCreate")).click();
        // Get The Window ID Handles
        Set<String> allWindowTabs = driver.getWindowHandles();
        Iterator<String> iterate = allWindowTabs.iterator();
        String mainFirstWindow = iterate.next();
        // Switch & Work In The Main Window Or Tap
        driver.switchTo().window(mainFirstWindow);
        driver.findElement(By.id("search_query_top")).sendKeys("shirt");
        driver.findElement(By.name("submit_search")).click();
        System.out.println("Title: " + driver.getTitle());
    }
}
