package com.learn.practice.cucumber.utils.config;


import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;


/**
 * DON'T ADD ANY PROJECT SPECIFIC UTILS HERE
 * 
 * THIS IS FOR FRAMEWORK CONFIGURATION ONLY
 */
public class ConfigUtils implements ConfigFileName{

	/**
	 * LOCAL CONFIGS CAN BE OVERRIDDEN
	 */
	public static String profile = System.getenv("spring.profiles.active");
	public static String currentBrowser = System.getenv("current.browser");
	public static String generateReport = System.getenv("generateReport");
	
	public static final boolean CLOSE_BROWSER_AFTER_TEST = true;

	private static Properties prop = null;
	
    /**
     * Read properties file which contains the base url, api endpoint details
     * This is to allow us to change environments quickly
     */
    public static Properties getProjectProperties(){
    	
    	if(prop == null){
	    	try {
	    		String projectRoot = getProjectRoot();
	    		String res = new File(projectRoot).getAbsolutePath() + File.separatorChar + "resources" + File.separatorChar + CONFIG_PROPERTIES_FILE_NAME;
	    		InputStream input = new FileInputStream(res);
	    		prop = new Properties();
	    		prop.load(input);
	 
	    	} catch (Exception ex) {
	    		ex.printStackTrace();
	        }
    	}
    	
    	return prop;
    }
    


    /**
     * Returns location of our project folder
     * @return
     */
	public static String getProjectRoot() {

		File f = new File(ConfigUtils.class.getProtectionDomain().getCodeSource().getLocation().getPath());
		String absolutePath = f.getAbsolutePath();
		String projectRoot = absolutePath.subSequence(0, absolutePath.indexOf("target")-1).toString();
		
		return projectRoot;
	}


	/**
	 * Get a specific property value from our config file
	 * @param property
	 * @return
	 */
	public static String getASpecificProperty(String property) {
		String specifiedProperty = null;
		try {
			Properties prop = getProjectProperties();
			specifiedProperty = prop.getProperty(property);

		} catch (Exception e) {
			specifiedProperty = null;
		}

		return specifiedProperty;
	}


	/**
	 * Returns a logger based on the class name
	 * @param class1
	 * @return
	 */
	public static Logger getLogger(Class<?> class1) {
		String projectRoot = getProjectRoot();
		PropertyConfigurator.configure(projectRoot + File.separator + "log4j.properties");
		Logger log = LoggerFactory.getLogger(class1.getSimpleName());

		return log;
	}


	/**
	 * Which browser to test on
	 * @return
	 */
	public static String getBrowerToTestOn() {
		currentBrowser  = System.getProperty("current.browser");
		if(currentBrowser != null) {
			currentBrowser = "gc";
		}
		return currentBrowser;
	}


    /**
     * Get web endpoint base url
     * @return
     */
	public static String getWebEndPoint() {
		profile = System.getProperty("spring.profiles.active");
		if(profile == null){
			profile = "mhratest";
		}

		Properties prop = getProjectProperties();
		String url = ConfigUtils.prop.getProperty("env.url." + profile);
		return url;
	}

}
