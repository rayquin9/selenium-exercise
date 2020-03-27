package com.selenium.exercise.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class RegistrationConfirmationPage {

	@FindBy(how = How.LINK_TEXT, using= "Flights")
	private WebElement flightsPageLink;
	
	public void gotoFlightsPage() {
		flightsPageLink.click();
	}
}
