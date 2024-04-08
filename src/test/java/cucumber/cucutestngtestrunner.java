package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/cucumber", glue={"Nalini.StepDefinations"},monochrome=true,plugin={"html:target/cucumber.html"})
public class cucutestngtestrunner extends AbstractTestNGCucumberTests{

}
