package com.selenium.exercise.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage extends BasePage {
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

    public RegisterPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public WebElement getFirstName() {
        return firstName;
    }

    public WebElement getLastName() {
        return lastName;
    }

    public WebElement getPhone() {
        return phone;
    }

    public WebElement getEmail() {
        return email;
    }

    public WebElement getAddress1() {
        return address1;
    }

    public WebElement getCity() {
        return city;
    }

    public WebElement getState() {
        return state;
    }

    public WebElement getZipCode() {
        return zipCode;
    }

    public WebElement getUserName() {
        return userName;
    }

    public WebElement getPassword() {
        return password;
    }

    public WebElement getConfirmPassword() {
        return confirmPassword;
    }

    public WebElement getRegisterButton() {
        return registerButton;
    }
}
