package com.selenium.exercise.steps;

import static org.junit.Assert.fail;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.selenium.exercise.constants.LoginForm;
import com.selenium.exercise.pages.RegisterPage;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class RegisterUserSteps {

    private static final String registerUrl = "http://newtours.demoaut.com/mercuryregister.php";
    private static final String registerConfirmationPageUrl = "http://newtours.demoaut.com/create_account_success.php";

    WebDriver driver = null;

    @Given("^I am in \"Chrome\"$")
    public void useChrome() {
        driver = setupChromeDriver();
    }

    @Given("^I am in \"Firefox\"$")
    public void useFirefox() {
        driver = setupFirefoxDriver();
    }

    @And("^I log into the application with the following values:$")
    public void loginWithDetails(DataTable loginValues) {
        List<Map<String, String>> rows = loginValues.asMaps(String.class, String.class);
        loginWithDetails(rows);
    }

    @Then("^I close the browser$")
    public void closeDriver() {
        driver.quit();
    }

    @Then("^I check that I am on the Registered User Page$")
    public void checkRegisteredPageUrl() {
        org.hamcrest.MatcherAssert.assertThat(driver.getCurrentUrl(),
                org.hamcrest.Matchers.containsString(registerConfirmationPageUrl));
    }

    private WebDriver setupChromeDriver() {
        String path = "src/test/resources/drivers/chromedriver.exe";

        File file = new File(path);
        String absolutePath = file.getAbsolutePath();
        System.setProperty("webdriver.chrome.driver", absolutePath);
        return new ChromeDriver();
    }

    private WebDriver setupFirefoxDriver() {
        String path = "src/test/resources/drivers/geckodriver.exe";

        File file = new File(path);
        String absolutePath = file.getAbsolutePath();
        System.setProperty("webdriver.gecko.driver", absolutePath);
        return new FirefoxDriver();
    }

    private void loginWithDetails(List<Map<String, String>> detailRows) {
        driver.get(registerUrl);
        RegisterPage registerPage = new RegisterPage(driver);
        for (Map<String, String> map : detailRows) {
            String valueString = map.get("Value");
            switch (LoginForm.from(map.get("Element"))) {
            case FIRST_NAME:
                registerPage.getFirstName().sendKeys(valueString);
                break;
            case LAST_NAME:
                registerPage.getLastName().sendKeys(valueString);
                break;
            case PHONE:
                registerPage.getPhone().sendKeys(valueString);
                break;
            case EMAIL:
                registerPage.getEmail().sendKeys(valueString);
                break;
            case ADDRESS:
                registerPage.getAddress1().sendKeys(valueString);
                break;
            case CITY:
                registerPage.getCity().sendKeys(valueString);
                break;
            case STATE:
                registerPage.getState().sendKeys(valueString);
                break;
            case ZIP:
                registerPage.getZipCode().sendKeys(valueString);
                break;
            case USER_ID:
                registerPage.getUserName().sendKeys(valueString);
                break;
            case PASS:
                registerPage.getPassword().sendKeys(valueString);
                registerPage.getConfirmPassword().sendKeys(valueString);
                break;
            default:
                fail("Invalid Login form Field");
            }
        }

        registerPage.getRegisterButton().submit();
        registerPage.waitUntilUrlContains(driver, registerConfirmationPageUrl);
    }
}
