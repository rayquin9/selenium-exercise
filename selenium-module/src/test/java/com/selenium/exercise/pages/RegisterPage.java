package com.selenium.exercise.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class RegisterPage {
	@FindBy(how = How.NAME, using = "firstName")
	private WebElement firstName;
	
	@FindBy(how = How.NAME, using = "lastName")
	private WebElement lastName;
	
	@FindBy(how = How.NAME, using = "phone")
	private WebElement phone;
	
	@FindBy(how = How.NAME, using = "userName")
	private WebElement email;
	
	@FindBy(how = How.NAME, using = "address1")
	private WebElement address1;
	
	@FindBy(how = How.NAME, using = "city")
	private WebElement city;
	
	@FindBy(how = How.NAME, using = "state")
	private WebElement state;
	
	@FindBy(how = How.NAME, using = "postalCode")
	private WebElement zipCode;
	
	@FindBy(how = How.NAME, using = "email")
	private WebElement userName;
	
	@FindBy(how = How.NAME, using = "password")
	private WebElement password;
	
	@FindBy(how = How.NAME, using = "confirmPassword")
	private WebElement confirmPassword;
	
	@FindBy(how = How.NAME, using = "register")
	private WebElement registerButton;

	public void registerUser() {
		firstName.sendKeys("testUser9");
		lastName.sendKeys("testUser9");
		phone.sendKeys("testUser9");
		email.sendKeys("testUser9");
		address1.sendKeys("testUser9");
		city.sendKeys("Arlington");
		state.sendKeys("VA");
		zipCode.sendKeys("22210");
		userName.sendKeys("testUser9");
		password.sendKeys("testUser9");
		confirmPassword.sendKeys("testUser9");
		registerButton.submit();
	}
}
