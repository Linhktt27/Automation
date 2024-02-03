package ReportAuto;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.*;

public class ExcelUtils {
    public static int i = 0;
    public static Workbook workbook = null;
    public static final String CHROME_DRIVER_SRC = System.getProperty("chromeDriverPath");
    public static final String DATA_SRC = System.getProperty("dataPath");
    public static final String IMAGES_SRC = System.getProperty("exportCapturePath");

//Lay workbook
    public static XSSFWorkbook getWorkbook (String filePath) throws IOException{
        File src = new File(filePath);
        if (!src.exists()){
            throw new IOException("Khong ton tai file voi duong dan"+filePath);
        }
        FileInputStream fis = new FileInputStream(src);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        fis.close();
        return workbook;
    }

    //Lay sheet
    public static XSSFSheet getSheet(XSSFWorkbook workbook, String sheetName ){
        XSSFSheet sheet = workbook.getSheet(sheetName);
        if (sheet==null){
            throw new NullPointerException("Sheet "+sheetName+" khong ton tai, them du lieu that bai!");
        }
        return sheet;
    }
//Dinh dang excel cho dep
    public static CellStyle getRowStyle(XSSFWorkbook workbook){
        CellStyle rowStyle = workbook.createCellStyle();
        rowStyle.setAlignment(HorizontalAlignment.CENTER);
        rowStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        rowStyle.setWrapText(true);
        return rowStyle;
    }

    public static String getCellValue (XSSFSheet sheet, int row, int column){
        String returnValue;
        XSSFCell cell = sheet.getRow(row).getCell(column);
        try{
            if (cell.getCellType() == CellType.STRING){
                returnValue = cell.getStringCellValue();
            } else if (cell.getCellType() == CellType.NUMERIC) {
                returnValue = String.format("%.0f",cell.getNumericCellValue());
            }else {
                returnValue = "";
            }
        }catch (Exception e){
            returnValue = "";
        }
        return returnValue;
    }

    public static void takeScreenShot (WebDriver driver, String outputSrc) throws IOException{
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File(outputSrc));
    }
public static Object[][] readSheetData (XSSFSheet sheet){
        int rows = sheet.getPhysicalNumberOfRows(); //tong so row
        int columns = sheet.getRow(0).getLastCellNum(); //tong so column
    Object[][] data = new Object[rows-1][columns]; //tao mang 2 chieu, chua 1 cot cho title
    for (int row = 1; row <rows; row++){
        for (int col = 0;col < columns;col++){
            data[row-1][col] = ExcelUtils.getCellValue(sheet, row, col);
        }
    }
    return data;
}

public static void writeImage (String image, Row row, Cell cell, XSSFSheet sheet) throws IOException{
    InputStream is = new FileInputStream(image);//lay hinh, image la duong dan
    byte[] bytes = IOUtils.toByteArray(is);
    int pictureId = sheet.getWorkbook().addPicture(bytes, XSSFWorkbook.PICTURE_TYPE_PNG); //tao doi tuong hinh anh vao workbook, chua co hinh that
    is.close();
    XSSFDrawing drawing = sheet.getDrawingPatriarch();//khoi tao de dua hinh anh len excel
    ClientAnchor anchor = new XSSFClientAnchor(); //dinh vi
    anchor.setCol1(cell.getColumnIndex());
    anchor.setRow1(row.getRowNum());
    anchor.setCol2(cell.getColumnIndex()+1);
    anchor.setRow2(row.getRowNum()+1);
    drawing.createPicture(anchor,pictureId); //pictureId de biet bo hinh nao vao
}

public static void export (String outputSrc, XSSFWorkbook workbook) throws IOException {
    FileOutputStream out = new FileOutputStream(outputSrc);
    workbook.write(out);
    out.close();
}

    public static Workbook readXLSXFile(String fileName) throws IOException {
        ClassLoader classLoader = ExcelUtils.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        return new XSSFWorkbook(inputStream);
    }

    public static void writeXLSXFile(Workbook workbook, String fileName) throws IOException {
        File tempFile = File.createTempFile(fileName, ".xlsx");
        try (FileOutputStream outputStream = new FileOutputStream(tempFile)) {
            workbook.write(outputStream);
        }
// Đường dẫn của tệp tạm thời
        String tempFilePath = tempFile.getAbsolutePath();

// Thư mục resources (để tệp sẽ được đóng gói)
        String resourcesDirectory = "src/test/resources/";

// Thư mục đích (trong thư mục resources)
        String destinationDirectory = resourcesDirectory + fileName;

// Sao chép tệp tạm thời vào thư mục resources
        try (InputStream inputStream = new FileInputStream(tempFilePath);
             OutputStream outputStream = new FileOutputStream(destinationDirectory)) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
        }
// Xóa tệp tạm thời sau khi sao chép
        tempFile.delete();
    }

    public static void addImageToCell(Workbook workbook, Row row, int column, String imagePath) throws IOException {
        InputStream imageStream = new FileInputStream(imagePath);
        byte[] bytes = IOUtils.toByteArray(imageStream);
        int pictureIdx = workbook.addPicture(bytes, Workbook.PICTURE_TYPE_JPEG);

        CreationHelper helper = workbook.getCreationHelper();
        Drawing<?> drawing = row.getSheet().createDrawingPatriarch();

        ClientAnchor anchor = helper.createClientAnchor();
        anchor.setCol1(column);
        anchor.setRow1(row.getRowNum());
        anchor.setCol2(column+1);
        anchor.setRow2(row.getRowNum()+1);


        Picture pict = drawing.createPicture(anchor, pictureIdx);
        pict.resize(1,1); // Thay đổi kích thước hình ảnh theo ô
    }

    public static Workbook getWorkbook() {
        if(workbook != null) {
            return workbook;
        }
        return null;
    }

}

