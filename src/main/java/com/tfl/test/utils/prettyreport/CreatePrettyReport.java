package com.tfl.test.utils.prettyreport;

import com.github.mkolisnyk.cucumber.reporting.CucumberDetailedResults;

import java.io.File;
import java.util.Date;

/**
 * This will be automatically run once before starting the tests
 * This will monitor the specified folders and generate tests automatically
 * 
 * assumptions:
 * 	input folder is the target folder

 * @author Noor
 *
 */
public class CreatePrettyReport {

	public static void main(String[] args) {
		CreatePrettyReport cpr = new CreatePrettyReport();
		cpr.monitorFolder("Pretty");
	}

	/**
	 * Monitors folder for changes and than generates pretty reports
	 */
	public void monitorFolder(String outFolderName) {
		//Where to put pretty reports
		String res = new File("").getAbsolutePath();
	
		String [] aaa = new String[4];
		aaa[0] = "-f";
		aaa[1] = res + File.separatorChar + "target" ;
		aaa[2] = "-o";
		String outFile = res + File.separatorChar + "target" + File.separatorChar + outFolderName + new Date().toString().replace(":", "").substring(0,16).replace(" ", "");
		
		//Create folder
		File f = new File(outFile);
		f.mkdirs();
		aaa[3] = outFile;
		try {
			//CucumberReportMonitor.main(aaa);
			new CucumberDetailedResults().execute(false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
