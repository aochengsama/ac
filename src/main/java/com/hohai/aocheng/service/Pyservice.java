package com.hohai.aocheng.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

public class Pyservice {


    public String  run(String a1,String a2,String a3,String a4,String a5,
                     String a6 ,String a7,String  a8, String a9 ,String a10 ,
                     String a11 ,String a12 ,String a13 ,String a14 ,String a15,String a16,
                       String a17,String a18,String a19){

        String s="";
        String line = null;
        try {
            //tiaoshi_ex.py
            //tiaoshiall5.py
            //D:\IMC-ylj\Client_lib\file\venv\Scripts\python.exe
            //D:\anaconda3\python.exe
            String[] args1 = new String[] { "D:\\anaconda3\\python.exe",
                    "D:\\fuzaifenpei_resources\\tiaoshi_ceshi2.py",
                    a1,a2,a3,a4,a5,a6 ,a7, a8, a9 ,a10 ,a11 ,a12 ,a13 ,a14 ,a15,a16,a17,a18,a19};
            Process proc = Runtime.getRuntime().exec(args1);// 执行py文件

            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));


            line = in.readLine();

//            while ((line = in.readLine()) != null) {
//                System.out.println(line);
//            }
            in.close();
            proc.waitFor();


        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return line;
    }

}
