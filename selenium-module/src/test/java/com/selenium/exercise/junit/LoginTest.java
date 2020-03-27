package com.selenium.exercise.junit;

import static org.hamcrest.MatcherAssert.assertThat;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.selenium.exercise.pages.BasePage;
import com.selenium.exercise.pages.FindFlightsPage;
import com.selenium.exercise.pages.RegisterPage;
import com.selenium.exercise.pages.RegistrationConfirmationPage;

public class LoginTest {

    private String homeUrl = "http://newtours.demoaut.com/";
    private String registerUrl = "http://newtours.demoaut.com/mercuryregister.php";
    private String registerConfirmationPageUrl = "http://newtours.demoaut.com/create_account_success.php";
    private String flightsPageUrl = "http://newtours.demoaut.com/mercuryreservation.php";
    private String flightSearchResultsPargeUrl = "http://newtours.demoaut.com/mercuryreservation2.php";
    private String bookFlightUrl = "http://newtours.demoaut.com/mercurypurchase.php";
    
    
    private String firstName = "testUser9";
    private String lastName = "testUser9";
    private String phone = "testUser9";
    private String email = "testUser9";
    private String address = "testUser9";
    private String city = "Arlington";
    private String state = "VA";
    private String zip = "22210";
    private String userName = "testUser9";
    private String password = "testUser9";

    private BasePage basePage;
    
    @Before
    public void before() {
        basePage = new BasePage();
    }
    
    @Test
    @Ignore
    public void testLoginOnChrome() {

        WebDriver driver = setupChromeDriver();
        // register a new user to be able to do anything
        registerUser(driver);

    }

    @Test
    public void testGotoFlightsPageOnChrome() {

        WebDriver driver = setupChromeDriver();
        // register a new user to be able to do anything
        registerUser(driver);
        driver.findElement(By.linkText("Flights")).click();
        assertThat(driver.getCurrentUrl(), org.hamcrest.Matchers.containsString(flightsPageUrl));

    }

    @Test
    @Ignore
    public void testFieldsOnFlightsPageOnChrome() {

        WebDriver driver = setupChromeDriver();
        // register a new user to be able to do anything
        registerUser(driver);

        // goto flights page by clicking link
        driver.findElement(By.linkText("Flights")).click();
        assertThat(driver.getCurrentUrl(), org.hamcrest.Matchers.containsString(flightsPageUrl));
        ;

        WebElement departingFromSelectWebElement = driver.findElement(By.name("fromPort"));

        Select departingFromSelect = new Select(departingFromSelectWebElement);

        List<String> departingFromValues = departingFromSelect.getOptions().stream().map(el -> el.getText())
                .collect(Collectors.toList());
        assertThat(departingFromValues, org.hamcrest.Matchers.not(org.hamcrest.Matchers.empty()));
        assertThat(departingFromValues, org.hamcrest.Matchers.hasItems("New York", "London"));

    }

    @Test
    public void testSearchFlightsPageOnRandomBrowser() throws IOException {
        WebDriver driver = (System.currentTimeMillis() % 2)== 1 ? setupFirefoxDriver() : setupFirefoxDriver();
        // register a new user to be able to do anything
        registerUser(driver);

        // goto flights page by clicking link
        driver.findElement(By.linkText("Flights")).click();
        assertThat(driver.getCurrentUrl(), org.hamcrest.Matchers.containsString(flightsPageUrl));

        // 1 passenger depart from New York on March 31 to Paris and returning on April
        // 4 in Business class with no airline preference

        WebElement departingFromSelectWebElement = driver.findElement(By.name("fromPort"));

        Select departingFromSelect = new Select(departingFromSelectWebElement);
        departingFromSelect.selectByValue("New York");

        Select departingFromMonthSelect = new Select(driver.findElement(By.name("fromMonth")));
        departingFromMonthSelect.selectByVisibleText("March");
        Select departingFromDaySelect = new Select(driver.findElement(By.name("fromDay")));
        departingFromDaySelect.selectByVisibleText("31");
        Select arrivingToSelect = new Select(driver.findElement(By.name("toPort")));
        arrivingToSelect.selectByVisibleText("Paris");
        Select arrivingToMonthSelect = new Select(driver.findElement(By.name("toMonth")));
        arrivingToMonthSelect.selectByVisibleText("April");
        Select arrivingToDaySelect = new Select(driver.findElement(By.name("toDay")));
        arrivingToDaySelect.selectByVisibleText("4");

        // select flight class
        driver.findElement(By.cssSelector("input[name='servClass'][value='Business']")).click();

        // hit submit
        driver.findElement(By.name("findFlights")).click();
        new WebDriverWait(driver, 5).until(ExpectedConditions.urlContains(flightSearchResultsPargeUrl));
        org.hamcrest.MatcherAssert.assertThat(driver.getCurrentUrl(),
                org.hamcrest.Matchers.containsString(flightSearchResultsPargeUrl));

        // Make sure it says departing New York to Paris
        org.hamcrest.MatcherAssert.assertThat(driver.findElement(By.xpath(
                "/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table[1]/tbody/tr[1]/td/table/tbody/tr[2]/td[1]/b/font"))
                .getText(), org.hamcrest.Matchers.is("New York to Paris"));

        org.hamcrest.MatcherAssert.assertThat(driver.findElement(By.cssSelector(
                "body > div > table > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(4) > td > table > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(5) > td > form > table:nth-child(9) > tbody > tr:nth-child(1) > td > table > tbody > tr:nth-child(2) > td:nth-child(2) > b > font"))
                .getText(), org.hamcrest.Matchers.is("3/31/2020"));

        //Take a screnshot to confirm order and color
        //because getting the values from the webdriver requires absolute paths
        TakesScreenshot takesScreenshot = ((TakesScreenshot) driver);

        File flightSelectPageScreenshotFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

        File saveFlightSelectPageScreenshotFile = new File(
                "src/test/resources/screenshots/flightSelectPageScreenshot.jpg");

        Files.move(flightSelectPageScreenshotFile.toPath(), saveFlightSelectPageScreenshotFile.toPath(),
                StandardCopyOption.REPLACE_EXISTING);
        //driver.quit();
    }

    @Test
    @Ignore
    public void testBookFlightsPageOnRandomBrowser() throws IOException {
        WebDriver driver = (System.currentTimeMillis() % 2)== 1 ? setupFirefoxDriver() : setupChromeDriver();
        // register a new user to be able to do anything
        registerUser(driver);

        // goto flights page by clicking link
        driver.findElement(By.linkText("Flights")).click();
        assertThat(driver.getCurrentUrl(), org.hamcrest.Matchers.containsString(flightsPageUrl));

        // 1 passenger depart from New York on March 31 to Paris and returning on April
        // 4 in Business class with no airline preference

        WebElement departingFromSelectWebElement = driver.findElement(By.name("fromPort"));

        Select departingFromSelect = new Select(departingFromSelectWebElement);
        departingFromSelect.selectByValue("New York");
        Select departingFromMonthSelect = new Select(driver.findElement(By.name("fromMonth")));
        departingFromMonthSelect.selectByVisibleText("March");
        Select departingFromDaySelect = new Select(driver.findElement(By.name("fromDay")));
        departingFromDaySelect.selectByVisibleText("31");
        
        Select arrivingToSelect = new Select(driver.findElement(By.name("toPort")));
        arrivingToSelect.selectByVisibleText("Paris");
        Select arrivingToMonthSelect = new Select(driver.findElement(By.name("toMonth")));
        arrivingToMonthSelect.selectByVisibleText("April");
        Select arrivingToDaySelect = new Select(driver.findElement(By.name("toDay")));
        arrivingToDaySelect.selectByVisibleText("4");

        // select flight class
        driver.findElement(By.cssSelector("input[name='servClass'][value='Business']")).click();

        // hit submit
        driver.findElement(By.name("findFlights")).click();
        new WebDriverWait(driver, 5).until(ExpectedConditions.urlContains(flightSearchResultsPargeUrl));
        org.hamcrest.MatcherAssert.assertThat(driver.getCurrentUrl(),
                org.hamcrest.Matchers.containsString(flightSearchResultsPargeUrl));
        
        //Select the 7:10 departing flight
        Optional<WebElement> departingFlightOptional = driver.findElements(By.name("outFlight")).stream().filter(el->el.getAttribute("value").contains("7:10")).findFirst();
        assertThat("No 7:10 departing flight found", departingFlightOptional.isPresent());
        
        departingFlightOptional.get().click();
        
        //Select the 16:37 departing flight
        Optional<WebElement> returningFlightOptional = driver.findElements(By.name("inFlight")).stream().filter(el->el.getAttribute("value").contains("16:37")).findFirst();
        assertThat("No 16:37 departing flight found", returningFlightOptional.isPresent());
        
        returningFlightOptional.get().click();

        // hit continue
        driver.findElement(By.name("reserveFlights")).click();
        new WebDriverWait(driver, 5).until(ExpectedConditions.urlContains(bookFlightUrl));
        org.hamcrest.MatcherAssert.assertThat(driver.getCurrentUrl(),
                org.hamcrest.Matchers.containsString(bookFlightUrl));
        
        //Take a screnshot to confirm details
        //because getting the values from the webdriver requires absolute paths
        TakesScreenshot takesScreenshot = ((TakesScreenshot) driver);

        File bookFlightPageScreenshotFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

        File saveBookFlightPageScreenshotFile = new File(
                "src/test/resources/screenshots/bookFlightPageScreenshot.jpg");

        Files.move(bookFlightPageScreenshotFile.toPath(), saveBookFlightPageScreenshotFile.toPath(),
                StandardCopyOption.REPLACE_EXISTING);
        // driver.quit();
        
    }
    
    @Test
    public void testBookFlightsPageOnRandomBrowserUsingPages() throws IOException {
        WebDriver driver = (System.currentTimeMillis() % 2)== 1 ? setupFirefoxDriver() : setupChromeDriver();
        
        // register a new user to be able to do anything
        //registerUser(driver);
        driver.get(registerUrl);
        RegisterPage registerPage = PageFactory.initElements(driver, RegisterPage.class);
      //  registerPage.registerUser();
        
        //Move to Page Class
        new WebDriverWait(driver, 5).until(ExpectedConditions.urlContains(registerConfirmationPageUrl));
        org.hamcrest.MatcherAssert.assertThat(driver.getCurrentUrl(),
                org.hamcrest.Matchers.containsString(registerConfirmationPageUrl));
        
        
        // goto flights page by clicking link
        RegistrationConfirmationPage registerConfirmPage = PageFactory.initElements(driver, RegistrationConfirmationPage.class);
        registerConfirmPage.gotoFlightsPage();
        assertThat(driver.getCurrentUrl(), org.hamcrest.Matchers.containsString(flightsPageUrl));

        // 1 passenger depart from New York on March 31 to Paris and returning on April
        // 4 in Business class with no airline preference

        FindFlightsPage findFlightsPage = PageFactory.initElements(driver, FindFlightsPage.class);
        findFlightsPage.setSearchFields("New York", "March", "31", "Paris", "April", "4");

        // select flight class
        driver.findElement(By.cssSelector("input[name='servClass'][value='Business']")).click();

        // hit submit, Move the submit and wait to Page Class
        driver.findElement(By.name("findFlights")).click();
        new WebDriverWait(driver, 5).until(ExpectedConditions.urlContains(flightSearchResultsPargeUrl));
        
        
        org.hamcrest.MatcherAssert.assertThat(driver.getCurrentUrl(),
                org.hamcrest.Matchers.containsString(flightSearchResultsPargeUrl));
        
        //Select the 7:10 departing flight
        Optional<WebElement> departingFlightOptional = driver.findElements(By.name("outFlight")).stream().filter(el->el.getAttribute("value").contains("7:10")).findFirst();
        assertThat("No 7:10 departing flight found", departingFlightOptional.isPresent());
        
        departingFlightOptional.get().click();
        
        //Select the 16:37 departing flight
        Optional<WebElement> returningFlightOptional = driver.findElements(By.name("inFlight")).stream().filter(el->el.getAttribute("value").contains("16:37")).findFirst();
        assertThat("No 16:37 departing flight found", returningFlightOptional.isPresent());
        
        returningFlightOptional.get().click();

        // hit continue
        driver.findElement(By.name("reserveFlights")).click();
        new WebDriverWait(driver, 5).until(ExpectedConditions.urlContains(bookFlightUrl));
        org.hamcrest.MatcherAssert.assertThat(driver.getCurrentUrl(),
                org.hamcrest.Matchers.containsString(bookFlightUrl));
        
        //Take a screnshot to confirm details
        //because getting the values from the webdriver requires absolute paths
        TakesScreenshot takesScreenshot = ((TakesScreenshot) driver);

        File bookFlightPageScreenshotFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

        File saveBookFlightPageScreenshotFile = new File(
                "src/test/resources/screenshots/bookFlightPageScreenshot.jpg");

        Files.move(bookFlightPageScreenshotFile.toPath(), saveBookFlightPageScreenshotFile.toPath(),
                StandardCopyOption.REPLACE_EXISTING);
        // driver.quit();
        
    }
    private void registerUser(WebDriver driver) {
        driver.get(registerUrl);
        driver.findElement(By.name("firstName")).sendKeys(firstName);
        driver.findElement(By.name("lastName")).sendKeys(lastName);
        driver.findElement(By.name("phone")).sendKeys(phone);
        driver.findElement(By.name("userName")).sendKeys(email);
        driver.findElement(By.name("address1")).sendKeys(address);
        driver.findElement(By.name("city")).sendKeys(city);
        driver.findElement(By.name("state")).sendKeys(state);
        driver.findElement(By.name("postalCode")).sendKeys(zip);
        driver.findElement(By.name("email")).sendKeys(userName);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("confirmPassword")).sendKeys(password);
        driver.findElement(By.name("register")).submit();

        new WebDriverWait(driver, 5).until(ExpectedConditions.urlContains(registerConfirmationPageUrl));
        org.hamcrest.MatcherAssert.assertThat(driver.getCurrentUrl(),
                org.hamcrest.Matchers.containsString(registerConfirmationPageUrl));
    }
    private void registerUserWithPages(WebDriver driver) {
        driver.get(registerUrl);
        RegisterPage registerPage =  new RegisterPage(driver);
        
        registerPage.getFirstName().sendKeys(firstName);
        registerPage.getLastName().sendKeys(lastName);
        registerPage.getPhone().sendKeys(phone);
        registerPage.getEmail().sendKeys(email);
        registerPage.getAddress1().sendKeys(address);
        registerPage.getCity().sendKeys(city);
        registerPage.getState().sendKeys(state);
        registerPage.getZipCode().sendKeys(zip);
        registerPage.getUserName().sendKeys(userName);
        registerPage.getPassword().sendKeys(password);
        registerPage.getConfirmPassword().sendKeys(password);
        registerPage.getRegisterButton().submit();

        registerPage.waitUntilUrlContains(driver, registerConfirmationPageUrl);
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
}
