package com.selenium.exercise.steps;

import static org.junit.Assert.fail;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.selenium.exercise.constants.FlightForm;
import com.selenium.exercise.pages.FindFlightsPage;
import com.selenium.exercise.pages.RegistrationConfirmationPage;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class FindFlightSteps {

    private static final String flightsPageUrl = "http://newtours.demoaut.com/mercuryreservation.php";
    private static final String flightSearchResultsPargeUrl = "http://newtours.demoaut.com/mercuryreservation2.php";

    private BaseSteps baseSteps;

    public FindFlightSteps(BaseSteps baseSteps) {
        this.baseSteps = baseSteps;
    }

    @When("^the User searches for a \"([^\"]*)\" \"([^\"]*)\" class flight with the following values:$")
    public void flightSearch(String flightType, String flightClass, DataTable searchValues) {
        System.out.println("Flight type: " + flightType);
        System.out.println("Flight class: " + flightClass);

        List<Map<String, String>> rows = searchValues.asMaps(String.class, String.class);
        System.out.println(rows);

        findFlight(flightType, flightClass, rows);
    }

    @And("^moves to the Flights Page$")
    public void clickFlightsLink() {
        RegistrationConfirmationPage confirmationPage = new RegistrationConfirmationPage(baseSteps.getDriver());
        confirmationPage.gotoFlightsPage();
        org.hamcrest.MatcherAssert.assertThat(baseSteps.getDriver().getCurrentUrl(),
                org.hamcrest.Matchers.containsString(flightsPageUrl));
    }

    @Then("^they should see flight results$")
    public void confirmOnFlightResultsPage() {
        org.hamcrest.MatcherAssert.assertThat(baseSteps.getDriver().getCurrentUrl(),
                org.hamcrest.Matchers.containsString(flightSearchResultsPargeUrl));
    }

    private void findFlight(String flightType, String flightClass, List<Map<String, String>> rows) {
        FindFlightsPage page = new FindFlightsPage(baseSteps.getDriver());

        page.selectFlightClass(flightClass);
        setTripType(flightType, page);
        selectFlightDetails(rows, page);

        page.getSubmitButton().click();
        new WebDriverWait(baseSteps.getDriver(), 5).until(ExpectedConditions.urlContains(flightSearchResultsPargeUrl));
    }


    private void setTripType(String flightType, FindFlightsPage page) {
        if ("ROUNDTRIP".equals(flightType)) {
            page.getRoundTripRadioButton().click();
        } else if ("ONE WAY".equals(flightType)) {
            page.getOneWayRadioButton().click();
        } else {
            fail("Invalid trip type");
        }
    }

    private void selectFlightDetails(List<Map<String, String>> detailRows, FindFlightsPage page) {
        int i = 0;
        String[] dateStuff;
        String month, day;
        for (Map<String, String> map : detailRows) {
            String valueString = map.get("Value");
            switch (FlightForm.from(map.get("Element"))) {
            case DEPART_CITY:
                Select fromCitySelect = new Select(page.getFromCitySelector());
                i = page.findIndexByValue(fromCitySelect, valueString);
                fromCitySelect.selectByIndex(i);
                break;
            case ARRIVAL_CITY:
                Select toCitySelect = new Select(page.getToCitySelector());
                i = page.findIndexByValue(toCitySelect, valueString);
                toCitySelect.selectByIndex(i);
                break;
            case DEPART_DATE:
                dateStuff = valueString.split(" ");
                month = dateStuff[0];
                day = dateStuff[1];
                Select departMonthSelect = new Select(page.getFromMonthSelector());
                i = page.findIndexByText(departMonthSelect, month);
                departMonthSelect.selectByIndex(i);
                Select departDaySelect = new Select(page.getFromDaySelector());
                i = page.findIndexByText(departDaySelect, day);
                departDaySelect.selectByIndex(i);
                break;
            case RETURN_DATE:
                dateStuff = valueString.split(" ");
                month = dateStuff[0];
                day = dateStuff[1];
                Select returnMonthSelect = new Select(page.getReturnMonthSelector());
                i = page.findIndexByText(returnMonthSelect, month);
                returnMonthSelect.selectByIndex(i);
                Select returnDaySelect = new Select(page.getReturnDaySelector());
                i = page.findIndexByText(returnDaySelect, day);
                returnDaySelect.selectByIndex(i);
                break;
            default:
                fail("Invalid form Field");
            }
        }
    }
}
