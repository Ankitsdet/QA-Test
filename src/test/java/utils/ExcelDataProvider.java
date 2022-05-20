package utils;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;

public class ExcelDataProvider {

    XSSFWorkbook wb;

    public ExcelDataProvider() {

        File src = new File("./testData/Data.xlsx");

        try {
            FileInputStream fis = new FileInputStream(src);
            wb = new XSSFWorkbook(fis);
        } catch (Exception e) {
            System.out.println("Unable to read excel file" + e.getMessage());
        }

    }

    public String getStringData(String sheetName, int row, int column) {
        return wb.getSheet(sheetName).getRow(row).getCell(column).getStringCellValue();
    }

}
