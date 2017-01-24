package com.tfl.test.runner; /**
 * Created by TPD_Auto on 19/01/2017.
 */

import com.github.mkolisnyk.cucumber.runner.ExtendedCucumber;
import com.github.mkolisnyk.cucumber.runner.ExtendedCucumberOptions;
import cucumber.api.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(ExtendedCucumber.class)
@ExtendedCucumberOptions(
        outputFolder = "target/report",
        jsonReport = "target/cucumber.json",
        usageReport = true,
        jsonUsageReport = "target/cucumber-usage.json",
        detailedReport = true,
        detailedAggregatedReport = true,
        overviewReport = true,
        overviewChartsReport = true,
        featureOverviewChart = true,

        coverageReport = true,

        retryCount = 0,
        screenShotLocation = "target/images/",
        //screenShotSize = "300px",
        excludeCoverageTags = {"@ignore" },

        toPDF = true)
@CucumberOptions(
        format = {"pretty", "html:target/cucumber-html-report", "usage:target/cucumber-usage.json", "json:target/cucumber.json",},
        glue = { "com.tfl.test.steps" },
        features = { "classpath:basic/" },
        tags = { "@gogo" }
)
public class RunStepsReUsed {
}