package steps;

import base.BaseUtil;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.testng.AssertJUnit.assertTrue;

public class Login extends BaseUtil {


    private BaseUtil baseutil;

    public Login(BaseUtil util) {
        this.baseutil = util;

    }

    private WebDriver driver;

    @Before
    public void seetup() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Given("i am in the login page")
    @Given("i am in the login page of the para bank Application")
    public void i_am_in_the_login_page_of_the_para_bank_Application() {

        driver.get("https://parabank.parasoft.com/parabank/index.htm?ConnType=JDBC");

    }

    @When("I enter a valid {string} and {string} with {string}")
    public void i_enter_a_valid_cridential(String username, String password, String userFullName1) {

        baseutil.userFullName = userFullName1;
        driver.findElement(By.name("username")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("username")).submit();

    }

    @Then("I should be taken to the Overview page")
    public void i_should_be_taken_to_the_Overview_page() {

        String actualuserFullName = driver.findElement(By.className("smallText")).getText().toString();
        System.out.println(baseutil.userFullName.toString());
        assertTrue(actualuserFullName, actualuserFullName.contains(baseutil.userFullName));

        driver.findElement(By.xpath("//h1[@class=\"title\"]")).isDisplayed();
        driver.findElement(By.linkText("Log Out")).click();

    }

    @After
    public void quitbrowser() {
        driver.quit();

    }
}
