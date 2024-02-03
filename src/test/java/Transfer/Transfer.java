package Transfer;

import Login.LoginModel;
import RunAutoTest.BaseTest;
import RunAutoTest.CommondVariable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transfer {
    BaseTest base = new BaseTest();
    WebDriver driver = base.getDriver();
    TransferModel trans = new TransferModel(this.driver);
    LoginModel login = new LoginModel(driver);
    CommondVariable comd = new CommondVariable() {};
    int fee;
    double getwithdrawablecashbefore;
    double getwithdrawablecashafter;
    String OrderTime;
    String contentbank;
    String accounreceivps;

    @Before
    public void beforeTest() {
        base.beforeSuite();
        login.dangNhapVaoSMO(comd.userMain,comd.passMain);
        getwithdrawablecashbefore = trans.laySoTienCoTheRut();
    }
    @Test
    @When("^Internal_I access to Money Transfer$")
    public void internal_i_access_to_Money_Transfer() {
        trans.truyCapVaoManHinhChuyenTien();
    }

    @When("^Internal_I choose a bank internal and fill account number, amount$")
    public void internal_i_choose_a_bank_internal_and_fill_account_number_amount() {
        trans.chonNganHangChuyenTien(trans.BtnInternalBank);
        trans.nhapSoTaiKhoanNganHang(comd.acc);
        trans.nhapSoTienChuyen(comd.amt);
        fee = trans.layPhiChuyenTien();
    }

    @When("^Internal_I click Continue two times$")
    public void internal__I_click_Continue_two_times() {
        trans.chonButtonTiepTuc();
        contentbank = trans.layNoiDungChuyenTien();
        trans.chonButtonXacNhan();
    }

    @When("^Internal_I fill otp and click Continue$")
    public void internal_i_fill_otp_and_click_Continue() {
        trans.nhapMaOTP(comd.otpCode);
        trans.chonButtonXacNhanLan2();
        LocalDateTime current = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd/mm/yyyy");
        OrderTime = current.format(formatter);
    }

    @Then("^Internal_I transfer internal success$")
    public void internal_i_transfer_internal_success() {
        //Kiem tra thong bao chuyen tien thanh cong
        Assert.assertTrue(trans.kiemTraHienThiThongBaoThanhCong());

        //Kiem tra hien thi so tien chuyen
        trans.kiemTraHienThiSoTien(comd.amt);

        //Kiem tra hien thi ngan hang chuyen
        trans.kiemTraHienThiTenNganHang(comd.banknameinternal);

        //Kiem tra hien thi giao dich trong Lich su chuyen tien
        trans.kiemTraHienThiLichSuChuyenTien(OrderTime,"Chuyển khoản VPB", comd.acc, comd.amt, fee, "Người chuyển trả","Chuyển tiền thường","Thành công",contentbank);

        //Kiem tra so tien bi block
        getwithdrawablecashafter = trans.laySoTienCoTheRut();
        System.out.println("----getwithdrawablecashbefore la: " + getwithdrawablecashbefore);
        System.out.println("----getwithdrawablecashafter la: " + getwithdrawablecashafter);
        System.out.println("----fee la: " + fee);
        int amtblockcaculate = comd.amt+fee;
        int amtblockact = (int) (getwithdrawablecashbefore - getwithdrawablecashafter);
        Assert.assertEquals(amtblockcaculate, amtblockact);

    }

    @When("^Napas_I access to Money Transfer$")
    public void napas_i_access_to_Money_Transfer() {
        trans.truyCapVaoManHinhChuyenTien();
    }

    @When("^Napas_choose a bank napas and fill account number, amount$")
    public void napas_choose_a_bank_napas_and_fill_account_number_amount() {
        trans.chonNganHangChuyenTien(trans.BtnNapasBank);
        trans.nhapSoTaiKhoanNganHang(comd.acc);
        trans.nhapSoTienChuyen(comd.amt);
        fee = trans.layPhiChuyenTien();
    }

    @When("^Napas_I click Continue two times$")
    public void napas_i_Click_Continue_Two_Times() {
        trans.chonButtonTiepTuc();
        contentbank = trans.layNoiDungChuyenTien();
        trans.chonButtonXacNhan();
    }

    @When("^Napas_I fill otp and click Continue$")
    public void napas_i_fill_otp_and_click_Continue() {
        trans.nhapMaOTP(comd.otpCode);
        trans.chonButtonXacNhanLan2();
        LocalDateTime current = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd/mm/yyyy");
        OrderTime = current.format(formatter);
}

    @Then("^Napas_I transfer napas success$")
    public void napas_i_transfer_napas_success() {
        //Kiem tra thong bao chuyen tien thanh cong
        Assert.assertTrue(trans.kiemTraHienThiThongBaoThanhCong());

        //Kiem tra hien thi so tien chuyen
        trans.kiemTraHienThiSoTien(comd.amt);

        //Kiem tra hien thi ngan hang chuyen
        trans.kiemTraHienThiTenNganHang(comd.banknamenapas);

        //Kiem tra hien thi giao dich trong Lich su chuyen tien
        trans.kiemTraHienThiLichSuChuyenTien(OrderTime,"Chuyển khoản nhanh", comd.acc, comd.amt, fee, "Người chuyển trả","Chuyển tiền thường","Thành công",contentbank);

        //Kiem tra so tien bi block
        getwithdrawablecashafter = trans.laySoTienCoTheRut();
        System.out.println("----getwithdrawablecashbefore la: " + getwithdrawablecashbefore);
        System.out.println("----getwithdrawablecashafter la: " + getwithdrawablecashafter);
        System.out.println("----fee la: " + fee);
        int amtblockcaculate = comd.amt+fee;
        int amtblockact = (int) (getwithdrawablecashbefore - getwithdrawablecashafter);
        Assert.assertEquals(amtblockcaculate, amtblockact);
    }


    @When("^Citad_I access to Money Transfer$")
    public void citad_i_access_to_Money_Transfer() {
        trans.truyCapVaoManHinhChuyenTien();
    }

    @When("^Citad_I choose a bank citad and fill account number, amount$")
    public void citad_i_choose_a_bank_citad_and_fill_account_number_amount() {
        trans.chonNganHangChuyenTien(trans.BtnNapasBank);
        trans.chonVaoOptionChuyenTienThuong();
        trans.nhapSoTaiKhoanNganHang(comd.acc);
        trans.nhapTenTaiKhoanNhan("Mr Nguyen Van Dong");
        trans.chonThanhPho();
        trans.chonChiNhanh();
        trans.nhapSoTienChuyen(comd.amt);
        fee = trans.layPhiChuyenTien();
    }
    @When("^Citad_I click Continue two times$")
    public void citad_iClickContinueTwoTimes() {
        trans.chonButtonTiepTuc();
        contentbank = trans.layNoiDungChuyenTien();
        trans.chonButtonXacNhan();
    }

    @When("^Citad_I fill otp and click Continue$")
    public void citad_i_fill_otp_and_click_Continue() {
        trans.nhapMaOTP(comd.otpCode);
        trans.chonButtonXacNhanLan2();
        LocalDateTime current = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd/mm/yyyy");
        OrderTime = current.format(formatter);
    }

    @Then("^Citad_I transfer citad success$")
    public void citad_i_transfer_citad_success() {
        //Kiem tra thong bao chuyen tien thanh cong
        Assert.assertTrue(trans.kiemTraHienThiThongBaoThanhCong());

        //Kiem tra hien thi so tien chuyen
        trans.kiemTraHienThiSoTien(comd.amt);

        //Kiem tra hien thi ngan hang chuyen
        trans.kiemTraHienThiTenNganHang(comd.banknamenapas);

        //Kiem tra hien thi giao dich trong Lich su chuyen tien
        trans.kiemTraHienThiLichSuChuyenTien(OrderTime,"Chuyển liên ngân hàng", comd.acc, comd.amt, fee, "Người chuyển trả","Chuyển tiền thường","Thành công",contentbank);

        //Kiem tra so tien bi block
        getwithdrawablecashafter = trans.laySoTienCoTheRut();
        System.out.println("----getwithdrawablecashbefore la: " + getwithdrawablecashbefore);
        System.out.println("----getwithdrawablecashafter la: " + getwithdrawablecashafter);
        System.out.println("----fee la: " + fee);
        int amtblockcaculate = comd.amt+fee;
        int amtblockact = (int) (getwithdrawablecashbefore - getwithdrawablecashafter);
        Assert.assertEquals(amtblockcaculate, amtblockact);
    }

    @When("^CKNB_I access to Money Transfer$")
    public void cknb_iAccessToMoneyTransfer() {
        trans.truyCapVaoManHinhChuyenTien();
    }

    @When("^CKNB_I choose tab To internal VPS's account$")
    public void cknb_i_choose_tab_To_internal_VPS_s_account() {
        trans.chonVaoTabChuyenTienTrongVPS();
    }

    @When("^CKNB_I choose option To other’s VPS trading accounts$")
    public void cknb_i_choose_option_To_other_s_VPS_trading_accounts() {
        trans.chonLoaiChuyenKhoan(trans.RdoCknb);
    }

    @When("^CKNB_I fill Account number and Transfer amount$")
    public void cknb_i_fill_Account_number_and_Transfer_amount() {
        trans.nhapSoTaiKhoanVPS(comd.accVPS);
        trans.nhapSoTienChuyenTrongVps(comd.amt);
    }

    @When("^CKNB_I click Continue two times$")
    public void cknbiClickContinueTwoTimes() {
        trans.chonButtonTiepTucChuyenTienTrongVps();
        contentbank = trans.layNoiDungChuyenTienTrongVps();
        trans.chonButtonXacNhanChuyenTienTrongVps();
    }

    @When("^CKNB_I fill otp and click Continue$")
    public void cknb_i_fill_otp_and_click_Continue() {
        trans.nhapMaOtpChuyenTienTrongVps(comd.otpCode);
        trans.chonButtonXacNhanChuyenTienTrongVpsLan2();

        LocalDateTime current = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd/mm/yyyy");
        OrderTime = current.format(formatter);

    }

    @Then("^CKNB_I transfer to other’s VPS trading accounts success$")
    public void cknb_i_transfer_to_other_s_VPS_trading_accounts_success() {
        //Kiem tra thong bao chuyen tien thanh cong
        Assert.assertTrue(trans.kiemTraHienThiThongBaoChuyenTienTrongVpsThanhCong());

        //Kiem tra hien thi so tien chuyen
        trans.kiemTraHienThiSoTienChuyenTrongVps(comd.amt);

        //Kiem tra hien thi giao dich trong Lich su chuyen tien
        trans.kiemTraHienThiLichSuChuyenTien(OrderTime,"Chuyển khoản nội bộ", comd.acc, comd.amt, 0, "Người chuyển trả","Chuyển tiền thường","Thành công",contentbank);


        //Kiem tra so tien bi block
        getwithdrawablecashafter = trans.laySoTienCoTheRut();
        System.out.println("----getwithdrawablecashbefore la: " + getwithdrawablecashbefore);
        System.out.println("----getwithdrawablecashafter la: " + getwithdrawablecashafter);
        int amtblockact = (int) (getwithdrawablecashbefore - getwithdrawablecashafter);
        Assert.assertEquals(comd.amt, amtblockact);
    }

    @When("^CKSTK_I access to Money Transfer$")
    public void ckstk_i_access_to_Money_Transfer() {
        trans.truyCapVaoManHinhChuyenTien();
    }

    @When("^CKSTK_I choose tab To internal VPS's account$")
    public void ckstk_i_choose_tab_To_internal_VPS_s_account() {
        trans.chonVaoTabChuyenTienTrongVPS();
    }

    @When("^CKSTK_I choose option Between your sub-accounts$")
    public void ckstk_i_choose_option_Between_your_sub_accounts() {
        trans.chonLoaiChuyenKhoan(trans.RdoCkstk);
    }

    @When("^CKSTK_I choose Account number and Transfer amount$")
    public void ckstk_i_choose_Account_number_and_Transfer_amount() {
        trans.chonTieuKhoanNhanTien();
        trans.nhapSoTienChuyenTrongVps(comd.amt);
    }

    @When("^CKSTK_I click Continue two times$")
    public void ckstk_iClickContinueTwoTimes() {
        trans.chonButtonTiepTucChuyenTienTrongVps();
        contentbank = trans.layNoiDungChuyenTienTrongVps();
        accounreceivps = trans.layTenNguoiNhan();
        trans.chonButtonXacNhanChuyenTienTrongVps();

        LocalDateTime current = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd/mm/yyyy");
        OrderTime = current.format(formatter);
        System.out.println("----Ngay gio chuyen tien la: " + OrderTime);
    }

    @Then("^CKSTK_I transfer between your sub-accounts success$")
    public void ckstk_i_transfer_between_your_sub_accounts_success()  {
        //Kiem tra thong bao chuyen tien thanh cong
        Assert.assertTrue(trans.kiemTraHienThiThongBaoChuyenTienTrongVpsThanhCong());

        //Kiem tra hien thi so tien chuyen
        trans.kiemTraHienThiSoTienChuyenTrongVps(comd.amt);

        //Kiem tra hien thi ten nguoi nhan
        trans.kiemTraHienThiTenNguoiNhan("Tài khoản 270494");
        //Tren Test ten la TK TEST

        //Kiem tra hien thi giao dich trong Lich su chuyen tien
        trans.kiemTraHienThiLichSuChuyenTien(OrderTime,"Chuyển khoản nội bộ", comd.acc, comd.amt, 0, "Người chuyển trả","Chuyển tiền thường","Thành công",contentbank);


        //Kiem tra so tien bi block
        getwithdrawablecashafter = trans.laySoTienCoTheRut();
        System.out.println("----getwithdrawablecashbefore la: " + getwithdrawablecashbefore);
        System.out.println("----getwithdrawablecashafter la: " + getwithdrawablecashafter);
        int amtblockact = (int) (getwithdrawablecashbefore - getwithdrawablecashafter);
        Assert.assertEquals(comd.amt, amtblockact);

    }
    @After
    public void afterTest (Scenario scenario){
        try {
            base.tearDown(scenario, "Transfer.feature");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
