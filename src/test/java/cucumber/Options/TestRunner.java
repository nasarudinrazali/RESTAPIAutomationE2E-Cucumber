package cucumber.Options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/features/endToEnd.feature", plugin="json:target/jsonReports/cucumber-html-report.json", glue="stepDefinitions")
public class TestRunner {

}
