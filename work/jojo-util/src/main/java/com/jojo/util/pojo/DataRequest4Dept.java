/**
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.util.pojo;

/**
 * <summary>
 * 部门信息查询
 * </summary>
 *
 * @author jojo
 *
 */
public class DataRequest4Dept extends DataRequest {

    /**   */
    private static final long serialVersionUID = -6627992392376251212L;

    private String parentCode ;
    private int subCodeLength = 2;
    public String getParentCode()
    {
        return parentCode;
    }
    public void setParentCode(String parentCode)
    {
        this.parentCode = parentCode;
    }
    public int getSubCodeLength()
    {
        return subCodeLength;
    }
    public void setSubCodeLength(int subCodeLength)
    {
        this.subCodeLength = subCodeLength;
    }


}
