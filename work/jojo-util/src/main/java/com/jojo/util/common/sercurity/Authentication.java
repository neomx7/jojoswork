/**
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.util.common.sercurity;

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
public class Authentication
{
    public static String encodeMD5Hex(String data)
    {
        return DigestUtils.md5Hex(data);
    }

    public static void main(String[] args)
    {
        System.out.println(Authentication.encodeMD5Hex("123"));
    }
}
