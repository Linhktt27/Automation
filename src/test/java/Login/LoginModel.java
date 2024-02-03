package Login;

import RunAutoTest.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginModel extends BasePage {
    int secondwait = 10;
    int sleep = 5000;
    WebDriver driver;
    public By TxtUsername = By.xpath("//*[@id=\"Account\"]");
    public By TxtPassword = By.xpath("//*[@id=\"Password\"]");
    public By BtnLogin = By.xpath("//*[@id=\"btnLogin\"]");
    public By LoginSucc = By.xpath("//*[@id=\"lblAccountDefault\"]");

    public By BtnChange = By.xpath("//*[@id=\"divMain\"]/b/b/div[1]/table/tbody/tr/td[2]/div/div[2]/table/tbody/tr/td[3]/div/a");
    public By OptSubaccount = By.xpath("/html/body/div[1]/b/b/div[8]/div/div/div[2]/div[2]/div[1]/table/tbody/tr[1]/td[4]/input");
    //public By BtnConfirm = By.xpath("//*[@id=\"divListAccount\"]/div[5]/div/a[1]");
    public By BtnConfirm = By.xpath("//*[@id=\"divListAccount\"]/div[4]/div/a[1]");

    public By BtnPortfolioManagement = By.xpath("//*[@id=\"liAsset\"]/a");
    public By BtnAssetsManagement = By.xpath("//*[@id=\"two\"]/li[1]/a");
    public By LblPurchasingpower = By.xpath("//*[@id=\"divCashBalance\"]/table/tbody/tr/td[2]");

    public By LblWithdrawableCash = By.xpath("//*[@id=\"divCashBalance\"]/table/tbody/tr/td[4]");

    public By BtnOnlyOneTime = By.xpath("//*[@id=\"divSaveDevice\"]/div/div/div[3]/button[2]");

    public By TxtError = By.xpath("//*[@id=\"contentModal\"]");
    public LoginModel (WebDriver driver) {
        this.driver = driver;
    }
    public void fillUsername (String username) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(secondwait));
        wait.until(ExpectedConditions.visibilityOfElementLocated(TxtUsername));
        System.out.println("-------Ten: "+username);
        System.out.println("-------Ten: "+TxtUsername);
        driver.findElement(TxtUsername).sendKeys(username);
    }

    public void fillPassword (String password) {
        driver.findElement(TxtPassword).sendKeys(password);
    }

    public void clickOnButtonLogin (){
        driver.findElement(BtnLogin).click();
    }

    public void dangNhapVaoSMO(String username,String password) {
        fillUsername(username);
        fillPassword(password);
        clickOnButtonLogin();
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(secondwait));
        //wait.until(ExpectedConditions.visibilityOfElementLocated(BtnOnlyOneTime)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(BtnChange)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(OptSubaccount)).click();
        driver.findElement(BtnConfirm).click();
    }
}
