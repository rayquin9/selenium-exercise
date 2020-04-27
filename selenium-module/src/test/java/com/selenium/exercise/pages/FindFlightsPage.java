package com.selenium.exercise.pages;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class FindFlightsPage extends BasePage {

    private static final String FIRST_CLASS = "FIRST";
    private static final String COACH_CLASS = "COACH";
    private static final String BUSINESS_CLASS = "BUSINESS";

    @FindBy(how = How.NAME, using = "fromPort")
    private WebElement fromCitySelector;

    @FindBy(how = How.NAME, using = "fromMonth")
    private WebElement fromMonthSelector;

    @FindBy(how = How.NAME, using = "fromDay")
    private WebElement fromDaySelector;

    @FindBy(how = How.NAME, using = "toPort")
    private WebElement toCitySelector;

    @FindBy(how = How.NAME, using = "toMonth")
    private WebElement returnMonthSelector;

    @FindBy(how = How.NAME, using = "toDay")
    private WebElement returnDaySelector;

    @FindBy(how = How.CSS, using = "input[name='servClass'][value='Business']")
    private WebElement businessClassRadioButton;

    @FindBy(how = How.CSS, using = "input[name='servClass'][value='Coach']")
    private WebElement coachClassRadioButton;

    @FindBy(how = How.CSS, using = "input[name='servClass'][value='First']")
    private WebElement firstClassRadioButton;

    @FindBy(how = How.CSS, using = "input[name='tripType'][value='roundtrip']")
    private WebElement roundTripRadioButton;

    @FindBy(how = How.CSS, using = "input[name='tripType'][value='oneway']")
    private WebElement oneWayRadioButton;

    @FindBy(how = How.NAME, using = "findFlights")
    private WebElement submitButton;

    private Map<String, WebElement> flightClassElements;

    public FindFlightsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.flightClassElements = new HashMap<String, WebElement>();
        this.flightClassElements.put(BUSINESS_CLASS, businessClassRadioButton);
        this.flightClassElements.put(COACH_CLASS, coachClassRadioButton);
        this.flightClassElements.put(FIRST_CLASS, firstClassRadioButton);
    }

    public WebElement getFromCitySelector() {
        return fromCitySelector;
    }

    public WebElement getFromMonthSelector() {
        return fromMonthSelector;
    }

    public WebElement getFromDaySelector() {
        return fromDaySelector;
    }

    public WebElement getToCitySelector() {
        return toCitySelector;
    }

    public WebElement getReturnMonthSelector() {
        return returnMonthSelector;
    }

    public WebElement getReturnDaySelector() {
        return returnDaySelector;
    }

    public WebElement getBusinessClassRadioButton() {
        return businessClassRadioButton;
    }

    public WebElement getCoachClassRadioButton() {
        return coachClassRadioButton;
    }

    public WebElement getFirstClassRadioButton() {
        return firstClassRadioButton;
    }

    public WebElement getRoundTripRadioButton() {
        return roundTripRadioButton;
    }

    public WebElement getOneWayRadioButton() {
        return oneWayRadioButton;
    }

    public WebElement getSubmitButton() {
        return submitButton;
    }

    public void selectFlightClass(String inputClassType) {
        boolean foundClass = false;
        for (Entry<String, WebElement> flightClass : flightClassElements.entrySet()) {
            if (flightClass.getKey().equalsIgnoreCase(inputClassType)) {
                flightClass.getValue().click();
                foundClass = true;
                break;
            }
        }
        if (!foundClass) {
            throw new RuntimeException("Invalid flightclass type: " + inputClassType);
        }
    }
}
