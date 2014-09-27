/**
 *
 * JOJO
 *
 */
package com.jojo.util.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.FastDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日期处理公共类
 *
 * @author finley.yao
 * @version $Id: DateUtils.java, v 0.1 2013-6-13 下午3:07:54 finley.yao Exp $
 */
public class DateUtils
{

    protected static final Logger logger = LoggerFactory.getLogger(DateUtils.class);

    public static final String DATE_TIME_PATTERN_NUM_DATE = "yyyyMMdd";

    public static final String DATE_TIME_PATTERN_NUM_SEC = "yyyyMMddHHmmss";

    public static final String DATE_TIME_PATTERN_NUM_MS = "yyyyMMddHHmmssSSS";

    public static final String DATE_TIME_PATTERN_STR_DATE = "yyyy-MM-dd";

    public static final String DATE_TIME_PATTERN_STR_SEC = "yyyy-MM-dd HH:mm:ss";

    public static final String DATE_TIME_PATTERN_STR_MS = "yyyy-MM-dd HH:mm:ss SSS";

    public static final FastDateFormat fastDateFormat4Day = FastDateFormat.getInstance(DATE_TIME_PATTERN_NUM_DATE);

    public static final FastDateFormat fastDateFormat4MillSeconds = FastDateFormat
            .getInstance(DATE_TIME_PATTERN_NUM_MS);

    public static final FastDateFormat fastDateFormat4Seconds = FastDateFormat.getInstance(DATE_TIME_PATTERN_STR_SEC);

    public static String getCurrentDateTimeMs()
    {
        return fastDateFormat4MillSeconds.format(new Date());
    }


    public static String getDateTimeSec(long msTimeLong)
    {
        if (msTimeLong > 0)
        {
            return fastDateFormat4Seconds.format(new Date(msTimeLong));
        }
        return null;
    }

    public static String getDateTimeMs(Date date)
    {
        if (date != null)
        {
            return fastDateFormat4MillSeconds.format(date);
        }
        return null;
    }

    public static String parseDateTime2Ms(Date date)
    {
        if (date != null)
        {
            return fastDateFormat4MillSeconds.format(date);
        }
        return null;
    }

    public static String parseDateTimeMs2Sec(String orgiTimeMs) throws ParseException
    {
        if (StringUtils.isBlank(orgiTimeMs))
        {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_TIME_PATTERN_NUM_MS);
        return fastDateFormat4Seconds.format(dateFormat.parse(orgiTimeMs));
    }

    public static String parseDateTimeMsPostgre2Sec(String orgiTimePostgreMs)
    {
        if (StringUtils.isBlank(orgiTimePostgreMs))
        {
            return null;
        }
        return StringUtils.split(orgiTimePostgreMs, ".")[0];
    }

}