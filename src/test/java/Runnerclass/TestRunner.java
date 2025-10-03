package Runnerclass; // This class belongs to the Runnerclass package

import io.cucumber.testng.AbstractTestNGCucumberTests; // Base class for running Cucumber with TestNG
import io.cucumber.testng.CucumberOptions;             // Annotation to configure Cucumber options
import org.testng.annotations.DataProvider;            // TestNG feature for providing data to tests

// CucumberOptions annotation is used to configure how Cucumber runs
@CucumberOptions(
        // Path to your feature files (.feature)
        features = "src/test/resources/features",

        // Package(s) containing your step definitions
        glue = {"StepDefinitions"},

        // Plugins to generate reports and handle rerun
        plugin = {
                "pretty",                                // Nicely formatted console output
                "html:target/cucumber-reports.html",    // Generates HTML report in target folder
                "rerun:target/failed_scenarios.txt"     // Stores failed scenarios to rerun later
        },

        // Makes console output clean and readable
        monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {
    // Extending AbstractTestNGCucumberTests enables TestNG integration
    // and allows parallel execution of scenarios

    @Override
    @DataProvider(parallel = true) // Each scenario will run in its own thread
    public Object[][] scenarios() {
        // Fetches all scenarios from feature files as a 2D object array
        // TestNG uses this array to execute each scenario separately
        return super.scenarios();
    }
}
