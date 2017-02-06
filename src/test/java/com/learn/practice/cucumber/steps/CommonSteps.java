package com.learn.practice.cucumber.steps;


import com.learn.practice.cucumber.manager.WebDriverManager;
import com.learn.practice.cucumber.pageobjects.LoginPage;
import com.learn.practice.cucumber.pageobjects.MainNavigationBar;
import com.learn.practice.cucumber.utils.config.ConfigUtils;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;

/**
 * README
 * 
 * README
 * 
 * Holds:
 * 	Driver instance
 * 	All the pages
 * 	Handles creation of pretty report
 * @author Noor
 *
 */
public class CommonSteps {

	public static boolean initialiseOnce = false;
	public static WebDriver driver;
	public static String url;

	public static final String LOCATION_SCREENSHOTS = "./screenshots/";
	
	//Pages
	public static MainNavigationBar mainNavigationBar;
	public static LoginPage loginPage;

	public static Logger log = null;
	
	public CommonSteps() {
		if (driver == null) {
			log = ConfigUtils.getLogger(CommonSteps.class);
			url = ConfigUtils.getWebEndPoint();

			log.info("URL : " + url);
			try {
				driver = WebDriverManager.getDriverDynamic();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			driver.manage().window().maximize();

			//set hook to shut down driver
			addShutdownHook();
		}
	}

	/**
	 * Add shutdown hook
	 */
	private void addShutdownHook() {
		if(!initialiseOnce){
			log.info("Initialise shutdown hook");
			initialiseOnce = true;
			//This should only run when JVM shutsdown
	        Runtime.getRuntime().addShutdownHook(new Thread() {
	            public void run() {
	            	if(driver != null && ConfigUtils.CLOSE_BROWSER_AFTER_TEST){
		            	log.info("Shutting down browser just before JVM shutsdown");
		                driver.quit();
		                log.info("Shutdown complete");
	            	}
	            }
	        });
		}
	}

	
	/**
	 * Go to specified url
	 * @param pageUrl
	 */
	public void goToPage(String pageUrl) {
		driver.get(pageUrl);
	}


}