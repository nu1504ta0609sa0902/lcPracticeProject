package com.tfl.test.steps.jp;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.support.PageFactory;

import com.tfl.test.pages.jp.PlanAJourneyPage;
import com.tfl.test.steps.CommonSteps;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * TASK: Write all the steps necessary to drive your test scenarios to
 * completion 
 * 
 * Note: Please make use of page objects 
 * 
 */
public class JourneyPlannerSteps extends CommonSteps{

	public static final String PLAN_A_JOURNEY = "plan-a-journey/";

	@Before
	public void initialise() {
		driver.get(url + PLAN_A_JOURNEY);
		journeyPage = PageFactory.initElements(driver, PlanAJourneyPage.class);
	}
	
	@Given("^I am on Plan A Journey page$")
	public void i_am_on_Plan_A_Journey_page() throws Throwable {
		boolean checkWeAreInJourneyPage = journeyPage.checkWeAreInJourneyPage();
		assertTrue("We are not in journey planner page", checkWeAreInJourneyPage);
	}

	@When("^I enter From \"(.*?)\" and To \"(.*?)\" and click on Plan A Journey button$")
	public void i_enter_From_and_To_and_click_on_Plan_A_Journey_button(String from, String to) throws Throwable {
		journeyPage.enterFromStation(from);
		journeyPage.enterToStation(to);
		journeyResultsPage = journeyPage.clickPlanAJourneyButtonAndPass();
	}

	@Then("^I should see the journey planner results page$")
	public void i_should_see_the_journey_planner_results_page() throws Throwable {
		assertTrue("Title not matched",journeyResultsPage.checkTitleMatches());
	}
	
	@When("^I directly click on Plan A Journey button$")
	public void i_directly_click_on_Plan_A_Journey_button() throws Throwable {
		journeyResultsPage = journeyPage.clickPlanAJourneyButtonAndPass();
	}

	@Then("^I should see both From and Tos error messages$")
	public void i_should_see_both_From_and_Tos_error_messages() throws Throwable {
		assertTrue(journeyPage.checkFromErrorMessageIsDisplayed());
		assertTrue(journeyPage.checkToErrorMessageIsDisplayed());
	}

	
}
