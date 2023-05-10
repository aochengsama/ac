package com.hohai.aocheng.dao;

import com.hohai.aocheng.pojo.Data;
import com.hohai.aocheng.pojo.Data2;
import com.hohai.aocheng.utils.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Data2Dao implements Data2Daoimpl{
    @Override
    public List<Data2> getalldata() throws SQLException {
        // TODO Auto-generated method stub
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy/MM/dd HH");
        //获取连接
        Connection con = jdbc.con();
        //查询语句编译
        String sql = "select * from nrdata2 order by id";
        PreparedStatement per = con.prepareStatement(sql);
        //executeQuery()方法查询到数据库的相应结果存放到ResultSet
        ResultSet rest = per.executeQuery();
        //建立list集合存放输出查询结果
        List<Data2> dataList = new ArrayList<Data2>();
        //rest.next()方法将指针下移直到没下一行返回FALSE。
        while (rest.next()) {
            //将查询结果依次储存
            String id = rest.getString("id");

            Double chushi = rest.getDouble("chushi");
            Double jizu1 = rest.getDouble("jizu1");
            Double jizu2 = rest.getDouble("jizu2");
            Double jizu3 = rest.getDouble("jizu3");
            Double jizu4 = rest.getDouble("jizu4");
            Double jizu5 = rest.getDouble("jizu4");
            Double jizu6 = rest.getDouble("jizu4");


            //建立一个新对象放置结果
            Data2 data2 = new Data2(id, chushi, jizu1,jizu2, jizu3, jizu4,jizu5,jizu6);
            //将结果增加到list集合中
            dataList.add(data2);
        }

        return dataList;
    }
}
