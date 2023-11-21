
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;

import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

@RunWith(Cucumber.class)

public class StepDefinition {
    public WebDriver driver;
    public FirefoxBinary firefoxBinary;

 @Before
    public void createWebDriver() {
        firefoxBinary = new FirefoxBinary();
        firefoxBinary.addCommandLineOptions("--headless", "--no-sandbox");
    }

    @After
    public void quitWebDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("I open the Google search page")
    public void i_open_google_search_page() {
        System.setProperty("webdriver.gecko.driver", "/usr/local/bin/geckodriver");

        // Use the full path to the Firefox executable
        String firefoxPath = "/mnt/c/Program Files/Firefox Developer Edition/firefox.exe";
        System.setProperty("webdriver.firefox.bin", firefoxPath);

        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setBinary(firefoxBinary);

        driver = new FirefoxDriver(firefoxOptions);
        driver.get("https://www.google.com");
    }
    @When("lookup the word {string}")
    public void lookup_the_word(String string) {
        driver.findElement(By.name("q")).clear();
        driver.findElement(By.name("q")).sendKeys(string);
        driver.findElement(By.name("btnk")).click();
    }
    @Then("Serach results disppalt {string}")
    public void serach_results_disppalt(String string) {
        System.out.println(driver.getTitle().contains(string));

    }
}
