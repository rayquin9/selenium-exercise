package com.selenium.exercise.pages;

import java.util.List;

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

    public int findIndexByValue(Select s, String value) {
        List<WebElement> elements = s.getOptions();
        int index = -1;
        String valueUpper = value.trim().toUpperCase();
        for (int i = 0; i < elements.size(); i++) {
            WebElement element = elements.get(i);
            if (element.getAttribute("value").toUpperCase().equals(valueUpper)) {
                index = i;
                break;
            }
        }
        return index;
    }
    
    public int findIndexByText(Select s, String value) {
        List<WebElement> elements = s.getOptions();
        int index = -1;
        String valueUpper = value.trim().toUpperCase();
        for (int i = 0; i < elements.size(); i++) {
            WebElement element = elements.get(i);
            if (element.getText().toUpperCase().equals(valueUpper)) {
                index = i;
                break;
            }
        }
        return index;
    }
}
