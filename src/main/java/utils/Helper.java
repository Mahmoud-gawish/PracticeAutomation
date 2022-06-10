package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Random;
import java.util.logging.Logger;

import static org.testng.AssertJUnit.fail;

public class Helper {
    private  static  final int TIMEOUT = Integer
            .parseInt(PropertiesReader.getProperty("liveproject.properties","webdriver.wait"));

    public static WebDriverWait getExplicitWait(WebDriver driver){
        return new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
    }

    public static void implicitWait(WebDriver driver){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));
    }

    public static String getCurrentTime(String dateFormate){
        String currentTime = "" ;
        try {
            currentTime = new SimpleDateFormat(dateFormate).format(new Date());
        }catch (IllegalArgumentException e){
            Logger.getLogger(e.getMessage());
            fail(e.getMessage());
        }
        return currentTime;
    }

    public static String getCurrentTime() {
        return getCurrentTime("ddMMYYYYHHmmssSSS");
    }
    public static int getRandomNumberBetweenTwoValues(int lowValue, int highValue) {
        return new Random().nextInt(highValue - lowValue) + lowValue;
    }

    public static String getRandomNumberBetweenTwoValuesAsString(int lowValue, int highValue) {
        return Integer.toString(getRandomNumberBetweenTwoValues(lowValue, highValue));
    }
}
