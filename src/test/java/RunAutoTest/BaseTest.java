package RunAutoTest;

import ReportAuto.CaptureHelpers;
import ReportAuto.ExcelHelpers;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class BaseTest {
    private WebDriver driver;
    CommondVariable comd = new CommondVariable() {};
    CaptureHelpers help = new CaptureHelpers();
    ExcelHelpers excel = new ExcelHelpers();
    String result;

    @Before
    public void beforeSuite(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.get(comd.urlSmoWeb);
    }

    @After
    public void tearDown(Scenario scenario) throws Exception {
        if (scenario.isFailed()) {
            System.out.println("Result: False");
            // Take a screenshot
            help.captureScreenshot(driver,scenario.getName());
        }
        driver.quit();
        excel.setExcelFile("src/test/resources/ReportExcel.xlsx", "Sheet1");
        excel.setCellData(scenario.getId(), 1, 1);
        excel.setCellData(scenario.getName(), 1, 2);
        excel.setCellData(String.valueOf(scenario.getStatus()), 1, 3);
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
