package com.selenium.exercise.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class FindFlightsPage extends BasePage {

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
    
    public FindFlightsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
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
}
