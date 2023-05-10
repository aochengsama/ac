package com.hohai.aocheng.dao;

import com.hohai.aocheng.pojo.Plan;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface PlanDaoimpl {
    List<Plan> getdatabyiddate(int id, Date date1, Date date2) throws SQLException;
}
