package com.bitauto.bdc.modules.oozieServer.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserGroupUtil {

    public  static   String path =  "/data/web_server/shuchang_name.txt";
    //public  static  String path =  "d:/shuchang_name.txt";

    public  static List<String> getGroupUserName(){
        BufferedReader br = null;
        List<String> list = new ArrayList<String>();
        try {
            br = new BufferedReader(new FileReader(path));//构造一个BufferedReader类来读取文件
           // StringBuilder result = new StringBuilder();
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
               // result.append("'").append(s.split(",")[1]).append("'").append(",");
                list.add(s.split(",")[1]);
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

    public  static String  getGroupNumber(){
        BufferedReader br = null;
        StringBuilder result = new StringBuilder("");
        try {
            br = new BufferedReader(new FileReader(path));//构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                result.append(s.split(",")[0]).append("|");
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
        return result.toString().length()>2?result.toString().substring(0,result.toString().length()-1) :result.toString();
    }


   public  static  void  main(String [] args){
      getGroupUserName();
   }
}
