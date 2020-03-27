package com.selenium.exercise.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    
    public void setSelectorByValue(WebElement element, String value) {
        new Select(element).selectByValue(value);
    }
    
    public void waitUntilUrlContains(WebDriver driver, String url) {
        new WebDriverWait(driver, 5).until(ExpectedConditions.urlContains(url));
    }
}
