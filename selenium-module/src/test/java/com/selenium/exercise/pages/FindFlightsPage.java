package com.selenium.exercise.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

public class FindFlightsPage {

	@FindBy(how = How.LINK_TEXT, using= "fromPort")
	private WebElement fromCitySelector;
	
	@FindBy(how = How.LINK_TEXT, using= "fromMonth")
	private WebElement fromMonthSelector;
	
	@FindBy(how = How.LINK_TEXT, using= "fromDay")
	private WebElement fromDaySelector;
	
	@FindBy(how = How.LINK_TEXT, using= "toPort")
	private WebElement toCitySelector;
	
	@FindBy(how = How.LINK_TEXT, using= "toMonth")
	private WebElement returnMonthSelector;
	
	@FindBy(how = How.LINK_TEXT, using= "toDay")
	private WebElement returnDaySelector;

	@FindBy(how = How.CSS, using= "input[name='servClass'][value='Business']")
	private WebElement businessClassRadioButton;

	
	public void setSearchFields(String departCity, String departMonth, String departDay,
			String toCity,String returnMonth,String returnDay) {
		new Select(fromCitySelector).selectByValue(departCity);
		new Select(fromMonthSelector).selectByValue(departMonth);
		new Select(fromDaySelector).selectByValue(departDay);
		new Select(toCitySelector).selectByValue(toCity);
		new Select(returnMonthSelector).selectByValue(returnMonth);
		new Select(returnDaySelector).selectByValue(returnDay);
	}
	
	public FindFlightsPage getInstance(WebDriver driver) {
		
	}
}
