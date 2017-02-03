package com.learn.practice.cucumber.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * If we don't specify features than it will run all the features in the same
 * package
 * 
 * @author nuddin
 * "pretty", "html:target/cip-sprint0-html-report","json:target/report_cip_sprint0.json"
 */
@RunWith(Cucumber.class)
@CucumberOptions(format = {"pretty", "html:target/cucumber-html-report", "json:target/report_example.json"}, 
		glue = { "com.learn.practice.cucumber" },
		features = { "classpath:devices/" }
		,monochrome=true
)
public class RunAllPracticeDeviceTests {
	//This is just a runner
	
}
