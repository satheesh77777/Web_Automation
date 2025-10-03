package Runnerclass;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "@target/failed_scenarios.txt", // Note the '@' symbol
        glue = {"StepDefinitions"},
        plugin = {
                "pretty",
                "html:target/failed-cucumber-reports.html" // Separate report for reruns
        },
        monochrome = true
)
public class FailedScenarioRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
