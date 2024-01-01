package TestStep;

import RunAutoTest.BaseTest;
import RunAutoTest.CommondVariable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

public class LoginSmo {
    BaseTest base = new BaseTest();
    WebDriver driver = base.getDriver();
    LoginModel login = new LoginModel(driver);
    CommondVariable comd = new CommondVariable() {};

    @Before
    public void beforeTest() {
        base.beforeSuite();
    }

    @Test
    @When("^I fill username is account no$")
    public void i_fill_username_is_account_no() {
        login.fillUsername(comd.userMain);
    }

    @When("^I fill password of account no$")
    public void i_fill_password_of_account_no() {
        login.fillPassword(comd.passMain);
    }

    @When("^I click on button Login by account no$")
    public void i_click_on_button_Login_by_account_no(){
        login.clickOnButtonLogin();
    }

    @Then("^I login success by account no$")
    public void i_login_success_by_account_no() {
        Assert.assertTrue(driver.findElement(login.LoginSucc).isDisplayed() );
    }

    @Test
    @When("^I fill username is smartid$")
    public void i_fill_username_is_smartid()  {
        login.fillUsername(comd.smartidMain);
    }

    @When("^I fill password of smartid$")
    public void i_fill_password_of_smartid() {
        login.fillPassword(comd.passMain);
    }

    @When("^I click on button Login by smartid$")
    public void i_click_on_button_Login_by_smartid() {
        login.clickOnButtonLogin();
    }

    @Then("^I login success by smartid$")
    public void i_login_success_by_smartid() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(login.LoginSucc));
        Assert.assertTrue(driver.findElement(login.LoginSucc).isDisplayed() );
    }

    @After
    public void afterTest (Scenario scenario){
        try {
            base.tearDown(scenario);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
