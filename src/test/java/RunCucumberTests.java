import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/sim_card_activator.feature"
        ,
        glue = "stepDefinitions",
        plugin = {"pretty", "json:target/cucumber-report.json"}
)
public class RunCucumberTests {
}
