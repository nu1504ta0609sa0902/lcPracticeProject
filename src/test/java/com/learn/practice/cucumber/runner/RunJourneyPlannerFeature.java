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
		glue = { "com.tfl.test.steps" },
		features = { "classpath:basic/" }
		,monochrome=true
)
public class RunJourneyPlannerFeature {
	//This is just a runner
	
}
