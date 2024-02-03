package ReportAuto;

import io.cucumber.gherkin.GherkinParser;
import io.cucumber.java.Scenario;
import io.cucumber.messages.types.Envelope;
import io.cucumber.messages.types.PickleStep;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestReportTemplate {
    private String stt;
    private String title;
    private String steptest;
    private String expected;
    private String result;

    private String actual;
    private String image = null;
    private String linkimage = null;

    CaptureHelpers help = new CaptureHelpers();

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

    public void writeTestData (Scenario scenario, WebDriver driver, String featurename) throws IOException {
        String fileName = "ReportExcel.xlsx";
        String fileTemplate = "TestcaseTemplate.xlsx";

        String packagePath = "src/test/java/Features/";
        String featureFileName = featurename;
        String featureFilePath = packagePath + featureFileName;
        Path path = Paths.get(featureFilePath);

        GherkinParser gherkinParser = GherkinParser.builder().build();
        Stream<Envelope> pickles = gherkinParser.parse(path).filter(envelope -> envelope.getPickle().isPresent());
        List<Envelope> envelopeList = pickles.collect(Collectors.toList());
        List<PickleStep> lstStep = envelopeList.get(ExcelUtils.i-1).getPickle().get().getSteps();
        String step = "";
        String then = "";
        for (int i = 0; i < lstStep.size(); i++) {
            PickleStep p = lstStep.get(i);
            if (i + 1 < lstStep.size()) {
                step += p.getText() + "\n";
            } else {
                then += p.getText() + "\n";
            }
        }


        ExcelUtils.workbook = ExcelUtils.getWorkbook();
        if (ExcelUtils.workbook == null) {
            ExcelUtils.workbook = ExcelUtils.readXLSXFile(fileTemplate);
        }
        if (scenario.isFailed()) {
            System.out.println("Result: False");
            // Take a screenshot
            String url = help.captureScreenshot(driver, scenario.getName());

            Sheet sheet = ExcelUtils.workbook.getSheetAt(0);

            Row row = sheet.getRow(ExcelUtils.i);

            if (row != null) {
                Cell cell0 = row.getCell(0);
                cell0.setCellValue(ExcelUtils.i);

                Cell cell1 = row.getCell(1);
                cell1.setCellValue(scenario.getName());

                Cell cell2 = row.getCell(2);
                cell2.setCellValue(step);

                Cell cell3 = row.getCell(3);
                cell3.setCellValue(then);

                Cell cell5 = row.getCell(5);
                cell5.setCellValue(String.valueOf(scenario.getStatus()));
                ExcelUtils.addImageToCell(ExcelUtils.workbook, row, 6, url);

            }

            ExcelUtils.writeXLSXFile(ExcelUtils.workbook, fileName);
        } else {
            System.out.println("Thanh Cong " + ExcelUtils.i);

            Sheet sheet = ExcelUtils.workbook.getSheetAt(0);

            Row row = sheet.getRow(ExcelUtils.i);
            if (row != null) {
                Cell cell0 = row.getCell(0);
                cell0.setCellValue(ExcelUtils.i);

                Cell cell1 = row.getCell(1);
                cell1.setCellValue(scenario.getName());

                Cell cell2 = row.getCell(2);
                cell2.setCellValue(step);

                Cell cell3 = row.getCell(3);
                cell3.setCellValue(then);


                Cell cell5 = row.getCell(5);
                cell5.setCellValue(String.valueOf(scenario.getStatus()));
            }

            ExcelUtils.writeXLSXFile(ExcelUtils.workbook, fileName);
        }
    }
}


