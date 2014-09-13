/**
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.util.common;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <summary>
 * [扩展apache的util包，使之支持日期格式的转换，java.sql.Timestamp和java.util.Date]<br>
 * <br>
 * </summary>
 *
 * @author jojo
 *
 */
public class ExtBeanUtils extends BeanUtils
{
    private static final Logger logger = LoggerFactory.getLogger(ExtBeanUtils.class);
    static
    {
        ConvertUtils.register(new ExtDateConvert(), java.util.Date.class);
        ConvertUtils.register(new ExtDateConvert(), java.sql.Date.class);
        ConvertUtils.register(new ExtDateConvert(), java.sql.Timestamp.class);
    }

    public static void copyProperties(Object dest, Object orig)
    {
        try
        {
            BeanUtils.copyProperties(dest, orig);
        }
        catch (IllegalAccessException ex)
        {
            logger.error(ex.getMessage(), ex);
        }
        catch (InvocationTargetException ex)
        {
            logger.error(ex.getMessage(), ex);
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(), ex);
        }
    }
}
