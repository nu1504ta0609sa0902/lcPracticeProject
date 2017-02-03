package com.learn.practice.cucumber.steps.devices.practice;

import com.learn.practice.cucumber.pageobjects.LoginPage;
import com.learn.practice.cucumber.steps.CommonSteps;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.hamcrest.Matchers;
import org.junit.Assert;

/**
 * Created by TPD_Auto on 17/10/2016.
 */
public class LoginPageSteps extends CommonSteps {


    @When("^I am logged into appian with username \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void i_am_logged_into_appian_as_user(String username, String password) throws Throwable {
        if(loginPage == null)
            loginPage = new LoginPage(driver);

        loginPage = loginPage.loadPage(url);
        try {
            mainNavigationBar = loginPage.login(username, password);
        }catch(Exception e) {
            try {
                mainNavigationBar = loginPage.reloginUsing(username, password);
            }catch (Exception e2){}
        }
    }

    @Then("^I should see name of logged in user as \"([^\"]*)\"$")
    public void i_should_see_name_of_logged_in_user_as(String expectedName) throws Throwable {
        String actualName = loginPage.getLoggedInUserName();
        Assert.assertThat(actualName, Matchers.equalTo(expectedName));
    }

    @Then("^I should see name of the logged in account holder as \"([^\"]*)\"$")
    public void i_should_see_name_of_the_logged_in_account_holder_as(String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

}
