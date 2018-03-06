package com.bitauto.bdc.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * 日期处理
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年12月21日 下午12:53:33
 */
public class DateUtils {
	/** 时间格式(yyyy-MM-dd) */
	public final static String DATE_PATTERN = "yyyy-MM-dd";
	/** 时间格式(yyyy-MM-dd HH:mm:ss) */
	public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    /** 时间格式(Tue, 27 Feb 2018 16:00:00 GMT) */
	public final static SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", java.util.Locale.US);


	
	public static String format(Date date) {
        return format(date, DATE_PATTERN);
    }

    public static String format(Date date, String pattern) {
        if(date != null){
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }

    public static String getLastDay(Date date) {
        return getAroundDate(date, -1, DATE_PATTERN);
    }

    public static String getAroundDate(Date date, int amount, String pattern) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE, amount);
        date=calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        String dateString = formatter.format(date);
        return dateString;
    }

    /**
     * @param dateString Thu May 18 2017 00:00:00 GMT+0800 (中国标准时间)
     * @return 年月日;
     */
    public static String parseTime(String dateString) {
        dateString = dateString.replace("GMT", "").replaceAll("\\(.*\\)", "");
        //将字符串转化为date类型，格式2016-10-12
        SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss z", Locale.ENGLISH);
        Date dateTrans = null;
        try {
            dateTrans = format.parse(dateString);
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dateTrans);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateString;

    }

    public static String  parseGMTTime(String dateString){
        try {
            Date date = sdf.parse(dateString.toString());
            return  format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
