package RunAutoTest;

import ReportAuto.ExcelUtils;
import ReportAuto.TestReportTemplate;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.time.Duration;

public class BaseTest {
    private WebDriver driver;
    CommondVariable comd = new CommondVariable() {};
    TestReportTemplate temp = new TestReportTemplate();
    String result;

    @Before
    public void beforeSuite(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.get(comd.urlSmoWeb);
        ExcelUtils.i++;
    }

    @After
    public void tearDown(Scenario scenario, String filename) throws IOException {
        temp.writeTestData(scenario,driver, filename );
        driver.quit();
    }

    public WebDriver getDriver(){
        System.setProperty("webdriver.chrome.driver", comd.urlChromeDriver);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        return driver;
    }
}
