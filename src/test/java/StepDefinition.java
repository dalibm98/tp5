import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class StepDefinition {
    private WebDriver driver;
    private FirefoxBinary firefoxBinary;

    @Before
    public void createWebDriver() {
        firefoxBinary = new FirefoxBinary();
    }

    @After
    public void quitWebDriver() {
        driver.quit();
    }

    @Given("i open google search page")
    public void i_open_google_search_page() {

        firefoxBinary.addCommandLineOptions("--headless");
        firefoxBinary.addCommandLineOptions("--no-sandbox");
        System.setProperty("webdriver.gecko.driver", "/usr/bin/geckodriver");
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setBinary(firefoxBinary);

        driver = new FirefoxDriver(firefoxOptions);

        driver.get("https://www.google.fr");
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