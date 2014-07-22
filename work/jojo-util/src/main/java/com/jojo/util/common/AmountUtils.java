/**
 *
 *JOJO
 * 
 */
package com.jojo.util.common;

import java.text.DecimalFormat;

import org.apache.log4j.Logger;

/**
 *
 * @author finley.yao
 * @version $Id: AmountUtils.java, v 0.1 2013-5-2 下午1:04:42 finley.yao Exp $
 */
public class AmountUtils {

    private static final Logger logger = Logger.getLogger(AmountUtils.class);

    /**
    * 将以分为单位的金额字符串转换为以元为单位的金额字符串
    * 0.00 <- 0
    * 0.01 <- 1
    * 532.3 <- 53230
    * "" -> 0.00
    * null -> 0.00
    * @param s 以分为单位的金额字符串
    * @return 以元为单位的金额字符串
    */
    public static String amtAddPoint(String amt) {
        if (amt == null || "".equals(amt.trim())) {
            return "0.00";
        }

        try {
            DecimalFormat format = new DecimalFormat("0.00");
            return format.format((Double.parseDouble(amt) / 100));
        } catch (Exception e) {
            logger.error("金额转换出错[" + amt + "]" + e.getMessage());
            return "0.00";
        }
    }

}
