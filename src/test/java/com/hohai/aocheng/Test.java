package com.hohai.aocheng;

import com.hohai.aocheng.dao.*;
import com.hohai.aocheng.excel.*;
import com.hohai.aocheng.mapper.Data2Mapper;
import com.hohai.aocheng.mapper.DataMapper;
import com.hohai.aocheng.mapper.FigMapper;
import com.hohai.aocheng.mapper.PlanMapper;
import com.hohai.aocheng.pojo.Data;
import com.hohai.aocheng.pojo.Data2;
import com.hohai.aocheng.pojo.Data_op;
import com.hohai.aocheng.pojo.Plan;
import com.hohai.aocheng.service.Figservice;
import com.hohai.aocheng.service.Pyservice;
import com.hohai.aocheng.utils.MybatisUtils;
import com.mysql.cj.Session;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Test {
    @org.junit.jupiter.api.Test
    public void test1() throws ParseException, SQLException {
        SqlSession session = MybatisUtils.getSession();
        PlanMapper planMapper=session.getMapper(PlanMapper.class);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy/MM/dd HH");
        Date date1=simpleDateFormat.parse("2012/11/09 00");
        Date date2=simpleDateFormat.parse("2012/11/09 01");
        for (Plan plan : planMapper.getdatabyiddate(0,date1,date2)) {
            System.out.println(plan);
        }


    }

    @org.junit.jupiter.api.Test
    public void test2() throws IOException {
        FigExcelimpl figExcelimpl=new FigExcel();
        String s = figExcelimpl.getfigbyid("111");
        Figservice.GenerateImage(s,"D:\\IDEA_workplace\\fuzaifenpei\\src\\main\\resources\\images\\img.jpg");


    }

    @org.junit.jupiter.api.Test
    public void test3() throws IOException {
        Pyservice pyservice=new Pyservice();
        String a1 = "二滩水电站";
        String a2 = "2020/06/01 00";
        String a3="2020/06/07 00";
        String a4="6";
        String a5="3300";
        String a6="550,551,552,553,554,555";
        String a7="400,401,402,403,404,405";
        String a8="180,181,182,183,184,185";
        String a9="0";
        String a10="2226";
        String a11="0";
        String a12="19700";
        String a13="24.2";
        String a14="57.9";
        String a15="100";
        String a16="动态规划计算负荷分配";
        String a17="优化出力";
        String a18="189";
        String a19="135";
        String runs = pyservice.run(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15,a16,a17,a18,a19);
        System.out.println(runs);


    }

    @org.junit.jupiter.api.Test
    public void test4() throws ParseException, SQLException, IOException {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy/MM/dd HH");
        Date date1=simpleDateFormat.parse("2002/11/09 00");
        Date date2=simpleDateFormat.parse("2022/11/09 01");
        Data_opDaoimpl opDaoimpl=new Data_opDap();
        List<Data_op> opList = opDaoimpl.getdatabyiddate(date1, date2);
        for (Data_op data_op : opList) {
            System.out.println(data_op);
        }
    }
    @org.junit.jupiter.api.Test
    public void test5() throws SQLException, IOException {
        Data2Daoimpl daoimpl=new Data2Dao();
        List<Data2> data2List = daoimpl.getalldata();
        for (Data2 data2 : data2List) {
            System.out.println(data2);
        }
    }

}
