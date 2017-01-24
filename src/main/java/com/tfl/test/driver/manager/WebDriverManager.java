package com.tfl.test.driver.manager;

import java.io.File;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;

import com.tfl.test.utils.config.ConfigUtils;

/**
 * We should be able add any number of drivers without any issues
 * 
 * Framework was designed in a way so that we can use it for both:
 * Desktop web and mobile web app testing.
 * 
 * We should be able to plugin any webdriver based implementation with ease
 */
public class WebDriverManager {

	
	public static Logger log = null;
	static{
		log = ConfigUtils.getLogger(WebDriverManager.class);
	}

	/**
	 * Get driver based on test_map.properties variable env.driver.browser
	 * @return
	 * @throws MalformedURLException 
	 */
	public static WebDriver getDriverDynamic() throws MalformedURLException {
		String defaultBrowser = getBrowserToTestOn();

		log.info("Current Browser : " + defaultBrowser);
		
		RemoteWebDriver driver = null;
		if (defaultBrowser.equals("firefox") || defaultBrowser.equals("ff")) {

			// set driver properties
			FirefoxProfile profile = new FirefoxProfile();
			profile.setPreference("javascript.enabled", true);
			profile.setEnableNativeEvents(true);
			profile.setPreference("webdriver.load.strategy", "fast"); // As of 2.19. from 2.9 - 2.18 use 'fast'
			
			//Allow geo location : 
			profile.setPreference("geo.prompt.testing", true); 
			profile.setPreference("geo.prompt.testing.allow", true);
			

            //Use env variable
            String ffLocation = System.getenv("FIREFOX");
            if (ffLocation == null) {
			   ffLocation = ConfigUtils.getASpecificProperty("env.browser.location." + defaultBrowser);
			   driver = new FirefoxDriver(profile);
			}else{
		       FirefoxBinary binary = new FirefoxBinary(new File(ffLocation));
		       driver = new FirefoxDriver(binary, profile);
			}

		} else if (defaultBrowser.equals("ie")
				|| defaultBrowser.equals("internetexplorer")) {
			// set driver location
			setDriverProperty(defaultBrowser, "webdriver.ie.driver");
			DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
			caps.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			caps.setCapability(
					InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
					true);
			driver = new InternetExplorerDriver(caps);

		} else if (defaultBrowser.equals("chrome")
				|| defaultBrowser.equals("gc")) {
			// //set driver location
			setDriverProperty(defaultBrowser, "webdriver.chrome.driver");
			//DesiredCapabilities chrome = DesiredCapabilities.chrome();
			driver = new ChromeDriver();
		}else if(defaultBrowser.equals("headless")){
			log.info("Headless Phantom JS Test");
			log.info("Make sure Phantom JS is up and running and connected to selenium hub");
			driver = getHeadlessRemoteWebDriverInstance();
			//driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		}
		return driver;
	}

	/**
	 * Checks if ENV variale is set
	 * Else uses the properties file to determine which browser to test on
	 * @return
	 */
	private static String getBrowserToTestOn() {
		return ConfigUtils.getBrowerToTestOn();
	}


	/**
	 * Returns base url for our tests
	 * @return
	 */
	public static String getWebEndPoint() {
		return ConfigUtils.getWebEndPoint();
	}
	/**
	 * Get headless instance
	 * @return
	 */
	private static RemoteWebDriver getHeadlessRemoteWebDriverInstance() {
		RemoteWebDriver driver = null;
		try {
			if (driver == null){
 
				DesiredCapabilities caps = DesiredCapabilities.phantomjs();
				caps.setJavascriptEnabled(true);
				caps.setCapability("takesScreenshot", false);
				driver = new PhantomJSDriver(caps);    
				driver.manage().window().setSize( new Dimension( 1124, 850 ) );
				
			}
		} catch (Exception e) {
			log.info("Failed to Launch Remote Phantom Driver"
					+ e.getStackTrace());
		}
		return driver;
	}

	/**
	 * We need to set chromedriver.exe and ieserverdriver.exe locations
	 * @param defaultBrowser
	 * @param whichDriver
	 */
	private static void setDriverProperty(String defaultBrowser,
			String whichDriver) {
		String locationOfDriver = ConfigUtils
				.getASpecificProperty("env.browser.location." + defaultBrowser);
		if (locationOfDriver != null) {
			String wid = System.getProperty(whichDriver);
			log.info(wid);
			if (wid == null) {
				// This only needs to be done once
				log.info(locationOfDriver);
				File file = new File(locationOfDriver);
				System.setProperty(whichDriver, file.getAbsolutePath());
			}
		}
	}
    /**
     * Go to specified section for current target test environment
     * @param driver
     * @param pageSection if no section is specified than go to target test base url
     * @return
     */
    public static WebDriver getSpecificSection(WebDriver driver, String pageSection){
    	
        driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
        String url = ConfigUtils.getWebEndPoint();
        driver.get(url+pageSection);
        System.out.println(url);
        return driver;
    }
}
