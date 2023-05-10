package com.hohai.aocheng.dao;

import com.hohai.aocheng.pojo.Data_op;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface Data_opDaoimpl {
    List<Data_op> getdatabyiddate(Date date1, Date date2) throws SQLException;
}
