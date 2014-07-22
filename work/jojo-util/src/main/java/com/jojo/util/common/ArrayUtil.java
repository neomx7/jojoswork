/**
 *
 *JOJO
 * 
 */
package com.jojo.util.common;

import org.apache.commons.lang.StringUtils;

import com.jojo.util.constants.JOJOConstants;

/**
 *
 * @author finley.yao
 * @version $Id: ArrayUtil.java, v 0.1 2013-7-11 下午12:30:56 finley.yao Exp $
 */
public class ArrayUtil {
    public static String array2String(String[] array) {
        return array2String(array, JOJOConstants.COMMA);
    }

    public static String array2String(String[] array, String combine) {
        if (array == null) {
            return StringUtils.EMPTY;
        }
        StringBuilder sb = new StringBuilder();
        for (String arraySplit : array) {
            sb.append(combine);
            sb.append(arraySplit);
        }
        return sb.length() > 0 ? sb.substring(JOJOConstants.NUM_ONE) : StringUtils.EMPTY;
    }

}
