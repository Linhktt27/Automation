package CreateOrderBuyEvenOpen;

import Login.LoginModel;
import RunAutoTest.BaseTest;
import RunAutoTest.CommondVariable;
import RunAutoTest.MessageError;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CreateOrderBuyEvenOpen {
    BaseTest base = new BaseTest();
    WebDriver driver = base.getDriver();
    CommondVariable comd = new CommondVariable() {};
    LoginModel login = new LoginModel(this.driver);
    OrderModel order = new OrderModel(this.driver);

    MessageError msg = new MessageError() {};
    double getpurchasingpowerbefore;
    double pricetc;
    double priceceil;
    String OrderTime;

    @Before
    public void beforeTest() {
        base.beforeSuite();
        login.dangNhapVaoSMO(comd.userMain,comd.passMain);
        getpurchasingpowerbefore = order.laySucMua();
    }

    @Given("^I click on button Buy$")
    public void i_click_on_button_Buy() {
        order.clickVaoButtonMua();
    }

    @When("^ORD'(\\d+)'_I fill symbol HSX, price and even lot volume$")
    public void ord__I_fill_symbol_HSX_price_and_even_lot_volume(int arg1) {
        order.nhapMaCoPhieu(comd.symbolHSX);
        String price = order.layGiaThamChieu();
        pricetc = Double.parseDouble(price);
        order.nhapGiaDat(price);
        order.nhapKhoiLuong(comd.volume);
    }

    @When("^ORD'(\\d+)'_I click on button Order$")
    public void ord__I_click_on_button_Order(int arg1) {
        order.clickVaoButtonDat();
    }

    @When("^ORD'(\\d+)'_At popup Order confirmation, I click on button Confirm$")
    public void ord__At_popup_Order_confirmation_I_click_on_button_Confirm(int arg1) {
        order.clickVaoButtonXacNhan();
        LocalDateTime current = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        OrderTime = current.format(formatter);
        System.out.println("----Gio phut giay dat lenh la: " + OrderTime);
    }

    @Then("^ORD'(\\d+)'_I place an order to buy even lot on HSX success, new order show on Daily Orders, amount blocked is correct$")
    public void ord__I_place_an_order_to_buy_even_lot_on_HSX_success_new_order_show_on_Daily_Orders_amount_blocked_is_correct(int arg1) throws ParseException {
        //Kiem tra popup thong bao dat lenh thanh cong
        order.kiemTraHienThiThongBaoThanhCong(msg.msgcreatesucess);
        order.kiemTraHienThiLoaiLenh(msg.msgtypeorder);
        order.clickDongPopupThongBao();

        //Kiem tra lenh moi co show len So lenh trong ngay
        String placeprice = Double.toString(pricetc);
        order.kiemTraHienThiLenhMoiTrongSoLenh(OrderTime,comd.symbolHSX,comd.volume,placeprice,"Chờ khớp");

        //Kiem tra so tien block co chinh xac hay khong
        order.kiemTraSoTienBlock(getpurchasingpowerbefore,pricetc,comd.orderfee,comd.volume);

    }

    @When("^ORD'(\\d+)'_I fill symbol HNX, price and even lot volume$")
    public void ord__I_fill_symbol_HNX_price_and_even_lot_volume(int arg1) {
        order.nhapMaCoPhieu(comd.symbolHNX);
        String price = order.layGiaThamChieu();
        pricetc = Double.parseDouble(price);
        order.nhapGiaDat(price);
        order.nhapKhoiLuong(comd.volume);

    }

    @Then("^ORD'(\\d+)'_I place an order to buy even lot on HNX success, new order show on Daily Orders, amount blocked is correct$")
    public void ord__I_place_an_order_to_buy_even_lot_on_HNX_success_new_order_show_on_Daily_Orders_amount_blocked_is_correct(int arg1)  {
        //Kiem tra popup thong bao dat lenh thanh cong
        order.kiemTraHienThiThongBaoThanhCong(msg.msgcreatesucess);
        order.kiemTraHienThiLoaiLenh(msg.msgtypeorder);
        order.clickDongPopupThongBao();

        //Kiem tra lenh moi co show len So lenh trong ngay
        String placeprice = Double.toString(pricetc);
        order.kiemTraHienThiLenhMoiTrongSoLenh(OrderTime,comd.symbolHNX,comd.volume,placeprice,"Chờ khớp");

        //Kiem tra so tien block co chinh xac hay khong
        order.kiemTraSoTienBlock(getpurchasingpowerbefore,pricetc,comd.orderfee,comd.volume);
    }

    @When("^ORD'(\\d+)'_I fill symbol UPCOM, price and even lot volume$")
    public void ord__I_fill_symbol_UPCOM_price_and_even_lot_volume(int arg1)  {
        order.nhapMaCoPhieu(comd.symbolUPCOM);
        String price = order.layGiaThamChieu();
        pricetc = Double.parseDouble(price);
        order.nhapGiaDat(price);
        order.nhapKhoiLuong(comd.volume);

    }

    @Then("^ORD'(\\d+)'_I place an order to buy even lot on UPCOM success, new order show on Daily Orders, amount blocked is correct$")
    public void ord__I_place_an_order_to_buy_even_lot_on_UPCOM_success_new_order_show_on_Daily_Orders_amount_blocked_is_correct(int arg1) {
        //Kiem tra popup thong bao dat lenh thanh cong
        order.kiemTraHienThiThongBaoThanhCong(msg.msgcreatesucess);
        order.kiemTraHienThiLoaiLenh(msg.msgtypeorder);
        order.clickDongPopupThongBao();

        //Kiem tra lenh moi co show len So lenh trong ngay
        String placeprice = Double.toString(pricetc);
        order.kiemTraHienThiLenhMoiTrongSoLenh(OrderTime,comd.symbolUPCOM,comd.volume,placeprice,"Chờ khớp");

        //Kiem tra so tien block co chinh xac hay khong
        order.kiemTraSoTienBlock(getpurchasingpowerbefore,pricetc,comd.orderfee,comd.volume);
    }

    @When("^ORD'(\\d+)'_I fill symbol HSX, price MP and even lot volume$")
    public void ord__I_fill_symbol_HSX_price_MP_and_even_lot_volume(int arg1)  {
        order.nhapMaCoPhieu(comd.symbolHSX);
        String pricec = order.layGiaTran();
        priceceil = Double.parseDouble(pricec);
        order.nhapGiaDat("MP");
        order.nhapKhoiLuong(comd.volume);

    }

    @Then("^ORD'(\\d+)'_I place an order to buy even lot on HSX, price MP success, new order show on Daily Orders, amount blocked is correct$")
    public void ord__I_place_an_order_to_buy_even_lot_on_HSX_price_MP_success_new_order_show_on_Daily_Orders_amount_blocked_is_correct(int arg1) {
        //Kiem tra popup thong bao dat lenh thanh cong
        order.kiemTraHienThiThongBaoThanhCong(msg.msgcreatesucess);
        order.kiemTraHienThiLoaiLenh(msg.msgtypeorder);
        order.clickDongPopupThongBao();

        //Kiem tra lenh moi co show len So lenh trong ngay
        order.kiemTraHienThiLenhMoiTrongSoLenh(OrderTime,comd.symbolHSX,comd.volume,"MP","Chờ khớp");

        //Kiem tra so tien block co chinh xac hay khong
        order.kiemTraSoTienBlock(getpurchasingpowerbefore,priceceil,comd.orderfee,comd.volume);
    }

    @When("^ORD'(\\d+)'_I fill symbol HSX, price ATC and even lot volume$")
    public void ord__I_fill_symbol_HSX_price_ATC_and_even_lot_volume(int arg1) {
        order.nhapMaCoPhieu(comd.symbolHSX);
        String pricec = order.layGiaTran();
        priceceil = Double.parseDouble(pricec);
        order.nhapGiaDat("ATC");
        order.nhapKhoiLuong(comd.volume);

    }

    @Then("^ORD'(\\d+)'_I place an order to buy even lot on HSX, price ATC success, new order show on Daily Orders, amount blocked is correct$")
    public void ord__I_place_an_order_to_buy_even_lot_on_HSX_price_ATC_success_new_order_show_on_Daily_Orders_amount_blocked_is_correct(int arg1) {
        //Kiem tra popup thong bao dat lenh thanh cong
        order.kiemTraHienThiThongBaoThanhCong(msg.msgcreatesucess);
        order.kiemTraHienThiLoaiLenh(msg.msgtypeorder);
        order.clickDongPopupThongBao();

        //Kiem tra lenh moi co show len So lenh trong ngay
        order.kiemTraHienThiLenhMoiTrongSoLenh(OrderTime,comd.symbolHSX,comd.volume,"ATC","Chờ khớp");

        //Kiem tra so tien block co chinh xac hay khong
        order.kiemTraSoTienBlock(getpurchasingpowerbefore,priceceil,comd.orderfee,comd.volume);
    }

    @When("^ORD'(\\d+)'_I fill symbol HNX, price MTL and even lot volume$")
    public void ord__I_fill_symbol_HNX_price_MTL_and_even_lot_volume(int arg1) {
        order.nhapMaCoPhieu(comd.symbolHNX);
        String pricec = order.layGiaTran();
        priceceil = Double.parseDouble(pricec);
        order.nhapGiaDat("MTL");
        order.nhapKhoiLuong(comd.volume);
    }

    @Then("^ORD'(\\d+)'_I place an order to buy even lot on HNX, price MTL success, new order show on Daily Orders, amount blocked is correct$")
    public void ord__I_place_an_order_to_buy_even_lot_on_HNX_price_MTL_success_new_order_show_on_Daily_Orders_amount_blocked_is_correct(int arg1) {
        //Kiem tra popup thong bao dat lenh thanh cong
        order.kiemTraHienThiThongBaoThanhCong(msg.msgcreatesucess);
        order.kiemTraHienThiLoaiLenh(msg.msgtypeorder);
        order.clickDongPopupThongBao();

        //Kiem tra lenh moi co show len So lenh trong ngay
        order.kiemTraHienThiLenhMoiTrongSoLenh(OrderTime,comd.symbolHNX,comd.volume,"MTL","Chờ khớp");

        //Kiem tra so tien block co chinh xac hay khong
        order.kiemTraSoTienBlock(getpurchasingpowerbefore,priceceil,comd.orderfee,comd.volume);

    }

    @When("^ORD'(\\d+)'_I fill symbol HNX, price MOK and even lot volume$")
    public void ord__I_fill_symbol_HNX_price_MOK_and_even_lot_volume(int arg1) {
        order.nhapMaCoPhieu(comd.symbolHNX);
        String pricec = order.layGiaTran();
        priceceil = Double.parseDouble(pricec);
        order.nhapGiaDat("MOK");
        order.nhapKhoiLuong(comd.volume);

    }

    @Then("^ORD'(\\d+)'_I place an order to buy even lot on HNX, price MOK success, new order show on Daily Orders, amount blocked is correct$")
    public void ord__I_place_an_order_to_buy_even_lot_on_HNX_price_MOK_success_new_order_show_on_Daily_Orders_amount_blocked_is_correct(int arg1) {
        //Kiem tra popup thong bao dat lenh thanh cong
        order.kiemTraHienThiThongBaoThanhCong(msg.msgcreatesucess);
        order.kiemTraHienThiLoaiLenh(msg.msgtypeorder);
        order.clickDongPopupThongBao();

        //Kiem tra lenh moi co show len So lenh trong ngay
        order.kiemTraHienThiLenhMoiTrongSoLenh(OrderTime,comd.symbolHNX,comd.volume,"MOK","Chờ khớp");
        //Kiem tra so tien block co chinh xac hay khong
        order.kiemTraSoTienBlock(getpurchasingpowerbefore,priceceil,comd.orderfee,comd.volume);
    }

    @When("^ORD'(\\d+)'_I fill symbol HNX, price MAK and even lot volume$")
    public void ord__I_fill_symbol_HNX_price_MAK_and_even_lot_volume(int arg1) {
        order.nhapMaCoPhieu(comd.symbolHNX);
        String pricec = order.layGiaTran();
        priceceil = Double.parseDouble(pricec);
        order.nhapGiaDat("MAK");
        order.nhapKhoiLuong(comd.volume);

    }

    @Then("^ORD'(\\d+)'_I place an order to buy even lot on HNX, price MAK success, new order show on Daily Orders, amount blocked is correct$")
    public void ord__I_place_an_order_to_buy_even_lot_on_HNX_price_MAK_success_new_order_show_on_Daily_Orders_amount_blocked_is_correct(int arg1) {
        //Kiem tra popup thong bao dat lenh thanh cong
        order.kiemTraHienThiThongBaoThanhCong(msg.msgcreatesucess);
        order.kiemTraHienThiLoaiLenh(msg.msgtypeorder);
        order.clickDongPopupThongBao();

        //Kiem tra lenh moi co show len So lenh trong ngay
        order.kiemTraHienThiLenhMoiTrongSoLenh(OrderTime,comd.symbolHNX,comd.volume,"MAK","Chờ khớp");
        //Kiem tra so tien block co chinh xac hay khong
        order.kiemTraSoTienBlock(getpurchasingpowerbefore,priceceil,comd.orderfee,comd.volume);
    }

    @When("^ORD'(\\d+)'_I fill symbol HNX, price ATC and even lot volume$")
    public void ord__I_fill_symbol_HNX_price_ATC_and_even_lot_volume(int arg1) {
        order.nhapMaCoPhieu(comd.symbolHNX);
        String pricec = order.layGiaTran();
        priceceil = Double.parseDouble(pricec);
        order.nhapGiaDat("ATC");
        order.nhapKhoiLuong(comd.volume);
    }

    @Then("^ORD'(\\d+)'_I place an order to buy even lot on HNX, price ATC success, new order show on Daily Orders, amount blocked is correct$")
    public void ord__I_place_an_order_to_buy_even_lot_on_HNX_price_ATC_success_new_order_show_on_Daily_Orders_amount_blocked_is_correct(int arg1)  {
        //Kiem tra popup thong bao dat lenh thanh cong
        order.kiemTraHienThiThongBaoThanhCong(msg.msgcreatesucess);
        order.kiemTraHienThiLoaiLenh(msg.msgtypeorder);
        order.clickDongPopupThongBao();

        //Kiem tra lenh moi co show len So lenh trong ngay
        order.kiemTraHienThiLenhMoiTrongSoLenh(OrderTime,comd.symbolHNX,comd.volume,"ATC","Chờ khớp");
        //Kiem tra so tien block co chinh xac hay khong
        order.kiemTraSoTienBlock(getpurchasingpowerbefore,priceceil,comd.orderfee,comd.volume);
    }

    @When("^ORD'(\\d+)'_I fill symbol HNX, price PLO and even lot volume$")
    public void ord__I_fill_symbol_HNX_price_PLO_and_even_lot_volume(int arg1) {
        order.nhapMaCoPhieu(comd.symbolHNX);
        String pricec = order.layGiaTran();
        priceceil = Double.parseDouble(pricec);
        order.nhapGiaDat("PLO");
        order.nhapKhoiLuong(comd.volume);
    }

    @Then("^ORD'(\\d+)'_I place an order to buy even lot on HNX, price PLO success, new order show on Daily Orders, amount blocked is correct$")
    public void ord__I_place_an_order_to_buy_even_lot_on_HNX_price_PLO_success_new_order_show_on_Daily_Orders_amount_blocked_is_correct(int arg1) {
        //Kiem tra popup thong bao dat lenh thanh cong
        order.kiemTraHienThiThongBaoThanhCong(msg.msgcreatesucess);
        order.kiemTraHienThiLoaiLenh(msg.msgtypeorder);
        order.clickDongPopupThongBao();

        //Kiem tra lenh moi co show len So lenh trong ngay
        order.kiemTraHienThiLenhMoiTrongSoLenh(OrderTime,comd.symbolHNX,comd.volume,"PLO","Chờ khớp");
        //Kiem tra so tien block co chinh xac hay khong
        order.kiemTraSoTienBlock(getpurchasingpowerbefore,priceceil,comd.orderfee,comd.volume);
    }

    @After
    public void afterTest (Scenario scenario){
        try {
            base.tearDown(scenario,"CreateOrderBuyEvenOpen.feature");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
