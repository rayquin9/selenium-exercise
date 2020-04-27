package com.selenium.exercise.steps;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class BaseSteps {

    private WebDriver driver = null;
    private Properties properties;

    public BaseSteps() throws IOException {
        properties = new Properties();
        properties.load(this.getClass().getClassLoader().getResourceAsStream("cucumberTest.properties"));
    }

    @Before
    public void setupDriver() {
        String browserType = getProperty("browser");
        if ("Chrome".equalsIgnoreCase(browserType)) {
            driver = setupChromeDriver();
        } else if ("Firefox".equalsIgnoreCase(browserType)) {
            driver = setupFirefoxDriver();
        } else {
            throw new RuntimeException("Invalid browswer type given: " + browserType);

        }
    }

    @Then("^the browser gets closed$")
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
        String path = getProperty("chrome.driver");

        File file = new File(path);
        String absolutePath = file.getAbsolutePath();
        System.setProperty("webdriver.chrome.driver", absolutePath);
        return new ChromeDriver();
    }

    private WebDriver setupFirefoxDriver() {
        String path = getProperty("firefox.driver");

        File file = new File(path);
        String absolutePath = file.getAbsolutePath();
        System.setProperty("webdriver.gecko.driver", absolutePath);
        return new FirefoxDriver();
    }

    public WebDriver getDriver() {
        return driver;
    }

    public String getProperty(String name) {
        return properties.getProperty(name);
    }
}
