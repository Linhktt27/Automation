package ReportAuto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFHyperlink;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.io.IOException;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class TestReportTemplate {
    private String stt;
    private String title;
    private String steptest;
    private String expected;
    private String result;

    private String actual;
    private String image = null;
    private String linkimage = null;

    public String getActual() {
        return actual;
    }

    public void setActual(String actual) {
        this.actual = actual;
    }

    public String getStt() {
        return stt;
    }

    public void setStt(String stt) {
        this.stt = stt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSteptest() {
        return steptest;
    }

    public void setSteptest(String steptest) {
        this.steptest = steptest;
    }

    public String getExpected() {
        return expected;
    }

    public void setExpected(String expected) {
        this.expected = expected;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLinkimage() {
        return linkimage;
    }

    public void setLinkimage(String linkimage) {
        this.linkimage = linkimage;
    }

    public void writeTestData (int startIndex, Row row, XSSFSheet sheet) throws IOException{
        CreationHelper creationHelper = sheet.getWorkbook().getCreationHelper();
        CellStyle globalStyle = row.getRowStyle();
        Cell cell;

        cell = row.createCell(startIndex);
        cell.setCellValue(getStt());
        cell.setCellStyle(globalStyle);

        cell = row.createCell(startIndex+1);
        cell.setCellValue(getTitle());
        cell.setCellStyle(globalStyle);

        cell = row.createCell(startIndex+2);
        cell.setCellValue(getSteptest());
        cell.setCellStyle(globalStyle);

        cell = row.createCell(startIndex+3);
        cell.setCellValue(getExpected());
        cell.setCellStyle(globalStyle);

        cell = row.createCell(startIndex+4);
        cell.setCellValue(getActual());
        cell.setCellStyle(globalStyle);


        cell = row.createCell(startIndex+5);
        cell.setCellValue(getResult());
        cell.setCellStyle(globalStyle);

        if (getImage() != null){
            cell = row.createCell(startIndex+6);
            cell.setCellStyle(globalStyle);
            ExcelUtils.writeImage(getImage(),row,cell,sheet);

            cell = row.createCell(startIndex+7);
            cell.setCellValue("Link screenshot");
            cell.setCellStyle(globalStyle);

            //Tao hyperlink
            XSSFHyperlink hyperlink = (XSSFHyperlink) creationHelper.createHyperlink(HyperlinkType.URL);
            hyperlink.setAddress(getImage().replace("\\","/")); //set address vao hyperlink
            cell.setHyperlink(hyperlink);
        }
    }
}
