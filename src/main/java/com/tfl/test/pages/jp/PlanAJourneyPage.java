package com.tfl.test.pages.jp;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * THESE ARE EXAMPLES WHICH COMES WITH THE FRAMEWORK
 * CREATE YOUR OWN PAGE OBJECTS WHERE NECESSARY
 * 
 * You only need to use these methods
 */
public class PlanAJourneyPage{

	@FindBy(how = How.CSS, using = "input.primary-button.plan-journey-button")
	private WebElement planAJourneyButton;
	
	@FindBy(how = How.ID, using="InputFrom")
	private WebElement fromStation;
	
	@FindBy(how = How.ID, using="InputTo")
	private WebElement toStation;

	private WebDriver driver;

	public PlanAJourneyPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public PlanAJourneyPage clickPlanAJourneyButtonAndFail(){
		planAJourneyButton.click();
		return PageFactory.initElements(driver, PlanAJourneyPage.class);//new PlanAJourneyPage(driver);
	}
	
	public JourneyPlannerResultsPage clickPlanAJourneyButtonAndPass(){
		planAJourneyButton.click();
		return PageFactory.initElements(driver, JourneyPlannerResultsPage.class); //new JourneyPlannerResultsPage(driver);
	}
	
	public void clickPlanAJourney(){
		planAJourneyButton.click();
	}
	
	public void enterFromStation(String name){
		new WebDriverWait(driver, 2).until(ExpectedConditions.elementToBeClickable(fromStation));
		fromStation.sendKeys(name);
	}
	
	public void enterToStation(String name){
		toStation.sendKeys(name);
	}

	public boolean checkFromErrorMessageIsDisplayed() {
		List<WebElement> errors = driver.findElements(By.cssSelector(".field-validation-error[data-valmsg-replace='true']"));
		WebElement fromErrorMessage = errors.get(0);
		return fromErrorMessage.isDisplayed();
	}

	public boolean checkToErrorMessageIsDisplayed() {
		List<WebElement> errors = driver.findElements(By.cssSelector(".field-validation-error[data-valmsg-replace='true']"));
		WebElement fromErrorMessage = errors.get(1);
		return fromErrorMessage.isDisplayed();
	}

	public boolean checkWeAreInJourneyPage() {
		String title = driver.getTitle();
		System.out.println(title);
		return title.equals("Plan a journey - Transport for London");
	}
}
