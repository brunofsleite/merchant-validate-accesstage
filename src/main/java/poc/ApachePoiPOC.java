package poc;

import com.google.gson.Gson;
import entity.Merchant;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ApachePoiPOC {
    public static void main(String[] args) {
        FileInputStream file = null;
        DataFormatter formatter = new DataFormatter();
        try {
            file = new FileInputStream(new File("C:\\temp\\ecTombamento.xlsx"));

            XSSFWorkbook workbook = new XSSFWorkbook(file);

            XSSFSheet sheet = workbook.getSheetAt(0);

            ArrayList<Merchant> merchants = new ArrayList<>();

            for(int i=sheet.getFirstRowNum()+1;i<=sheet.getLastRowNum();i++){
                Merchant merchant = new Merchant();
                Row row = sheet.getRow(i);
                for(int j=row.getFirstCellNum();j<=row.getLastCellNum();j++){
                    Cell cell = row.getCell(j);
                    merchant.setBank("1"); // Banco do Brasil
                    merchant.setDocumentType("2");
                    if(j == 0) {
                        merchant.setDocumentNumber(formatter.formatCellValue(cell));
                    }
                    if(j == 7) {
                        merchant.setAccount(formatter.formatCellValue(cell));
                    }
                    if(j == 8) {
                        merchant.setAccountDigit(formatter.formatCellValue(cell));
                    }
                    if(j == 8) {
                        merchant.setAccountDigit(formatter.formatCellValue(cell));
                    }
                    if(j == 5) {
                        merchant.setBankBranch(formatter.formatCellValue(cell));
                    }
                    if(j == 6) {
                        merchant.setBankBranchDigit(formatter.formatCellValue(cell));
                    }
                }
                merchants.add(merchant);
            }
            String json = new Gson().toJson(merchants);
            System.out.println(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}