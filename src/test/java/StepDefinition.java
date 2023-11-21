import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
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
        // Créer une instance de FirefoxBinary pour spécifier le chemin du binaire Firefox
        firefoxBinary = new FirefoxBinary();
        firefoxBinary.addCommandLineOptions("--headless", "--no-sandbox");
    }

    @After
    public void quitWebDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("J'ouvre la page de recherche Google")
    public void i_open_google_search_page() {
        // Définir le chemin du driver Gecko
        System.setProperty("webdriver.gecko.driver", "/usr/local/bin/geckodriver");

        // Utiliser le chemin complet vers l'exécutable Firefox
        String firefoxPath = "/mnt/c/Program Files/Mozilla Firefox/firefox.exe";
        System.setProperty("webdriver.firefox.bin", firefoxPath);

        // Configurer les options de Firefox
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setBinary(firefoxBinary);

        // Initialiser le pilote Firefox
        driver = new FirefoxDriver(firefoxOptions);

        // Ouvrir la page Google
        driver.get("https://www.google.com");
    }

    @When("je recherche le mot {string}")
    public void lookup_the_word(String string) {
        // Effacer le champ de recherche, saisir le mot et cliquer sur le bouton de recherche
        driver.findElement(By.name("q")).clear();
        driver.findElement(By.name("q")).sendKeys(string);
        driver.findElement(By.name("btnk")).click();
    }

    @Then("les résultats de la recherche affichent {string}")
    public void search_results_display(String string) {
        // Afficher si le titre de la page contient la chaîne spécifiée
        System.out.println(driver.getTitle().contains(string));
    }
}
