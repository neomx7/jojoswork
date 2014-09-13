/**
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.util.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.beanutils.Converter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <summary>
 * [扩展apache的util包，使之支持日期格式]<br>
 * <br>
 * </summary>
 *
 * @author jojo
 *
 */
public class ExtDateConvert implements Converter
{
    private static final Logger logger = LoggerFactory.getLogger(ExtDateConvert.class);
    private static String dateFormatStr = "yyyy/MM/dd";
    private static SimpleDateFormat dateTimeFormat = new SimpleDateFormat(dateFormatStr);

    private static String dateLongFormatStr = dateFormatStr + " HH:mm:ss";
    private static SimpleDateFormat dateTimeLongFormat = new SimpleDateFormat(dateLongFormatStr);

    public Object convert(Class arg0, Object arg1)
    {
        if (arg1 == null)
        {
            return null;
        }
//        System.out.println(arg1.getClass().getName() + "=" + arg1.toString());
        String className = arg1.getClass().getName();
        // java.sql.Timestamp
        if ("java.sql.Timestamp".equalsIgnoreCase(className))
        {
            try
            {
                SimpleDateFormat df = new SimpleDateFormat(dateFormatStr + " HH:mm:ss");
                return df.parse(dateTimeLongFormat.format(arg1));
            }
            catch (Exception e)
            {
                try
                {
                    SimpleDateFormat df = new SimpleDateFormat(dateFormatStr);
                    return df.parse(dateTimeFormat.format(arg1));
                }
                catch (ParseException ex)
                {
                    logger.error(e.getMessage(), e);
                    return null;
                }
            }
        }
        else
        {// java.util.Date,java.sql.Date
            String p = (String) arg1;
            if (p == null || p.trim().length() == 0)
            {
                return null;
            }
            try
            {
                SimpleDateFormat df = new SimpleDateFormat(dateFormatStr + " HH:mm:ss");
                return df.parse(p.trim());
            }
            catch (Exception e)
            {
                try
                {
                    SimpleDateFormat df = new SimpleDateFormat(dateFormatStr);
                    return df.parse(p.trim());
                }
                catch (ParseException ex)
                {
                    logger.error(e.getMessage(), e);
                    return null;
                }
            }
        }
    }

    public static String formatDateTime(Object obj)
    {
        if (obj != null)
            return dateTimeFormat.format(obj);
        else
            return "";
    }

    public static String formatLongDateTime(Object obj)
    {
        if (obj != null)
            return dateTimeLongFormat.format(obj);
        else
            return "";
    }

}
