package com.hohai.aocheng.dao;

import com.hohai.aocheng.pojo.Data;

import java.sql.SQLException;
import java.util.List;

public interface DataDaoimpl {
    List<Data> getalldata() throws SQLException;
}
