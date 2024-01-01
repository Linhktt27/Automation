package RunAutoTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class BasePage {
    int TIMEOUT = 10;
    int SLEEP = 5000;
    WebDriverWait wait;
    Actions act;

    public void choDenKhiPhanTuCoTheClick(WebDriver driver, By elm){
        wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
        wait.until(ExpectedConditions.elementToBeClickable(elm));
    }
    public void choDenKhiPhanTuXuatHien(WebDriver driver, By elm){
        wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(elm));
    }

    public void moveChuotDenPhanTuVaClick(WebDriver driver , By elm){
        choDenKhiPhanTuCoTheClick(driver,elm);
        act = new Actions(driver);
        act.moveToElement(driver.findElement(elm)).click().perform();
    }

    public void choPhanTuXuatHienVaClick(WebDriver driver , By elm) {
        choDenKhiPhanTuCoTheClick(driver,elm);
        driver.findElement(elm).click();
    }

    public String layTextCuaPhanTu (WebDriver driver , By elm){
        choDenKhiPhanTuXuatHien(driver,elm);
        return driver.findElement(elm).getText();
    }

    public void nhapTextVaoPhanTu(WebDriver driver , By elm, String text) {
        choDenKhiPhanTuCoTheClick(driver,elm);
        driver.findElement(elm).sendKeys(text);
    }

    public void choLoadPage(){
        try {
            Thread.sleep(SLEEP);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean kiemTraCoHienThiPhanTu(WebDriver driver, By elm ){
        choDenKhiPhanTuXuatHien(driver,elm);
        return driver.findElement(elm).isDisplayed();
    }

    public void kiemTraPhanTuHienThiCoTrungKetQuaMongMuon(WebDriver driver, String expected, By elm){
        choDenKhiPhanTuXuatHien(driver,elm);
        String getamount = driver.findElement(elm).getText();
        Assert.assertEquals(expected, getamount);
    }
}
