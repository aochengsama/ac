package com.hohai.aocheng.excel;

import com.hohai.aocheng.pojo.Data2;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FigExcel implements FigExcelimpl{
    @Override
    public String getfigbyid(String id) throws IOException {
        //获取文件流
        FileInputStream inputStream = new FileInputStream("D:\\fuzaifenpei_resources\\nrfigs.xlsx");

        //1.创建工作簿,使用excel能操作的这边都看看操作
        Workbook workbook = new XSSFWorkbook(inputStream);
        //2.得到表
        Sheet sheet = workbook.getSheetAt(0);

        Row row = sheet.getRow(1);
        String result="";
        switch (id){
            case "111":
                Cell cell1 = row.getCell(0);
                result = cell1.getStringCellValue();
                break;
            case "222":
                Cell cell2 = row.getCell(1);
                result = cell2.getStringCellValue();
                break;
            case "333":
                Cell cell3 = row.getCell(2);
                result = String.valueOf(cell3.getNumericCellValue());
                break;
        }



        return result;
    }
}
