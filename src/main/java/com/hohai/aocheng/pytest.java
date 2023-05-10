package com.hohai.aocheng;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class pytest {
    public static void main(String[] args) {
        String a1 = "二滩水电站";
        String a2 = "2023/1/1 02";
        String a3="2023/1/1 04";
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

        try {
            String[] args1 = new String[] { "D:\\anaconda3\\python.exe",
                    "D:\\IDEA_workplace\\fuzaifenpei\\src\\main\\java\\com\\hohai\\aocheng\\tiaoshi_ex.py",
                    a1,a2,a3,a4,a5,a6 ,a7, a8, a9 ,a10 ,a11 ,a12 ,a13 ,a14 ,a15,a16};
            Process proc = Runtime.getRuntime().exec(args1);// 执行py文件

            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            proc.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}
