package com.tfl.test.utils.prettyreport;

import com.github.mkolisnyk.cucumber.reporting.CucumberDetailedResults;
import com.github.mkolisnyk.cucumber.reporting.CucumberResultsOverview;
import com.github.mkolisnyk.cucumber.reporting.CucumberUsageReporting;
import com.github.mkolisnyk.cucumber.reporting.types.result.CucumberResult;

import java.util.Calendar;

/**
 * IF YOUR USING JENKINS THAN DON'T USE THIS
 * <p>
 * USE JENKINS PLUGINS FROM MASTERTHOUGHT
 * <p>
 * This will be automatically run once before starting the tests
 * This will monitor the specified folders and generate tests automatically
 * <p>
 * assumptions:
 * input folder is the target folder
 *
 * @author Noor
 */
public class CreatePrettyReportManual {

    public static void main(String[] args) {

        CucumberDetailedResults results = new CucumberDetailedResults();
        results.setOutputDirectory("target");
        results.setOutputName("target/r1/cucumber-results1");
        results.setSourceFile("target/cucumber-usage.json");
        try {
            results.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

//        CucumberResultsOverview results = new CucumberResultsOverview();
//        results.setOutputDirectory("target");
//        results.setOutputName("target/r1/cucumber-results1");
//        results.setSourceFile("target/cucumber.json");
//        try {
//            results.execute();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        CucumberDetailedResults results2 = new CucumberDetailedResults();
        results2.setOutputDirectory("target");
        results2.setOutputName("target/r2/cucumber-results2");
        results2.setSourceFile("target/cucumber.json");
        results2.setScreenShotLocation("/images/");
        try {
            results2.execute(true);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Cucumber

        CucumberUsageReporting report = new CucumberUsageReporting();
        report.setOutputDirectory("target/r3/cucumber-results3");
        report.setJsonUsageFile("cucumber-usage.json");
        try {
            report.executeReport();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String formatName(String fname) {
        Calendar instance = Calendar.getInstance();
        int dom = instance.get(Calendar.DAY_OF_MONTH);
        String ds = stringify(dom);
        fname = fname.replaceFirst(ds, ds + "_");
        return fname;
    }

    private String stringify(int dom) {
        String x = "" + dom;
        if (dom < 10) {
            x = "0" + dom;
        }
        return x;
    }

//
//    public void createReport(String outFolderName, boolean userBackUpFolder) {
//        String target = "target";
//        if (userBackUpFolder) {
//            target = "bu";
//        }
//
//        if (outFolderName == null) {
//            outFolderName = "PrettyReport";
//        }
//        //Where to put pretty reports
//        String res = new File("").getAbsolutePath();
//
//        String[] aaa = new String[4];
//        aaa[0] = "-f";
//        aaa[1] = res + File.separatorChar + target;
//        aaa[2] = "-o";
//        String folderName = new Date().toString().replace(":", "").substring(0, 16).replace(" ", "");
//        folderName = formatName(folderName);
//        String outFile = res + File.separatorChar + target + File.separatorChar + outFolderName + File.separatorChar + folderName;
//
//        //File to monitor
//        System.out.println("Monitoring folder : " + outFile);
//        //Create folder
//        File f = new File(outFile);
//        f.mkdirs();
//        aaa[3] = outFile;
//        try {
//            String jenkinsBasePath = "";
//            String buildNumber = "1";
//            String projectName = "PrettyReport";
//            boolean runWithJenkins = false;
//            boolean parallelTesting = false;
//            String jsonFileFolder = res + File.separatorChar + target;// + File.separatorChar + outFolderName;
//            Configuration configuration = new Configuration(new File(outFile), "MHRA_MDCM_DEVICES");
//            // optional configuration
//            configuration.setParallelTesting(parallelTesting);
//            //configuration.setJenkinsBasePath(jenkinsBasePath);
//            configuration.setRunWithJenkins(runWithJenkins);
//            configuration.setBuildNumber(buildNumber);
//
//            //Read and generate json file report
//            List<String> jsonFiles = new ArrayList<>();
//            File jf = new File(jsonFileFolder);
//            File[] files = jf.listFiles();
//            for(File fi: files){
//                String name = fi.getName();
//                if(name!=null && name.contains(".json")){
//                    System.out.println("File name : " + name);
//                    jsonFiles.add(jsonFileFolder + File.separatorChar + name);
//                }
//            }
//
//            System.out.println("Generating Report New");
//            ReportBuilder rb = new ReportBuilder(jsonFiles, configuration);
//            rb.generateReports();
//
//            //CucumberReportMonitor.main(aaa);
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }

}
