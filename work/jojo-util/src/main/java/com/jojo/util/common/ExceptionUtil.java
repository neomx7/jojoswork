/**
 *
 * JOJO
 */
package com.jojo.util.common;

/**
 *
 * @author finley.yao
 * @version $Id: ExceptionUtil.java, v 0.1 2013-8-5 上午11:06:07 finley.yao Exp $
 */
public class ExceptionUtil {

    public static String getExceptionStackTrace(Exception e) {
        if (e == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(e.toString());

        StackTraceElement[] traceElements = e.getStackTrace();
        for (StackTraceElement traceElement : traceElements) {
            sb.append("\r\n\t").append(" at ").append(traceElement.toString());
        }
        return sb.toString();
    }

    public static String getSimpleExceptionStackTrace(Exception e) {
        StackTraceElement[] traceElements = null;
        if (e == null || e.getCause() == null
            || (traceElements = e.getCause().getStackTrace()) == null || traceElements.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("</br>").append(" Caused by: ").append(e.getCause().toString());
        sb.append("</br>").append(" at ").append(traceElements[0].toString());

        return sb.toString();
    }
}
