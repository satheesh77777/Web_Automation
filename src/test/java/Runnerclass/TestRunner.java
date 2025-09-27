package Runnerclass;

import base.BaseClass; // Import BaseClass
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static base.BaseClass.driver;

@CucumberOptions(
        features = "src/test/resources/features", // Path to feature files
        glue = {"StepDefinitions"}, // Correct package paths
        plugin = {"pretty", "html:target/cucumber-reports"}, // Plugins for reporting
        tags = "@Mobile", // Tags to filter scenarios
        monochrome = true // Makes console output readable
)
public class TestRunner extends AbstractTestNGCucumberTests { // Extend only one class


}