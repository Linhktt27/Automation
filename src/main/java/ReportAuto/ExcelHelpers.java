package ReportAuto;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

public class ExcelHelpers {
    private FileInputStream fis;
    private FileOutputStream fileOut;
    private Workbook  wb;
    private Sheet sh;
    private Cell cell;
    private Row row;
    private CellStyle cellstyle;
    private Color mycolor;
    private String excelFilePath;
    private Map<String, Integer> columns = new HashMap<>();

    public void setExcelFile(String ExcelPath, String SheetName) throws Exception {
        try {
            File f = new File(ExcelPath);

            if (!f.exists()) {
                f.createNewFile();
                System.out.println("File doesn't exist, so created!");
            }
            wb = new XSSFWorkbook();
 //           fis = new FileInputStream(ExcelPath);
  //          wb = WorkbookFactory.create(fis);
            sh = wb.getSheet(SheetName);
            //sh = wb.getSheetAt(0); //0 - index of 1st sheet
            if (sh == null) {
                sh = wb.createSheet(SheetName);
            }

            this.excelFilePath = ExcelPath;


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public String getCellData(int rownum, int colnum) throws Exception{
        try{
            cell = sh.getRow(rownum).getCell(colnum);
            String CellData = null;
            switch (cell.getCellType()){
                case STRING:
                    CellData = cell.getStringCellValue();
                    break;
                case NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell))
                    {
                        CellData = String.valueOf(cell.getDateCellValue());
                    }
                    else
                    {
                        CellData = String.valueOf((long)cell.getNumericCellValue());
                    }
                    break;
                case BOOLEAN:
                    CellData = Boolean.toString(cell.getBooleanCellValue());
                    break;
                case BLANK:
                    CellData = "";
                    break;
            }
            return CellData;
        }catch (Exception e){
            return"";
        }
    }

    //Gọi ra hàm này nè
    public String getCellData(String columnName, int rownum) throws Exception {
        return getCellData(rownum, columns.get(columnName));
    }

    public void setCellData(String text, int rownum, int colnum) throws Exception {
        try{
            for (int i = 0; i < 500; i++) {
                row = sh.createRow(i);
                cell = row.createCell(i);
            }
            row  = sh.getRow(rownum);
            //if(row ==null)
            //{
               // row = sh.createRow(rownum);
               // System.out.println("------Tao row moi");
            //}
            cell = row.getCell(colnum);
           // if (cell==null) {
              //  cell = row.createCell(colnum);
               // System.out.println("------Tao cell moi");
            //}
            System.out.println("------CellType la truoc:" + cell.getCellType());
            System.out.println("------CellValue la truoc:" + cell.getStringCellValue());
            System.out.println("------rownum la:" + rownum);
            System.out.println("------colnum la:" + colnum);

            if (cell.getCellType() == CellType.STRING){
                rownum++;
                System.out.println("------rownum la b:" + rownum);
                row  = sh.getRow(rownum);
                if(row ==null)
                {
                    row = sh.createRow(rownum);
                }
                cell = row.getCell(colnum);
                if (cell==null) {
                    cell = row.createCell(colnum);
                }
            }
            cell.setCellValue(text);

            fileOut = new FileOutputStream(excelFilePath);
            wb.write(fileOut);
            fileOut.flush();
            fileOut.close();
        }catch(Exception e){
            throw (e);
        }
    }
}
