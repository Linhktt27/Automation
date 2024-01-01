package RunAutoTest;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Test;

@CucumberOptions(
        features ="D:\\demoautotest\\Automation\\src\\test\\java\\Features\\login.feature",
        glue = {"TestStep"},
        plugin = {"pretty", "html:target/cucumber-html-report.html"}
)

@Test
        public class RunAuto extends AbstractTestNGCucumberTests {

}
