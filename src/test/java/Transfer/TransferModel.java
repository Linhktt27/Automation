package Transfer;

import Login.LoginModel;
import RunAutoTest.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TransferModel extends BasePage {
    WebDriver driver;
    public By BtnUtilities = By.xpath("//*[@id=\"liUtility\"]/a");
    public By BtnMoneyTransfer = By.xpath("//*[@id=\"three\"]/li[1]/a");
    public By TxtInternalBank = By.xpath("//*[@id=\"txtBankName\"]");

    public By BtnInternalBank = By.cssSelector("img[src=\"https://datafeedtest.vps.com.vn/bankicon/VPB.png\"]");
    //public By BtnInternalBank = By.cssSelector("img[src=\"https://soapidatafeed.vps.com.vn/bankicon/VPB.png\"]");

    public By BtnNapasBank = By.cssSelector("img[src=\"https://datafeedtest.vps.com.vn/bankicon/DAB.png\"]");
    //public By BtnNapasBank = By.cssSelector("img[src=\"https://soapidatafeed.vps.com.vn/bankicon/DAB.png\"]");
    public By RdbCitad = By.xpath("//*[@id=\"rdbTransNormal\"]");
    public By TxtReceiverName = By.xpath("//*[@id=\"txtNameRec\"]");

    public By TxtCity = By.xpath("//*[@id=\"txtProvinName\"]");

    public By BtnCity = By.xpath("/html/body/ul[20]/li[1]/a");
    public By TxtBranch = By.xpath("//*[@id=\"txBankBranch\"]");
    public By BtnBranch = By.xpath("/html/body/ul[21]/li/a");
    public By TxtAccountNo = By.xpath("//*[@id=\"txtAccNo\"]");
    public By TxtAmount = By.xpath("//*[@id=\"txtAmountBank\"]");

    public By LblFee = By.xpath("//*[@id=\"tblBank\"]/tbody[1]/tr[15]/td[2]/span[1]");

    public By BtnContinue = By.xpath("//*[@id=\"btnExecute\"]");
    public By BtnConfirm = By.xpath("//*[@id=\"btnShowpin\"]");
    public By TxtOtp = By.xpath("//*[@id=\"txtAuthenOTPBank\"]");
    public By BtnConfirm2 = By.xpath("//*[@id=\"btnOKAcceptBank\"]");
    public By TransSuccess = By.cssSelector("div[id=\"divCashTrans\"]>div[class=\"divconfirmBank col-md-1\"]>div[class=\"padding-bank\"]>img[src=\"../../../Images/TransSucess.png\"]");
    public By TransSuccessVps = By.cssSelector("div[id=\"divCashTransInternal\"]>div[class=\"divconfirm col-md-1\"]>div[class=\"padding-bank\"]>img[src=\"../../../Images/TransSucess.png\"]");

    public By TransAmount = By.xpath("//*[@id=\"RECEIVER_AMOUNT_BANK\"]");

    public By TransBank = By.xpath("//*[@id=\"RECEIVER_BANK\"]");

    public By TabTranferVPS = By.xpath("/html/body/div[1]/b/b/div[6]/div[5]/ul/li[2]/a");
    public By RdoCknb = By.xpath("//*[@id=\"rdbDiffAcc\"]");
    public By RdoCkstk = By. xpath("//*[@id=\"rdbSameAcc\"]");
    public By TxtSubAccount = By.xpath("/html/body/div[1]/b/b/div[6]/div[5]/div/div[4]/table/tbody[1]/tr[11]/td/input[2]");
    public By BtnSubAccount = By.xpath("/html/body/ul[18]/li[1]/a");
    public By TxtAccountVps = By.xpath("//*[@id=\"txtAccRecvAcc\"]");
    public By TxtAmountVps = By.xpath("//*[@id=\"txtDepositMoney\"]");

    public By BtnContinuesVps = By.xpath("//*[@id=\"btnExecuteInternal\"]");
    public By LblContentBank = By.xpath("//*[@id=\"lblContentBank\"]");
    public By LblContentVPS = By.xpath("//*[@id=\"lblTransferNote\"]");

    public By LblAccountRecei = By.xpath("//*[@id=\"lblAccRec\"]");
    public By BtnConfirmVps = By.xpath("//*[@id=\"btnContinueInternal\"]");

    public By TxtOtpVps = By.xpath("//*[@id=\"txtAuthenOTPIntenal\"]");

    public By BtnConfirm2Vps = By.xpath("//*[@id=\"btnOKAcceptInternal\"]");

    public By AmountVps = By.xpath("//*[@id=\"RECEIVER_AMOUNT\"]");
    public By ReceiverNameVps = By.xpath("//*[@id=\"RECEIVER\"]");
    public By BtnHistory = By.xpath("/html/body/div[1]/b/b/div[6]/div[5]/ul/li[3]/a");
    public By LblTransferTime = By.xpath("//*[@id=\"divCashTransHis\"]/table/tbody/tr[1]/td[2]");
    public By LblTransferType = By.xpath("//*[@id=\"divCashTransHis\"]/table/tbody/tr[1]/td[3]");
    public By LblBankAccount = By.xpath("//*[@id=\"divCashTransHis\"]/table/tbody/tr[1]/td[5]");

    public By LblTransferAmount = By.xpath("//*[@id=\"divCashTransHis\"]/table/tbody/tr[1]/td[7]");
    public By LblTransferFee = By.xpath("//*[@id=\"divCashTransHis\"]/table/tbody/tr[1]/td[8]");
    public By LblFeeType = By.xpath("//*[@id=\"divCashTransHis\"]/table/tbody/tr[1]/td[9]");
    public By LblTransferMethod = By.xpath("//*[@id=\"divCashTransHis\"]/table/tbody/tr[1]/td[10]");
    public By LblTransferStatus = By.xpath("//*[@id=\"divCashTransHis\"]/table/tbody/tr[1]/td[11]");
    public By LblTransferContent = By.xpath("//*[@id=\"divCashTransHis\"]/table/tbody/tr[1]/td[12]");
    public TransferModel (WebDriver driver) {
        this.driver = driver;
    }
    LoginModel login = new LoginModel(driver);
    public double laySoTienCoTheRut (){
        choLoadPage();
        moveChuotDenPhanTuVaClick(driver,login.BtnPortfolioManagement);
        moveChuotDenPhanTuVaClick(driver,login.BtnAssetsManagement);
        String withdrawablecashbefore = layTextCuaPhanTu(driver,login.LblWithdrawableCash);
        double getwithdrawablecashbefore = Double.parseDouble(withdrawablecashbefore.replaceAll(",", ""));
        return  getwithdrawablecashbefore;
    }

    public void truyCapVaoManHinhChuyenTien(){
        moveChuotDenPhanTuVaClick(driver,BtnUtilities);
        moveChuotDenPhanTuVaClick(driver,BtnMoneyTransfer);
    }

    public void chonNganHangChuyenTien (By btnbank){
        choPhanTuXuatHienVaClick(driver,TxtInternalBank);
        choPhanTuXuatHienVaClick(driver,btnbank);
    }

    public void chonVaoOptionChuyenTienThuong(){
        choPhanTuXuatHienVaClick(driver,RdbCitad);
    }
    public void nhapSoTaiKhoanNganHang (String acc){
        nhapTextVaoPhanTu(driver,TxtAccountNo,acc);
    }
    public void nhapTenTaiKhoanNhan(String name){
        nhapTextVaoPhanTu(driver,TxtReceiverName,name);
    }
    public void chonThanhPho(){
        choPhanTuXuatHienVaClick(driver,TxtCity);
        choPhanTuXuatHienVaClick(driver,BtnCity);
    }

    public void chonChiNhanh(){
        choPhanTuXuatHienVaClick(driver,TxtBranch);
        choPhanTuXuatHienVaClick(driver,BtnBranch);
    }
    public void nhapSoTienChuyen (int amt){
        nhapTextVaoPhanTu(driver,TxtAmount,String.valueOf(amt));
    }
    public int layPhiChuyenTien(){
        choPhanTuXuatHienVaClick(driver,LblFee);
        String textfee = layTextCuaPhanTu(driver,LblFee);
        int fee = Integer.parseInt(textfee.replaceAll(",", ""));
        return fee;
    }
    public void chonButtonTiepTuc(){
        choPhanTuXuatHienVaClick(driver,BtnContinue);
    }

    public String layNoiDungChuyenTien (){
        String contentbank = layTextCuaPhanTu(driver,LblContentBank);
        return contentbank;
    }
    public void chonButtonXacNhan(){
        choPhanTuXuatHienVaClick(driver,BtnConfirm);
    }

    public void nhapMaOTP (String otp){
        nhapTextVaoPhanTu(driver,TxtOtp,otp);
    }

    public void chonButtonXacNhanLan2(){
        choPhanTuXuatHienVaClick(driver,BtnConfirm2);
    }

    public boolean kiemTraHienThiThongBaoThanhCong (){
        return kiemTraCoHienThiPhanTu(driver,TransSuccess);
    }

    public void kiemTraHienThiSoTien(int expamt){
        NumberFormat en = NumberFormat.getInstance();
        String amount = en.format(expamt);
        kiemTraPhanTuHienThiCoTrungKetQuaMongMuon(driver,amount+" VND",TransAmount);
    }
    public void kiemTraHienThiTenNganHang(String expbankname){
        kiemTraPhanTuHienThiCoTrungKetQuaMongMuon(driver,expbankname,TransBank);
    }

    public void kiemTraHienThiLichSuChuyenTien(String exptime,String exptransfertype, String acc, int amt, int fee, String expfeetype, String exptransfermethod,String expstatus, String expcontent ){
        choPhanTuXuatHienVaClick(driver,BtnHistory);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Date dateexp = null;
        try {
            dateexp = sdf.parse(exptime);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        System.out.println("----dateexp la: " + dateexp);
        String ordertime = layTextCuaPhanTu(driver,LblTransferTime);
        Date dateact = null;
        try {
            dateact = sdf.parse(ordertime);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        System.out.println("----dateact la: " + dateact);
        Assert.assertTrue(dateact.before(dateexp)||dateact.equals(dateexp));

        kiemTraPhanTuHienThiCoTrungKetQuaMongMuon(driver,exptransfertype,LblTransferType);

        kiemTraPhanTuHienThiCoTrungKetQuaMongMuon(driver,acc,LblBankAccount);

        WebElement transferamount = driver.findElement(LblTransferAmount);
        int bankamt = Integer.parseInt(transferamount.getText().replaceAll(",",""));
        Assert.assertEquals(amt, bankamt );

        WebElement transferfee = driver.findElement(LblTransferFee);
        int bankfee = Integer.parseInt(transferfee.getText().replaceAll(",",""));
        Assert.assertEquals(fee, bankfee);

        kiemTraPhanTuHienThiCoTrungKetQuaMongMuon(driver,expfeetype,LblFeeType);

        kiemTraPhanTuHienThiCoTrungKetQuaMongMuon(driver,exptransfermethod,LblTransferMethod);

        kiemTraPhanTuHienThiCoTrungKetQuaMongMuon(driver,expstatus,LblTransferStatus);

        kiemTraPhanTuHienThiCoTrungKetQuaMongMuon(driver,expcontent,LblTransferContent);
    }

    public void chonVaoTabChuyenTienTrongVPS(){
        choPhanTuXuatHienVaClick(driver,TabTranferVPS);
    }

    public void chonLoaiChuyenKhoan(By transfertype){
        choPhanTuXuatHienVaClick(driver,transfertype);
    }
    public void nhapSoTaiKhoanVPS (String acc){
        nhapTextVaoPhanTu(driver,TxtAccountVps,acc);
    }
    public void nhapSoTienChuyenTrongVps(int amt){
        nhapTextVaoPhanTu(driver,TxtAmountVps,String.valueOf(amt));
    }

    public void chonButtonTiepTucChuyenTienTrongVps() {
        choPhanTuXuatHienVaClick(driver,BtnContinuesVps);
    }

    public String layNoiDungChuyenTienTrongVps(){
        String contentbank = layTextCuaPhanTu(driver,LblContentVPS);
        return contentbank;
    }

    public String layTenNguoiNhan(){
        String accounreceivps = layTextCuaPhanTu(driver,LblAccountRecei);
        return accounreceivps;
    }

    public void chonButtonXacNhanChuyenTienTrongVps(){
        choPhanTuXuatHienVaClick(driver,BtnConfirmVps);
    }

    public void nhapMaOtpChuyenTienTrongVps (String otp){
        nhapTextVaoPhanTu(driver,TxtOtpVps,otp);
    }
    public void chonButtonXacNhanChuyenTienTrongVpsLan2(){
        choPhanTuXuatHienVaClick(driver,BtnConfirm2Vps);
    }

    public boolean kiemTraHienThiThongBaoChuyenTienTrongVpsThanhCong (){
        return kiemTraCoHienThiPhanTu(driver,TransSuccessVps);
    }

    public void kiemTraHienThiSoTienChuyenTrongVps(int expamt){
        NumberFormat en = NumberFormat.getInstance();
        String amount = en.format(expamt);
        kiemTraPhanTuHienThiCoTrungKetQuaMongMuon(driver,amount+" VND",AmountVps);
    }
    public void chonTieuKhoanNhanTien(){
        choPhanTuXuatHienVaClick(driver,TxtSubAccount);
        choPhanTuXuatHienVaClick(driver,BtnSubAccount);
    }
    public void kiemTraHienThiTenNguoiNhan(String expname){
        WebElement getreceivername = driver.findElement(ReceiverNameVps);
        Assert.assertEquals(expname, getreceivername.getText() );
    }

}