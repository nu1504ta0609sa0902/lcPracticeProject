package com.tfl.test.pages.jp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


/**
 * THESE ARE EXAMPLES WHICH COMES WITH THE FRAMEWORK
 * CREATE YOUR OWN PAGE OBJECTS WHERE NECESSARY
 * 
 * You only need to use these methods
 */
public class JourneyPlannerResultsPage {

	private WebDriver driver;

	public JourneyPlannerResultsPage(WebDriver driver) {
		this.driver = driver;
	}
	
	/**
	 * Simple check to make sure we are in correct page
	 * @param expectedTitle
	 * @return
	 */
	public boolean checkTitleMatches(String expectedTitle){
		String title = driver.getTitle();
		return title.equals(expectedTitle);
	}
	
	public String getTitle(){
		return driver.getTitle();
	}

	public boolean checkTitleMatches() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String title = driver.findElement(By.xpath(".//*[@id='full-width-content']/div/div[2]/div/h1/span")).getText();
		System.out.println(title);
		return title.equalsIgnoreCase("Journey results");
	}
}
