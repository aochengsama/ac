package com.hohai.aocheng.mapper;

import com.hohai.aocheng.pojo.Plan;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface PlanMapper {

    List<Plan> getdatabyiddate(int id, Date date1,Date date2) throws SQLException;
}
