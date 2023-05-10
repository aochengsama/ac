package com.hohai.aocheng.pojo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
public class Plan {
    int id;
    Date time;
    Double power;
    Double out;
    Double fin;
    Double rin;
}
