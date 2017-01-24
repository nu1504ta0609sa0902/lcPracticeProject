package com.tfl.test.steps.shared;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;

import com.tfl.test.steps.CommonSteps;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

/**
 * DONT EXTEND ME
 * 
 * READ ME
 * 
 * @author nuddin
 * 
 */
public class SharedSteps extends CommonSteps{

	
	/**
	 * Take screen shot
	 * @param scenario
	 */
	@After  
    public void embedScreenshot(Scenario scenario) {  
        if (scenario.isFailed()) {  
        	log.info("Scenario Failed");
    		log.info("==================================\n");
            try {  
            	byte[] bytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            	scenario.embed(bytes, "image/png");
            } catch (WebDriverException wde) {  
                System.err.println(wde.getMessage());  
            } catch (ClassCastException cce) {  
                cce.printStackTrace();  
            } catch (Exception e){
            	e.printStackTrace();
            }
        }  else{
        	log.info("Scenario Passed");
    		//log.info("\n==================================");
        	
        }
    }
	
	@Before
	public void logScenarioNames(Scenario scenario) { 
		log.info("\n==================================");
		log.info("NEW TEST");
		log.info(scenario.getName());
	}


}
