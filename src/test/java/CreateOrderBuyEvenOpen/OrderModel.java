package CreateOrderBuyEvenOpen;

import Login.LoginModel;
import RunAutoTest.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderModel extends BasePage {
    WebDriver driver;
    public By BtnBuy = By.xpath("//*[@id=\"btnOrderBuy\"]");
    public By BtnSell = By.xpath("//*[@id=\"btnOrderSell\"]");
    public By BtnAccount = By.xpath("//*[@id=\"txtAccount\"]");
    public By TxtSubAccount = By.xpath("/html/body/ul[3]/li[1]/a");
    public By TxtSymbol = By.xpath("//*[@id=\"txtStock\"]");
    public By SearchSymbol = By.xpath("/html/body/ul[12]/li");

    public By TxtPrice = By.xpath("//*[@id=\"txtPrice\"]");
    public By LblReferacePrice = By.xpath("//*[@id=\"orderPriorClosePrice\"]");

    public By TxtVolume = By.xpath("//*[@id=\"txtVolume\"]");
    public By BtnOrder = By.xpath("//*[@id=\"btnUpdate\"]");

    public By BtnConfirm = By.xpath("//*[@id=\"btnPopupConfirmOrder\"]");

    public By MgsSuccess = By.xpath("//*[@id=\"alertModal\"]/div/div/div[2]/form/table/tbody/tr[5]/td[5]/input");
    public By MgsTypeOrder = By.xpath("//*[@id=\"alertModal\"]/div/div/div[2]/form/table/tbody/tr[4]/td/span");

    public By BtnClosePopup = By.xpath("//*[@id=\"alertModal\"]/div/div/div[2]/form/table/tbody/tr[7]/td/input");

    public By LblOrderTime = By.xpath("//*[@id=\"tblListOrder\"]/tbody/tr[1]/td[3]");

    public By LblSymbolOrder = By.xpath("/html/body/div[1]/b/b/div[5]/div/div[3]/div[1]/div[1]/div[1]/table/tbody/tr[1]/td[6]");

    public By LblOrderPrice = By.xpath("//*[@id=\"tblListOrder\"]/tbody/tr[1]/td[9]");
    public By LblOrderStatus =By.xpath("/html/body/div[1]/b/b/div[5]/div/div[3]/div[1]/div[1]/div[1]/table/tbody/tr/td[13]");

    public By LblVolumeOrder = By.xpath("/html/body/div[1]/b/b/div[5]/div/div[3]/div[1]/div[1]/div[1]/table/tbody/tr[1]/td[7]/div[1]/div/span");

    public By LblCeilPrice = By.xpath("//*[@id=\"orderCeilingPrice\"]");
    public By LblFloorPrice = By.xpath("//*[@id=\"orderFloorPrice\"]");

    public By BtnStockBalance = By.xpath("//*[@id=\"divShareBalanceTab\"]");
    public By TxtSearchSymbol = By.xpath("//*[@id=\"search_symbol\"]");

    public By LblSymbol = By.xpath("/html/body/ul[17]/li/a");
    public By LblStockBalance = By.xpath("//*[@id=\"divShareBalance\"]/div/table/tbody/tr[2]/td[4]");

    LoginModel login = new LoginModel(driver);
    public OrderModel(WebDriver driver) {
        this.driver = driver;
    }

    public double laySucMua (){
        choLoadPage();
        moveChuotDenPhanTuVaClick(driver,login.BtnPortfolioManagement);
        moveChuotDenPhanTuVaClick(driver,login.BtnAssetsManagement);
        String withdrawablecashbefore = layTextCuaPhanTu(driver,login.LblPurchasingpower);
        double purchasingpowerbefore = Double.parseDouble(withdrawablecashbefore.replaceAll(",", ""));
        return  purchasingpowerbefore;
    }

    public double laySoDuChungKhoan (String sym){
        choLoadPage();
        moveChuotDenPhanTuVaClick(driver,login.BtnPortfolioManagement);
        moveChuotDenPhanTuVaClick(driver,login.BtnAssetsManagement);
        choLoadPage();
        moveChuotDenPhanTuVaClick(driver,BtnStockBalance);
        nhapTextVaoPhanTu(driver,TxtSearchSymbol,sym);
        moveChuotDenPhanTuVaClick(driver,LblSymbol);
        String stockBalance = layTextCuaPhanTu(driver,LblStockBalance);
        double getstockBalance = Double.parseDouble(stockBalance.replaceAll(",", ""));
        return  getstockBalance;
    }

    public void clickVaoButtonMua(){
        choPhanTuXuatHienVaClick(driver,BtnBuy);
    }
    public void clickVaoButtonBan(){
        choPhanTuXuatHienVaClick(driver,BtnSell);
    }


    public void nhapMaCoPhieu(String sym){
        nhapTextVaoPhanTu(driver,TxtSymbol,sym);
        choPhanTuXuatHienVaClick(driver,TxtPrice);
    }

    public String layGiaThamChieu(){
        return  layTextCuaPhanTu(driver,LblReferacePrice);
    }

    public String layGiaTran(){
        return  layTextCuaPhanTu(driver,LblCeilPrice);
    }

    public void nhapGiaDat(String pri){
        nhapTextVaoPhanTu(driver,TxtPrice,pri);
    }

    public void nhapKhoiLuong(int vol){
        String volume = String.valueOf(vol);
        nhapTextVaoPhanTu(driver,TxtVolume,volume);
    }

    public void clickVaoButtonDat(){
        choPhanTuXuatHienVaClick(driver,BtnOrder);
    }

    public void clickVaoButtonXacNhan(){
        choPhanTuXuatHienVaClick(driver,BtnConfirm);
    }

    public void kiemTraHienThiThongBaoThanhCong(String expmsg){
        String getmessagesuccess = layTextCuaPhanTu(driver,MgsSuccess);
        Assert.assertEquals(expmsg, getmessagesuccess );
    }

    public void kiemTraHienThiLoaiLenh(String expordertype){
        String gettypeorder = layTextCuaPhanTu(driver,MgsTypeOrder);
        Assert.assertEquals(expordertype, gettypeorder );
    }

    public void clickDongPopupThongBao(){
        choPhanTuXuatHienVaClick(driver,BtnClosePopup);
    }

    public void kiemTraHienThiLenhMoiTrongSoLenh(String expordertime, String expsymbol, int expvolume, String expprice, String expstatus){
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        Date dateexp = null;
        try {
            dateexp = sdf.parse(expordertime);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        System.out.println("----dateexp la: " + dateexp);
        String ordertime = layTextCuaPhanTu(driver,LblOrderTime);
        Date dateact = null;
        try {
            dateact = sdf.parse(ordertime);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        System.out.println("----dateact la: " + dateact);

        Assert.assertTrue(dateact.before(dateexp)||dateact.equals(dateexp));
        kiemTraPhanTuHienThiCoTrungKetQuaMongMuon(driver,expsymbol,LblSymbolOrder);
        String volume = Integer.toString(expvolume);
        kiemTraPhanTuHienThiCoTrungKetQuaMongMuon(driver,volume,LblVolumeOrder);
        kiemTraPhanTuHienThiCoTrungKetQuaMongMuon(driver,expprice, LblOrderPrice);
        kiemTraPhanTuHienThiCoTrungKetQuaMongMuon(driver,expstatus, LblOrderStatus);
    }

    public void kiemTraSoTienBlock(double purchasingpowerbefore, double pricetc, double fee, int vol  ){

        Double getpurchasingpowerafter = laySucMua();
        Double amtblockcaculate = (pricetc*1000*vol)+((fee/100)*(pricetc*1000*vol));
        System.out.println("----amtblockcaculate la: " + amtblockcaculate);
        Double amtblockact = purchasingpowerbefore - getpurchasingpowerafter;
        Assert.assertEquals(amtblockact, amtblockcaculate );
    }

    public void kiemTraChungKhoanBlock(String sym, double symbolbalancebefore, int volume ){

        Double getstockBalance = laySoDuChungKhoan(sym);
        Double amtblockact = symbolbalancebefore - getstockBalance;
        Assert.assertEquals(Double.valueOf(volume), amtblockact );
    }
}
