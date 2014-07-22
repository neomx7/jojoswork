/**
 *
 *JOJO
 * Copyright (c) 2006-2014 ChinaPnR,Inc.All Rights Reserved.
 */
package com.jojo.util.common;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author elvis.xu
 * @version $Id: FileUtil.java, v 0.1 2014-1-27 下午3:22:55 elvis.xu Exp $
 */
public abstract class FileUtil {


    /**
     * 复制流
     * 【注:】本方法不关闭任何流
     * @param in
     * @param out
     * @throws IOException
     */
    public static void copyStream(InputStream in, OutputStream out) throws IOException {
        byte[] buf = new byte[1024 * 2];
        int count = 0;
        while ((count = in.read(buf)) != -1) {
            if (count > 0) {
                out.write(buf, 0, count);
            }
        }
    }

    /**
     * 生成一个全局唯一的key
     * @return
     */
    public static String generateUniqueKey() {
        return DateUtils.getCurrentDateTimeMs();
    }

    /**
     * 处理传入的filename返回一个全局唯一的filename
     * @param filename
     * @return
     */
    public static String uniqueFilename(String filename) {
        String key = FileUtil.generateUniqueKey();
        if (filename == null) {
            return key;
        }
        int index = filename.lastIndexOf('.');
        String suffix = "";
        if (index != -1) {
            suffix = filename.substring(index);
            filename = filename.substring(0, filename.length() - suffix.length());
        }
        return filename + "_" + key + suffix;
    }
}
