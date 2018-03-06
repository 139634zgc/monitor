package com.bitauto.bdc.common.utils;

/**
 * 基本数据类型解析类，提供默认值处理，当数据解析异常时可以使用用户自定义的默认数据进行处理
 *
 * @author liuming1
 */
public final class BasicTypeParse
{
    /**
     * 默认的私有构造函数
     */
    private BasicTypeParse()
    {

    }

    /**
     * long 类型的数据处理
     *
     * @param lData
     *            需要被解析的字符串
     * @param defaultVal
     *            解析异常时使用的默认值
     * @return 解析之后的字段
     */
    public static long parseLong(final String lData, final long defaultVal)
    {
        try
        {
            return Long.parseLong(lData);
        }
        catch (final Exception e)
        {
            return defaultVal;
        }
    }

    /**
     * int 类型数据的处理
     *
     * @param iData
     *            需要被解析的字段
     * @param defaultVal
     *            解析异常时使用的默认值
     * @return 解析之后的字段
     */
    public static int parseInt(final String iData, final int defaultVal)
    {
        try
        {
            return Integer.parseInt(iData);
        }
        catch (final Exception e)
        {
            return defaultVal;
        }
    }

    /**
     * short 类型数据的处理
     *
     * @param sData
     *            需要被解析的字段
     * @param defaultVal
     *            解析异常时使用的默认值
     * @return 解析之后的字段
     */
    public static short parseInt(final String sData, final short defaultVal)
    {
        try
        {
            return Short.parseShort(sData);
        }
        catch (final Exception e)
        {
            return defaultVal;
        }
    }

    /**
     * double 类型数据的处理
     *
     * @param iData
     *            需要被解析的字段
     * @param defaultVal
     *            解析异常时使用的默认值
     * @return 解析之后的字段
     */
    public static double parseDouble(final String iData, final double defaultVal)
    {
        try
        {
            return Double.parseDouble(iData);
        }
        catch (final Exception e)
        {
            return defaultVal;
        }
    }
    
    /**
     * float 类型数据的处理
     *
     * @param iData
     *            需要被解析的字段
     * @param defaultVal
     *            解析异常时使用的默认值
     * @return 解析之后的字段
     */
    public static float parseFloat(final String iData, final float defaultVal)
    {
        try
        {
            return Float.parseFloat(iData);
        }
        catch (final Exception e)
        {
            return defaultVal;
        }
    }
}
