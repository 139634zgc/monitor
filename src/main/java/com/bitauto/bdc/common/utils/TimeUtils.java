/*
 * renren-toro-tango-service - com.renren.toro.tango.util.TimeUtils.java
 * 2015年4月17日:上午10:59:22
 * Keen
 *
 * jacks808@163.com
 *
 * KKKKKKKKK    KKKKKKK                                                          
 * K:::::::K    K:::::K                                                          
 * K:::::::K    K:::::K                                                          
 * K:::::::K   K::::::K                                                          
 * KK::::::K  K:::::KKK    eeeeeeeeeeee        eeeeeeeeeeee    nnnn  nnnnnnnn    
 *   K:::::K K:::::K     ee::::::::::::ee    ee::::::::::::ee  n:::nn::::::::nn  
 *   K::::::K:::::K     e::::::eeeee:::::ee e::::::eeeee:::::een::::::::::::::nn 
 *   K:::::::::::K     e::::::e     e:::::ee::::::e     e:::::enn:::::::::::::::n
 *   K:::::::::::K     e:::::::eeeee::::::ee:::::::eeeee::::::e  n:::::nnnn:::::n
 *   K::::::K:::::K    e:::::::::::::::::e e:::::::::::::::::e   n::::n    n::::n
 *   K:::::K K:::::K   e::::::eeeeeeeeeee  e::::::eeeeeeeeeee    n::::n    n::::n
 * KK::::::K  K:::::KKKe:::::::e           e:::::::e             n::::n    n::::n
 * K:::::::K   K::::::Ke::::::::e          e::::::::e            n::::n    n::::n
 * K:::::::K    K:::::K e::::::::eeeeeeee   e::::::::eeeeeeee    n::::n    n::::n
 * K:::::::K    K:::::K  ee:::::::::::::e    ee:::::::::::::e    n::::n    n::::n
 * KKKKKKKKK    KKKKKKK    eeeeeeeeeeeeee      eeeeeeeeeeeeee    nnnnnn    nnnnnn
 * 
 */
package com.bitauto.bdc.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 时间工具 <br>
 * 2015年4月17日:上午10:59:22
 * 
 */
public class TimeUtils {

    public static final String yyyyMMdd = "yyyyMMdd";
    public static final String yyyy_MM_dd = "yyyy-MM-dd";
    public static final String yyyy_MM = "yyyy-MM";
    public static final String yyyy_MM_CHINESE = "yyyy年MM月";
    public static final String MM_dd_HH_mm_CHINESE = "MM月dd日 HH:mm";
    public static final String MM_dd = "MM-dd";
    public static final String MM_dd_HH_mm = "MM-dd HH:mm";
    public static final String yyyyMMdd_HHmmss = "yyyyMMdd HHmmss";
    public static final String yyyyMMddHHmmss = "yyyyMMddHHmmss";
    public static final String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
    public static final String yyyy_MM_dd_HH_mm_ssS = "yyyy-MM-dd HH:mm:ss.S";
    public static final String yyyy_MM_dd_HH_mm = "yyyy-MM-dd/HH:mm";
    public static final String MMdd = "MM/dd";
    public static final String DEFAULT = yyyy_MM_dd_HH_mm_ss;

    /**
     * 使用指定的时间创建一个Date对象 <br>
     * 2015年6月19日:下午5:44:09<br>
     * <br>
     * 
     * @param year
     * @param month
     *            从0开始
     * @param date
     * @return
     * 
     *         <pre>
     *         </pre>
     */
    public static Date build(
            int year,
            int month,
            int date) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, date);
        return calendar.getTime();
    }

    /**
     * 使用指定的时间创建一个Date对象 <br>
     * 2015年10月8日:上午11:22:16<br>
     * <br>
     * 
     * @param year
     * @param month
     *            start from0
     * @param date
     * @param hourOfDay
     * @param minute
     * @param second
     * @return
     * 
     *         <pre>
     *         </pre>
     */
    public static Date build(
            int year,
            int month,
            int date,
            int hourOfDay,
            int minute,
            int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, date, hourOfDay, minute, second);
        return calendar.getTime();
    }

    public static Date build(
            int year,
            int month,
            int date,
            int hourOfDay,
            int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, date, hourOfDay, minute);
        return calendar.getTime();
    }

    /**
     * 获取今天 <br>
     * 2015年6月23日:下午5:05:30<br>
     * <br>
     * 
     * @return
     */
    public static Date buildToday() {
        try {
            return TimeUtils.translate(new Date(), TimeUtils.yyyy_MM_dd);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获取这个月的第一天
     * 
     * @return
     */
    public static Date buildFirstDayOfThisMonth() {
        Date now = buildNow();
        return buildFirstDayOfThisMonth(now);
    }

    public static Date buildFirstDayOfThisMonth(
            Date now) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    public static Date buildLastDayOfThisMonth(
            Date now) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        return calendar.getTime();
    }

    public static Date buildLastDayOfThisMonth() {
        return buildLastDayOfThisMonth(buildNow());
    }

    /**
     * 获得今天的0点0分0秒对应的Date
     * 
     * @return
     */
    public static Date buildFirstSecondOfToday() {
        Date now = buildNow();
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(now);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取当前时间 <br>
     * 2015年9月22日:下午12:12:55<br>
     * <br>
     * 
     * @return
     */
    public static Date buildNow() {
        return new Date();
    }

    /**
     * 根据格式返回当前时间的字符串 <br>
     * 2015年7月24日:下午4:03:49<br>
     * <br>
     * 
     * @param format
     * @return
     */
    public static String buildByFormat(
            String format) {
        return TimeUtils.format(new Date(), format);
    }

    /**
     * 获取两天之间的日期list <br>
     * 2015年6月23日:下午3:54:40<br>
     * <br>
     * 
     * @param startDate
     * @param containsStartDay
     * @param endDate
     * @param containsEndDay
     * @return 早的日期排在前
     */
    public static List<Date> getDateListBetweenDays(
            Date startDate,
            boolean containsStartDay,
            Date endDate,
            boolean containsEndDay) {
        List<Date> result = new ArrayList<Date>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        if (containsStartDay) {
            Date time = calendar.getTime();
            result.add(time);
        }
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date today = calendar.getTime();
        while (today.compareTo(endDate) < 0) {
            result.add(today);
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            today = calendar.getTime();
        }
        if (containsEndDay) {
            result.add(calendar.getTime());
        }
        return result;
    }

    public static void main(
            String[] args) throws ParseException {
        // Date startDate = TimeUtils.parse("2015-06-01", TimeUtils.yyyy_MM_dd);
        // Date endDate = TimeUtils.parse("2015-06-10", TimeUtils.yyyy_MM_dd);
        // List<Date> dateListBetweenDays = TimeUtils.getDateListBetweenDays(startDate, true, endDate, true);
        // System.out.println(TimeUtils.batchFormat(dateListBetweenDays, TimeUtils.yyyy_MM_dd));
        // System.out.println(print(buildFirstDayOfThisMonth()));
        // System.out.println(print(buildFirstSecondOfToday()));
        // System.out.println(print(TimeUtils.getYesterday(TimeUtils.buildFirstSecondOfToday())));
        // System.out.println(TimeUtils.print(TimeUtils.buildLastDayOfThisMonth(TimeUtils.build(2016, 0, 1))));
        //        System.out.println(TimeUtils.getDay(buildNow()));
        //System.out.println(TimeUtils.getMonth(buildNow()));
        System.out.println(new Date());
        System.out.println(praseGMT("Wed, 16 Aug 2017 01:00:01 GMT"));
    }

    public static String praseGMT(String date) {
        try{
            //date="Wed, 16 Aug 2017 01:00:01 GMT";
            SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'",Locale.US);
            Date d=sdf.parse(date);
            sdf=new SimpleDateFormat(TimeUtils.yyyy_MM_dd_HH_mm_ss);
            return sdf.format(d);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }


    }

    /**
     * 格式化date <br>
     * 2015年4月17日:上午11:17:29<br>
     * <br>
     * 
     * @param date
     * @param format
     * @return
     */
    public static String format(
            Date date,
            String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            String string = sdf.format(date);
            return string;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static final String format(
            Date date) {
        return format(date, TimeUtils.yyyy_MM_dd_HH_mm_ss);
    }

    /**
     * 批量格式化 <br>
     * 2015年6月5日:下午1:35:29<br>
     * <br>
     * 
     * @param dateList
     * @param format
     * @return
     */
    public static List<String> batchFormat(
            List<Date> dateList,
            String format) {
        List<String> result = new ArrayList<String>();
        for (Date date : dateList) {
            result.add(TimeUtils.format(date, format));
        }
        return result;
    }

    /**
     * 把一个list的数据转成long类型 <br>
     * 2015年7月31日:下午5:39:01<br>
     * <br>
     * 
     * @param dates
     * @return
     */
    public static List<Long> batchToLong(
            List<Date> dates) {
        List<Long> result = new ArrayList<Long>();
        for (Date date : dates) {
            result.add(date.getTime());
        }
        return result;
    }

    /**
     * 按照日期格式转化成date <br>
     * 2015年5月14日:下午3:18:42<br>
     * <br>
     * 
     * @param dateString
     * @param format
     * @return
     * @throws ParseException
     */
    public static Date parse(
            String dateString,
            String format) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date date = simpleDateFormat.parse(dateString);
        return date;
    }

    /**
     * 转化日期格式 <br>
     * 2015年5月14日:下午3:52:17<br>
     * <br>
     * 
     * @param src
     * @param srcFormate
     * @param targetFormate
     * @return
     * @throws ParseException
     */
    public static String translate(
            String src,
            String srcFormate,
            String targetFormate) throws ParseException {
        if (src == null)
            return "";
        Date parse = TimeUtils.parse(src, srcFormate);
        String format = TimeUtils.format(parse, targetFormate);
        return format;
    }

    /**
     * 转化日期格式 <br>
     * 2015年5月14日:下午3:52:17<br>
     * <br>
     * 
     * @param src
     * @param targetFormate
     * @return
     * @throws ParseException
     */
    public static Date translate(
            Date src,
            String targetFormate) throws ParseException {
        return TimeUtils.parse(TimeUtils.format(src, targetFormate), targetFormate);
    }

    /**
     * 将时间戳转换为时间 <br>
     * 2015年4月22日 下午4:52:19 <br>
     * <br>
     * 
     * @param timeStamp
     * @return
     */
    public static String converTimeStampToDate(
            String timeStamp) {
        try {
            long timeMillis = Long.parseLong(timeStamp) * 1000;
            Date date = new Date(timeMillis);
            return format(date, "yyyy-MM-dd HH:mm:ss");
        } catch (NumberFormatException e) {
            return timeStamp;
        }
    }

    /**
     * 获取上月同一时期<br>
     * 2015年5月15日 上午11:10:39 <br>
     * <br>
     * 
     * @param currentDate
     * @return
     */
    public static Date getLastMonthDate(
            Date currentDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.MONTH, -1);
        return calendar.getTime();
    }

    /**
     * 取得某一月的同一天date <br>
     * 2015年6月5日:上午11:40:42<br>
     * <br>
     * 
     * @param currentDate
     * @param month
     *            负数向前,正数向后
     * @return
     */
    public static Date getLastMonthDate(
            Date currentDate,
            int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.MONTH, month);
        return calendar.getTime();
    }

    /**
     * 获取下月同一时期<br>
     * 2015年5月15日 上午11:10:39 <br>
     * <br>
     * 
     * @param currentDate
     * @return
     */
    public static Date getNextMonthDate(
            Date currentDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.MONTH, 1);
        return calendar.getTime();
    }

    /**
     * 根据当前日期获取昨日日期<br>
     * 2015年5月18日 下午3:47:47 <br>
     * <br>
     * 
     * @param currentDate
     * @return
     */
    public static Date getYesterday(
            Date currentDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);

        calendar.add(Calendar.DATE, -1);
        return calendar.getTime();
    }

    /**
     * 根据当前日期获取昨日日期<br>
     * 2015年5月18日 下午3:47:47 <br>
     * <br>
     * 
     * @param currentDate
     * @return
     */
    public static Date getTomorrow(
            Date currentDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);

        calendar.add(Calendar.DATE, 1);
        return calendar.getTime();
    }

    /**
     * 2015年5月23日 下午3:04:16 <br>
     * <br>
     * 
     * @param date
     * @return
     */
    public static Date getArriveTime(
            Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 3);
        return c.getTime();
    }

    /**
     * 预计调仓完成时间
     * 
     * @param date
     * @return
     */
    public static Date getTransferCompleteTime(
            Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 7);
        return c.getTime();
    }

    /**
     * 计算相差天数<br>
     * 2015年5月23日 下午4:02:34 <br>
     * <br>
     * 
     * @param startDate
     * @param endDate
     * @return
     */
    public static int getBetweenDays(
            Date startDate,
            Date endDate) {
        long beginTime = startDate.getTime();
        long endTime = endDate.getTime();
        int betweenDays = (int) ((endTime - beginTime) / (1000 * 60 * 60 * 24) + 0.5);

        return betweenDays;
    }

    /**
     *
     * 两个时间相差距离多少天多少小时多少分多少秒
     * @param startDate
     * @param endDate
     * @return long[] 返回值为：{天, 时, 分, 秒}
     */
    public static long[] getDistanceTimes(
            Date startDate,
            Date endDate) {
        long beginTime = startDate.getTime();
        long endTime = endDate.getTime();

        long diff = beginTime - endTime;
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;

        day = diff / (24 * 60 * 60 * 1000);
        hour = (diff / (60 * 60 * 1000) - day * 24);
        min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
        sec = (diff/1000-day*24*60*60-hour*60*60-min*60);
        long[] times = {day, hour, min, sec};
        return times;
    }



    /**
     * 显示时间差 eg: 3天2小时4分34秒
     * @param times
     * @return
     */
    public static String showDistanceTimes(long[] times){
        StringBuffer buffer = new StringBuffer();
        if(times[0] > 0){
            buffer.append(times[0]).append("天");
        }
        if(times[1] > 0){
            buffer.append(times[1]).append("小时");
        }
        if(times[2] > 0){
            buffer.append(times[2]).append("分");
        }
        if(times[3] > 0){
            buffer.append(times[3]).append("秒");
        }
        return buffer.toString();
    }



            /**
             * 计算相差的多少秒
             * @param startDate
             * @param endDate
             * @return
             */
    public static long getBetweenSecs(
            Date startDate,
            Date endDate) {
        long beginTime = startDate.getTime();
        long endTime = endDate.getTime();
        long betweenDays = ((endTime - beginTime) / (1000));

        return betweenDays;
    }

    /**
     * 判断是否是同一天<br>
     * 2015年5月25日 下午6:13:55 <br>
     * <br>
     * 
     * @param dateA
     * @param dateB
     * @return
     */
    public static boolean areSameDay(
            Date dateA,
            Date dateB) {
        Calendar calDateA = Calendar.getInstance();
        calDateA.setTime(dateA);

        Calendar calDateB = Calendar.getInstance();
        calDateB.setTime(dateB);

        return calDateA.get(Calendar.YEAR) == calDateB.get(Calendar.YEAR)
                && calDateA.get(Calendar.MONTH) == calDateB.get(Calendar.MONTH)
                && calDateA.get(Calendar.DAY_OF_MONTH) == calDateB.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 比较时间是否为同一个时间 <br>
     * 2015年10月8日:上午11:29:02<br>
     * <br>
     * 
     * @param dateA
     * @param dateB
     * @return
     * 
     *         <pre>
     *         </pre>
     */
    public static boolean areSameTime(
            Date dateA,
            Date dateB) {
        Calendar calDateA = Calendar.getInstance();
        calDateA.setTime(dateA);

        Calendar calDateB = Calendar.getInstance();
        calDateB.setTime(dateB);

        return calDateA.get(Calendar.YEAR) == calDateB.get(Calendar.YEAR)
                && calDateA.get(Calendar.MONTH) == calDateB.get(Calendar.MONTH)
                && calDateA.get(Calendar.DAY_OF_MONTH) == calDateB.get(Calendar.DAY_OF_MONTH)
                && calDateA.get(Calendar.HOUR_OF_DAY) == calDateB.get(Calendar.HOUR_OF_DAY)
                && calDateA.get(Calendar.MINUTE) == calDateB.get(Calendar.MINUTE)
                && calDateA.get(Calendar.SECOND) == calDateB.get(Calendar.SECOND);

    }

    /**
     * 获取days天之后的date对象 <br>
     * 2015年6月3日:下午8:53:05<br>
     * <br>
     * 
     * @param startDate
     * @param days
     *            正数向后,负数向前
     * @return
     */
    public static Date getBeforeDate(
            Date startDate,
            int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        return calendar.getTime();
    }

    public static String getMMdd(
            String dateString) {
        try {
            Date date = parse(dateString, yyyy_MM_dd);
            return format(date, MMdd);
        } catch (ParseException e) {
            e.printStackTrace();
            return dateString;
        }
    }

    /**
     * 计算当天剩余的秒数
     * 
     * @param date
     * @return
     */
    public static long getDayLastSeconds(
            Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        long lastSeconds = (calendar.getTime().getTime() - date.getTime()) / 1000;
        return lastSeconds;
    }

    // 获得当天24点时间
    public static Date getFastArriveTime() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }

    public static Date getNormalArriveTime(
            Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 1);
        return c.getTime();
    }

    /**
     * 返回规定新鲜事时间戳 <br>
     * 小于等于60秒 显示“刚刚更新”<br>
     * 大于60秒小于等于120秒 显示“2分钟前”<br>
     * 大于120秒小于等于180秒 显示“3分钟前”<br>
     * 以此类推一个小时内发布的 显示“4分钟前”……“59分钟前”<br>
     * 大于1小时且是当天发送的 显示发布时的具体时间“15:36”hh:mm<br>
     * 大于1小时且是昨天或者昨天之前（今年内）发送的 显示发布时的具体日期和具体时间 mm-dd hh-mm“05-10 22:00”<br>
     * 大于1小时且是去年及去年之前发送的 显示发布时的具体日期 “2013-10-10”yyyy-mm-dd <br>
     * 
     * @param createTime
     * @return
     */
    public static String getTimeStamp(
            String createTime) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date createDate = formatter.parse(createTime);
            Date currDate = new Date();
            long minusSecond = (currDate.getTime() - createDate.getTime()) / 1000;
            if (minusSecond <= 60) {
                // System.out.println("刚刚更新");
                return "刚刚更新";
            }

            // 一小时以内
            if (minusSecond < 60 * 60) {
                // System.out.println(minusSecond / 60 + "分钟前");
                return minusSecond / 60 + "分钟前";
            }

            Calendar currCalendar = Calendar.getInstance();
            currCalendar.setTime(currDate);

            Calendar createCalendar = Calendar.getInstance();
            createCalendar.setTime(createDate);

            // 当天内，一小时前
            if (minusSecond < 60 * 60 * 24 && currCalendar.get(Calendar.DATE) == createCalendar.get(Calendar.DATE)) {
                // System.out.println(format(createDate, "HH:mm"));
                return format(createDate, "HH:mm");
            }

            // 当年内，一天前
            if (minusSecond < 60 * 60 * 24 * 365
                    && currCalendar.get(Calendar.YEAR) == createCalendar.get(Calendar.YEAR)) {
                // System.out.println(format(createDate, "MM-dd HH:mm"));
                return format(createDate, "MM-dd HH:mm");
            }

            // 一年前
            // System.out.println(format(createDate, "yyyy-MM-dd"));
            return format(createDate, "yyyy-MM-dd");
        } catch (ParseException e) {
            e.printStackTrace();
            return createTime;
        }
    }

    /**
     * 使用小时获取对应的秒 <br>
     * 
     * @param hour
     * @return
     */
    public static int getSecondByHour(
            int hour) {
        return hour * 60;
    }

    /**
     * 获取天对应的秒 <br>
     * 
     * @param day
     * @return
     */
    public static int getSecondByDay(
            int day) {
        return day * getSecondByHour(24);
    }

    /**
     * 获取周对应的秒 <br>
     * 
     * @param week
     * @return
     */
    public static int getSecondByWeek(
            int week) {
        return week * getSecondByDay(7);
    }

    /**
     * print a date object to a human readable string
     * 
     * @param date
     * @return
     */
    public static String print(
            Date date) {
        return format(date, TimeUtils.yyyy_MM_dd_HH_mm_ss);
    }

    /**
     * return formated now time string
     * 
     * @return
     */
    public static String printNow() {
        return print(buildNow());
    }

    public static int getMonth(
            Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH);
    }

    public static int getYear(
            Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    public static int getDay(
            Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }
}
