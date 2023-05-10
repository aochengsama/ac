package com.hohai.aocheng.excel;

import com.hohai.aocheng.pojo.Data;
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

public class Data2Excel implements Data2Excelimpl{
    @Override
    public List<Data2> getalldata() throws IOException {
        //获取文件流
        FileInputStream inputStream = new FileInputStream("D:\\fuzaifenpei_resources\\nrdata2.xlsx");

        //1.创建工作簿,使用excel能操作的这边都看看操作
        Workbook workbook = new XSSFWorkbook(inputStream);
        //2.得到表
        Sheet sheet = workbook.getSheetAt(0);
        //建立list集合存放输出查询结果
        List<Data2> data2List = new ArrayList<Data2>();

        int row_num = sheet.getPhysicalNumberOfRows();
        for (int i = 1; i < row_num; i++) {
            Row row=sheet.getRow(i);
            Data2 data=new Data2();
            for (int i1 = 0; i1 < 7; i1++) {
                Cell cell=row.getCell(i1);
                if (cell!=null){
                    Double s=111111.;
                    Date dateCellValue=new Date();
                    s = cell.getNumericCellValue();


                    switch (i1){
                        case 0:
                            data.setId(String.valueOf(s));
                        case 1:
                            data.setChushi(s);
                        case 2:
                            data.setJizu1(s);
                        case 3:
                            data.setJizu2(s);
                        case 4:
                            data.setJizu3(s);
                        case 5:
                            data.setJizu4(s);
                        case 6:
                            data.setJizu5(s);
                        case 7:
                            data.setJizu6(s);
                    }
                }
            }
            data2List.add(data);
        }

        return data2List;
    }

}
