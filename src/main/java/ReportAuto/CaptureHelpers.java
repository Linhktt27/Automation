package ReportAuto;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import ultilities.PropertiesFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CaptureHelpers {
    //Lấy đường dẫn đến project hiện tại
    static String projectPath = System.getProperty("projectPath");
    //Tạo format ngày giờ để xíu gắn dô cái name của screenshot hoặc record video
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");

    public static String captureScreenshot(WebDriver driver, String screenName) {
        PropertiesFile.setPropertiesFile();
        try {
            // Tạo tham chiếu đối tượng của TakesScreenshot với dirver hiện tại
            TakesScreenshot ts = (TakesScreenshot) driver;
            // Gọi hàm getScreenshotAs để chuyển hóa hình ảnh về dạng FILE
            File source = ts.getScreenshotAs(OutputType.FILE);
            //Kiểm tra folder nếu không tồn tại thì tạo folder
            File theDir = new File(projectPath + PropertiesFile.getPropValue("exportCapturePath"));
            if (!theDir.exists()) {
                theDir.mkdirs();
            }
            // Chổ này đặt tên thì truyền biến "screenName" gán cho tên File chụp màn hình
            File newfile = new File(PropertiesFile.getPropValue("exportCapturePath") + "/" + screenName + "_" + dateFormat.format(new Date()) + ".png");
            FileHandler.copy(source, newfile);
            System.out.println("Screenshot taken: " + newfile.getAbsolutePath());
            return newfile.getAbsolutePath();
        } catch (Exception e) {
            System.out.println("Exception while taking screenshot: " + e.getMessage());
            return null;
        }
    }
}