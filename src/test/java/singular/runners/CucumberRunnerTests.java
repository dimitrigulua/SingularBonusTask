package singular.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = {"src/test/resources/singular/features/UserInfo.feature"},
        glue = {"singular.steps"}
)
public class CucumberRunnerTests extends AbstractTestNGCucumberTests {
}
