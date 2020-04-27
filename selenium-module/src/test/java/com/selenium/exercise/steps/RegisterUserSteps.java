package com.selenium.exercise.steps;

import static org.junit.Assert.fail;

import java.util.List;
import java.util.Map;

import com.selenium.exercise.constants.LoginForm;
import com.selenium.exercise.pages.RegisterPage;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class RegisterUserSteps {

    private static final String INVALID_LOGIN_FORM_FIELD = "Invalid Login form Field";
    private static final String registerUrl = "http://newtours.demoaut.com/mercuryregister.php";
    private static final String registerConfirmationPageUrl = "http://newtours.demoaut.com/create_account_success.php";

    private BaseSteps baseSteps;

    public RegisterUserSteps(BaseSteps baseSteps) {
        this.baseSteps = baseSteps;
    }

    @Given("^the User logs into the application$")
    public void loginWithTesterProfile() {
        baseSteps.getDriver().get(registerUrl);
        
        RegisterPage registerPage = new RegisterPage(baseSteps.getDriver());
        registerPage.getFirstName().sendKeys(baseSteps.getProperty("tester.firstName"));
        registerPage.getLastName().sendKeys(baseSteps.getProperty("tester.lastName"));
        registerPage.getPhone().sendKeys(baseSteps.getProperty("tester.phone"));
        registerPage.getEmail().sendKeys(baseSteps.getProperty("tester.email"));
        registerPage.getAddress1().sendKeys(baseSteps.getProperty("tester.address"));
        registerPage.getCity().sendKeys(baseSteps.getProperty("tester.city"));
        registerPage.getState().sendKeys(baseSteps.getProperty("tester.stateCode"));
        registerPage.getZipCode().sendKeys(baseSteps.getProperty("tester.zip"));
        registerPage.getUserName().sendKeys(baseSteps.getProperty("tester.userId"));
        registerPage.getPassword().sendKeys(baseSteps.getProperty("tester.password"));
        registerPage.getConfirmPassword().sendKeys(baseSteps.getProperty("tester.password"));
        
        registerPage.getRegisterButton().submit();
        registerPage.waitUntilUrlContains(baseSteps.getDriver(), registerConfirmationPageUrl);
    }
    
    @Given("^the User is on the registration page$")
    public void moveToRegistrationPage() {
        baseSteps.getDriver().get(registerUrl);
    }
    
    @And("^they log into the application with the following values:$")
    public void loginWithDetails(DataTable loginValues) {
        List<Map<String, String>> rows = loginValues.asMaps(String.class, String.class);
        loginWithDetails(rows);
    }

    @Then("^the User should be on the Registered User Page$")
    public void checkRegisteredPageUrl() {
        org.hamcrest.MatcherAssert.assertThat(baseSteps.getDriver().getCurrentUrl(),
                org.hamcrest.Matchers.containsString(registerConfirmationPageUrl));
    }

    private void loginWithDetails(List<Map<String, String>> detailRows) {
        RegisterPage registerPage = new RegisterPage(baseSteps.getDriver());
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
                fail(INVALID_LOGIN_FORM_FIELD);
            }
        }

        registerPage.getRegisterButton().submit();
        registerPage.waitUntilUrlContains(baseSteps.getDriver(), registerConfirmationPageUrl);
    }
}
