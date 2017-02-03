package com.learn.practice.cucumber.pageobjects;

import com.learn.practice.cucumber.utils.waits.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author TPD_Auto
 */

public class LoginPage extends _Page {

    @FindBy(id = "un")
    WebElement username;
    @FindBy(id = "pw")
    WebElement password;
    @FindBy(css = "input#remember")
    WebElement remember;
    @FindBy(css = ".gwt-Anchor.pull-down-toggle")
    WebElement settings;
    @FindBy(css = ".settings-pull-down .gwt-Anchor.pull-down-toggle")
    WebElement loggedInUsername;


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage loadPage(String url) {
        driver.get(url);
        return new LoginPage(driver);
    }

    public MainNavigationBar login(String un, String pword) {
        dontRemember();
        //login
        this.username.sendKeys(un);
        password.sendKeys(pword);
        this.username.submit();

        return new MainNavigationBar(driver);
    }

    public MainNavigationBar reloginUsing(String username, String uname) {
        logoutIfLoggedIn();
        MainNavigationBar login = login(username, uname);
        return login;
    }

    private void dontRemember() {
        WaitUtils.waitForElementToBeClickable(driver, remember, TIMEOUT_DEFAULT);
        if (remember.getAttribute("checked") != null) {
            remember.click();
        }
    }

    private LoginPage logoutIfLoggedIn() {
        try {
            WaitUtils.waitForElementToBeClickable(driver, settings, TIMEOUT_MEDIUM);
            if (settings.isDisplayed()) {
                settings.click();
                driver.findElement(By.linkText("Sign Out")).click();
                WaitUtils.waitForElementToBeClickable(driver, remember, TIMEOUT_MEDIUM);
            }
        } catch (Exception e) {
            //WaitUtils.waitForElementToBeClickable(driver,username, 20, false);
        }
        return new LoginPage(driver);
    }

    public String getLoggedInUserName() {
        WaitUtils.waitForElementToBeClickable(driver, loggedInUsername, TIMEOUT_MEDIUM);
        return loggedInUsername.getText();
    }

}
