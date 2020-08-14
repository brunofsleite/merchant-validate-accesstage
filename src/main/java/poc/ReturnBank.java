package poc;

import com.google.gson.Gson;
import entity.ValidateBank;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;

public class ReturnBank {
    public static void main(String[] args) {
        FileInputStream file = null;
        DataFormatter formatter = new DataFormatter();
        try {
            file = new FileInputStream(new File("C:\\temp\\ECsTombamentoProd-semRetorno.xlsx"));

            XSSFWorkbook workbook = new XSSFWorkbook(file);

            XSSFSheet sheet = workbook.getSheetAt(0);

            ArrayList<ValidateBank> validateBanks = new ArrayList<>();

            for(int i=sheet.getFirstRowNum()+1;i<=sheet.getLastRowNum();i++){
                ValidateBank validateBank = new ValidateBank();
                Row row = sheet.getRow(i);
                for(int j=row.getFirstCellNum();j<=row.getLastCellNum();j++){
                    Cell cell = row.getCell(j);

                    if(j == 10) {
                        validateBank.setId(formatter.formatCellValue(cell));
                    }
                }
                validateBanks.add(validateBank);
                String json = new Gson().toJson(validateBank);

                HttpClient httpClient = new DefaultHttpClient();
                HttpGet httpGet = new HttpGet("http://localhost:8080/bank-domicile/validation/" + Integer.parseInt(validateBank.getId()));
                httpGet.setHeader("Content-type", "application/json");

                try {
                    httpGet.getRequestLine();
                    HttpResponse httpResponse = httpClient.execute(httpGet);
                    HttpEntity responseEntity = httpResponse.getEntity();
                    String content = EntityUtils.toString(responseEntity);
                    System.out.println(content);

                    Cell cell = row.getCell(11);
                    if (cell == null) {
                        cell = row.createCell(11);
                    }
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    cell.setCellValue(content);

                    FileOutputStream fos = new FileOutputStream("C:\\temp\\ECsTombamentoProd-semRetorno.xlsx");
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
