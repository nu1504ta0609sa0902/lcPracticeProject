package com.learn.practice.cucumber.suite;

import com.learn.practice.cucumber.runner.RunAllPracticeDeviceTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


/**
 * Group any number of stories you would like to execute
 *
 */
@RunWith(Suite.class)
@SuiteClasses({RunAllPracticeDeviceTests.class})	//Run single feature
public class RunAllTestSuite {

}