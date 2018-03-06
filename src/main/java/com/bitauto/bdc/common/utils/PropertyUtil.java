package com.bitauto.bdc.common.utils;

import org.springframework.beans.factory.annotation.Value;

import java.io.*;
import java.util.Date;
import java.util.Properties;
import java.util.Set;

/**
 * Created by michealzhang on 2017/11/15.
 */
@Deprecated
public class PropertyUtil {

    @Value("${config.schedule-flag}")
    private String PATH="";


    public  String getProperty(String key){
        String res = null;
        Properties prop = new Properties();
        try {
            InputStream in = new BufferedInputStream(new FileInputStream(PATH));
            prop.load(in);
            res = prop.getProperty(key);
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }


    public  void setProperty(String key,String value){

        try {

            Properties prop = new Properties();
            InputStream in = new BufferedInputStream(new FileInputStream(PATH));
            prop.load(in);
            Set<String> set = prop.stringPropertyNames();
            set.forEach(s -> {
                prop.setProperty(s,prop.getProperty(s));

            });
            prop.setProperty(key,value);

            FileOutputStream oFile = new FileOutputStream(PATH, false);//false表示追加关闭
            prop.store(oFile,DateUtils.format(new Date(),DateUtils.DATE_TIME_PATTERN));
            oFile.flush();
            oFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//        setProperty("test","test2");
//        System.out.println("finished");
//    }



}
