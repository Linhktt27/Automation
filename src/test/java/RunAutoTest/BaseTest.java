package RunAutoTest;

import ReportAuto.CaptureHelpers;
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
    CaptureHelpers help = new CaptureHelpers();

    @Before
    public void beforeSuite(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.get(comd.urlSmoWeb);
    }

    @After
    public void tearDown(Scenario scenario) throws IOException {
        if (scenario.isFailed()) {
            System.out.println("Result: False");
            // Take a screenshot
            help.captureScreenshot(driver,scenario.getName());
        }
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
