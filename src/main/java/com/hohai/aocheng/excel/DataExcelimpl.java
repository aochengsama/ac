package com.hohai.aocheng.excel;

import com.hohai.aocheng.pojo.Data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface DataExcelimpl {
    List<Data> getalldata() throws IOException;
}
