package com.learn.practice.cucumber.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author TPD_Auto
 */
public class _Page {

    public static final boolean USE_DEBUG_TIME = true;
    public static final int TIMEOUT_SMALL = 5;
    public static final int TIMEOUT_MEDIUM = 15;
    public static final int TIMEOUT_HIGH = 30;
    public static final int TIMEOUT_VERY_HIGH = 60;
    public static final int TIMEOUT_DEFAULT = TIMEOUT_HIGH;

    public WebDriver driver;
    public final Logger log = LoggerFactory.getLogger(_Page.class);


    public _Page(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebDriver getDriver() {
        return driver;
    }
}
