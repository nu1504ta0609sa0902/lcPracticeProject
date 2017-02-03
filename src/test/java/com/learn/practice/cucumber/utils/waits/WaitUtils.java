package com.learn.practice.cucumber.utils.waits;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by TPD_Auto on 03/02/2017.
 */
public class WaitUtils {
    public static void waitForElementToBeClickable(WebDriver driver, WebElement element, int maxTimeToWait) {
        new WebDriverWait(driver, maxTimeToWait).until(ExpectedConditions.elementToBeClickable(element));
    }
}
