/**
 *
 * JOJO
 */
package com.jojo.util.common;

/**
 *
 * @author JOJO
 */
public class ExceptionUtil {

    public static String getExceptionStackTrace(Throwable t) {
        if (t == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(t.toString());

        StackTraceElement[] traceElements = t.getStackTrace();
        for (StackTraceElement traceElement : traceElements) {
            sb.append("\r\n\t").append(" at ").append(traceElement.toString());
        }
        return sb.toString();
    }

    public static String getSimpleExceptionStackTrace(Throwable t) {
        StackTraceElement[] traceElements = null;
        if (t == null || t.getCause() == null
            || (traceElements = t.getCause().getStackTrace()) == null || traceElements.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("</br>").append(" Caused by: ").append(t.getCause().toString());
        sb.append("</br>").append(" at ").append(traceElements[0].toString());

        return sb.toString();
    }
}
