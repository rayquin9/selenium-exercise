package com.selenium.exercise.steps;

import static org.junit.Assert.fail;

import java.util.List;
import java.util.Map;

import com.selenium.exercise.constants.LoginForm;
import com.selenium.exercise.pages.RegisterPage;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class RegisterUserSteps {

    private static final String registerUrl = "http://newtours.demoaut.com/mercuryregister.php";
    private static final String registerConfirmationPageUrl = "http://newtours.demoaut.com/create_account_success.php";

    private BaseSteps baseSteps;

    public RegisterUserSteps(BaseSteps baseSteps) {
        this.baseSteps = baseSteps;
    }

    @And("^I log into the application with the following values:$")
    public void loginWithDetails(DataTable loginValues) {
        List<Map<String, String>> rows = loginValues.asMaps(String.class, String.class);
        loginWithDetails(rows);
    }

    @Then("^I check that I am on the Registered User Page$")
    public void checkRegisteredPageUrl() {
        org.hamcrest.MatcherAssert.assertThat(baseSteps.getDriver().getCurrentUrl(),
                org.hamcrest.Matchers.containsString(registerConfirmationPageUrl));
    }

    private void loginWithDetails(List<Map<String, String>> detailRows) {
        baseSteps.getDriver().get(registerUrl);
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
                fail("Invalid Login form Field");
            }
        }

        registerPage.getRegisterButton().submit();
        registerPage.waitUntilUrlContains(baseSteps.getDriver(), registerConfirmationPageUrl);
    }
}
