package com.hohai.aocheng.excel;

import com.hohai.aocheng.pojo.Plan;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PlaneExcel implements PlanExcelimpl{
    @Override
    public List<Plan> getdatabyiddate(int id, Date date1, Date date2) throws IOException, ParseException {
        //获取文件流
        FileInputStream inputStream = new FileInputStream("D:\\fuzaifenpei_resources\\plan.xlsx");

        //1.创建工作簿,使用excel能操作的这边都看看操作
        Workbook workbook = new XSSFWorkbook(inputStream);
        //2.得到表
        Sheet sheet = workbook.getSheetAt(0);
        //建立list集合存放输出查询结果
        List<Plan> plans = new ArrayList<Plan>();

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd HH");
        int row_num = sheet.getPhysicalNumberOfRows();
        for (int i = 1; i < row_num; i++) {
            Row row=sheet.getRow(i);
            Plan plan=new Plan();
            for (int i1 = 0; i1 < 6; i1++) {
                Cell cell=row.getCell(i1);
                if (cell!=null){
                    Double s=111111.;
                    Date dateCellValue=new Date();
                    if (i1!=1){
                        s = cell.getNumericCellValue();
                    }
                    else {
                        dateCellValue = cell.getDateCellValue();
                    }
                    switch (i1){
                        case 0:
                            plan.setId(s.intValue());
                            break;
                        case 1:
                            plan.setTime(dateCellValue);
                            break;
                        case 2:
                            plan.setPower(s);
                        case 3:
                            plan.setOut(s);
                        case 4:
                            plan.setFin(s);
                        case 5:
                            plan.setRin(s);
                    }
                }
            }
            if (plan.getId()==id&&plan.getTime().after(date1)&&plan.getTime().before(date2)){
                plans.add(plan);
            }
        }






        return plans;
    }
}
