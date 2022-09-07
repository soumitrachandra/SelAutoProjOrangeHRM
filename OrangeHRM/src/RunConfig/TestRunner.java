package RunConfig;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.CucumberOptions;

//@RunWith(Cucumber.class)
@RunWith(Cucumber.class)
@CucumberOptions(
	features = "Login.feature",
	glue={"StepDefinitions.LoginSteps"},
	tags={"@tag","@tag1"}
	)

public class TestRunner {

}
