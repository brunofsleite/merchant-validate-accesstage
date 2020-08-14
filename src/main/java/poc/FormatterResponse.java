package poc;

import com.google.gson.Gson;
import entity.ValidateBank;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class FormatterResponse {
    public static void main(String[] args) {
        FileInputStream file = null;
        DataFormatter formatter = new DataFormatter();
        try {
            file = new FileInputStream(new File("C:\\temp\\ECsTombamentoProd2.xlsx"));

            XSSFWorkbook workbook = new XSSFWorkbook(file);

            XSSFSheet sheet = workbook.getSheetAt(0);

            ArrayList<ValidateBank> validateBanks = new ArrayList<>();

            for(int i=sheet.getFirstRowNum()+1;i<=sheet.getLastRowNum();i++){
                Row row = sheet.getRow(i);

                try {

                    Cell cell = row.getCell(10);

                    String cellJson = cell.toString();
                    Gson gson = new Gson();
                    ValidateBank validateBank = gson.fromJson(cellJson, ValidateBank.class);

                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    cell.setCellValue(Integer.parseInt(validateBank.getId()));

                    FileOutputStream fos = new FileOutputStream("C:\\temp\\ECsTombamentoProd2.xlsx");
                    workbook.write(fos);
                    fos.close();

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
