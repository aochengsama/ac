package com.hohai.aocheng.excel;

import com.hohai.aocheng.pojo.Plan;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface PlanExcelimpl {
    List<Plan> getdatabyiddate(int id, Date date1, Date date2) throws IOException, ParseException;
}
