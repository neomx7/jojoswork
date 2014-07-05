/**
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package  com.jojo.util;

import java.util.Date;

import org.apache.commons.lang.time.FastDateFormat;

/**
 * <summary>
 *
 * </summary>
 *
 * @author jojo
 *
 */
public class CommonUtils
{
    public static final String version = "0.0.1";

    private static final String FORMAT_DATE2MS = "yyyyMMddHHmmssSSS";

    //根据输入日期，返回精确到毫秒的17位定长时间戳,格式如 20140506070809321

    public static String getDateFormat2Ms(Date date)
    {
       return  FastDateFormat.getInstance(FORMAT_DATE2MS).format(date);
    }
}
