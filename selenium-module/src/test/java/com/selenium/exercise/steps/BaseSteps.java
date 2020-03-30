package com.selenium.exercise.steps;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class BaseSteps {

    private WebDriver driver = null;

    @Given("^I am in \"Chrome\"$")
    public void useChrome() {
        driver = setupChromeDriver();
    }

    @Given("^I am in \"Firefox\"$")
    public void useFirefox() {
        driver = setupFirefoxDriver();
    }

    @Then("^I close the browser$")
    public void closeDriver() {
        driver.quit();
    }

    @And("^take a screenshot and save it to \"([^\"]*)\"$")
    public void takeScreenshot(String fileLocation) throws IOException {
        TakesScreenshot takesScreenshot = ((TakesScreenshot) driver);

        File screenshotFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

        File saveScreenshotFile = new File("src/test/resources/screenshots/" + fileLocation + ".jpg");

        Files.move(screenshotFile.toPath(), saveScreenshotFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
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

    public WebDriver getDriver() {
        return driver;
    }

}
