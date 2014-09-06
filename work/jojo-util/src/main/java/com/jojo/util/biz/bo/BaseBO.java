/**
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.util.biz.bo;

import com.jojo.util.pojo.BasePOJO;

/**
 * <summary>
 *
 * </summary>
 *
 * @author jojo
 *
 */
public class BaseBO extends BasePOJO
{

    /**   */
    private static final long serialVersionUID = 5477205619023388101L;


    private String id;

    /**
     * 下一个用户的登录名
     */
    private String nextUser;


    private int number;

    public String getId()
    {
        return id;
    }
    public void setId(String id)
    {
        this.id = id;
    }
    public int getNumber()
    {
        return number;
    }
    public void setNumber(int number)
    {
        this.number = number;
    }
    public String getNextUser()
    {
        return nextUser;
    }
    public void setNextUser(String nextUser)
    {
        this.nextUser = nextUser;
    }
}
