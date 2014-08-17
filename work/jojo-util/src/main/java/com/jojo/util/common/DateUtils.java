/**
 *
 *JOJO
 *
 */
package com.jojo.util.common;

import java.util.Date;

import org.apache.commons.lang.time.FastDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日期处理公共类
 *
 * @author finley.yao
 * @version $Id: DateUtils.java, v 0.1 2013-6-13 下午3:07:54 finley.yao Exp $
 */
public class DateUtils {

    protected static final Logger logger                     = LoggerFactory
                                                               .getLogger(DateUtils.class);

    public static final String  DATE_TIME_PATTERN_NUM_DATE = "yyyyMMdd";

    public static final String  DATE_TIME_PATTERN_NUM_SEC  = "yyyyMMddHHmmss";

    public static final String  DATE_TIME_PATTERN_NUM_MS   = "yyyyMMddHHmmssSSS";

    public static final String  DATE_TIME_PATTERN_STR_DATE = "yyyy-MM-dd";

    public static final String  DATE_TIME_PATTERN_STR_SEC  = "yyyy-MM-dd HH:mm:ss";

    public static final String  DATE_TIME_PATTERN_STR_MS   = "yyyy-MM-dd HH:mm:ss SSS";


    public static final FastDateFormat fastDateFormat4Day = FastDateFormat.getInstance(DATE_TIME_PATTERN_NUM_DATE);

    public static final FastDateFormat fastDateFormat4MillSeconds = FastDateFormat.getInstance(DATE_TIME_PATTERN_NUM_MS);

    public static String getCurrentDateTimeMs()
    {
        return fastDateFormat4MillSeconds.format(new Date());
    }


}
