/**
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.util.common;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * <summary>
 * []<br>
 * <br>
 * </summary>
 *
 * @author jojo
 *
 */
public class MD5Encrypt
{
    private static final String MD5_PREFIX = "http://jojo.com";

    private static final ThreadLocal<MD5Encrypt> local = new ThreadLocal<MD5Encrypt>();

    private MD5Encrypt() {
        super();
    }

    public static MD5Encrypt getEncrypt() {
        MD5Encrypt encrypt = local.get();
        if (encrypt == null) {
            encrypt = new MD5Encrypt();
            local.set(encrypt);
        }
        return encrypt;
    }

    /**
     *
     * <summary>
     * [加密字符串]<br>
     * <br>
     * </summary>
     *
     * @author jojo
     *
     * @param s
     * @return
     */
    public static String encode(String s) {
        if (s == null) {
            return null;
        }
        return DigestUtils.md5Hex(MD5_PREFIX + s);
    }

    public static void main(String[] args){
        String passwd =  "123";
        System.out.println(passwd + " 加密后为： " + encode(passwd)) ;
        System.out.println(passwd + " 加密后为： " + encode(passwd)) ;
    }
}
