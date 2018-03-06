package com.bitauto.bdc.common.utils;


import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * java集合对象类型数据检查类定义
 *
 * @author liuming1
 * @version 2014年11月18日
 * @see DataCheck
 * @since
 */
public final class DataCheck
{
	/**
	 * 默认的私有构造函数
	 */
	private DataCheck()
	{

	}

	/**
	 * 检查map数据是否合法
	 *
	 * @author liuming1
	 * @param map
	 *           map集合
	 * @return map的合法性
	 */
	public static boolean checkMap(final Map<?, ?> map)
	{
		return (null != map) && (!map.isEmpty());
	}

	/**
	 * 检查list数据是否合法
	 *
	 * @author liuming1
	 * @param list
	 *           list集合
	 * @return list的合法性
	 */
	public static boolean checkList(final List<?> list)
	{
		return (null != list) && (!list.isEmpty());
	}

	/**
	 * 检查set数据是否合法
	 *
	 * @author liuming1
	 * @param set
	 *           set集合
	 * @return set的合法性
	 */
	public static boolean checkSet(final Set<?> set)
	{
		return (null != set) && (!set.isEmpty());
	}

	/**
	 * 检查字符串数据是否合法
	 *
	 * @author liuming1
	 * @param s
	 *           字符串
	 * @return s的合法性
	 */
	public static boolean checkString(final String s)
	{
		return (null != s) && (!s.isEmpty());
	}

	/**
	 * 检查字符串数据是否合法
	 *
	 * @param <T>
	 *           泛型定义
	 * @author liuming1
	 * @param ts
	 *           数组
	 * @return s的合法性
	 */
	public static <T> boolean checkArray(final T[] ts)
	{
		return (null != ts) && (0 < ts.length);
	}
}
