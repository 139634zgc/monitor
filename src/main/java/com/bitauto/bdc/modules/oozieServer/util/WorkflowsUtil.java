package com.bitauto.bdc.modules.oozieServer.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkflowsUtil {

    public  static   String path =  "/data/web_server/workflows_name.txt";
    //public  static  String path =  "d:/workflows_name.txt";

    public  static List<String> getNotworkflowsName(){
        BufferedReader br = null;
        List<String> list = new ArrayList<String>();
        try {
            br = new BufferedReader(new FileReader(path));//构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                list.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(br !=null)
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return list;
    }
}
