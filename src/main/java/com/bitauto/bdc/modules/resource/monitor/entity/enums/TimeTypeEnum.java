/*
 * 文件名：TimeTypeEnum.java
 * 版权：Copyright by www.yiche.com
 * 描述：
 * 修改人：liuming1
 * 修改时间：2017年11月14日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.bitauto.bdc.modules.resource.monitor.entity.enums;

public enum TimeTypeEnum
{
    TIME_30_Min("30min"),
    TIME_1_Hour("1hour"),
    TIME_1_Day("1day"),
    TIME_6_Hour("6hour"),
    TIME_12_Hour("12hour"),
    TIME_1_Week("1week"),
    TIME_1_Month("1month"),
    UNKNOW("-1");
    
    /**
     * 时间枚举字段
     */
    private String timeType;
    
    /**
     * 默认的私有构造函数
     * 
     * @param timeType
     */
    private TimeTypeEnum(String timeType)
    {
        this.timeType = timeType;
    }
    
    /**
     * Description:获取时间值
     * 
     * @return 时间值
     * @see
     */
    public String getVal()
    {
        return timeType;
    }
    
    /**
     * Description: 通过参数值获取枚举
     * 
     * @param timeType
     *            时间参数值
     * @return
     * @see
     */
    public static TimeTypeEnum getTimeType(String timeType)
    {
        TimeTypeEnum[] typeEnums = TimeTypeEnum.values();
        for (TimeTypeEnum timeTypeEnum : typeEnums)
        {
            if (timeTypeEnum.timeType.equalsIgnoreCase(timeType))
            {
                return timeTypeEnum;
            }
        }
        return UNKNOW;
    }
}
