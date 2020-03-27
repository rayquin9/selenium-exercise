package com.selenium.exercise.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class RegistrationConfirmationPage extends BasePage {

    @FindBy(how = How.LINK_TEXT, using = "Flights")
    private WebElement flightsPageLink;

    public void gotoFlightsPage() {
        flightsPageLink.click();
    }

    public RegistrationConfirmationPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
