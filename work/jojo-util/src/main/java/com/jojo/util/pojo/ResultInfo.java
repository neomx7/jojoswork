/**
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.util.pojo;

/**
 * <summary>
 *
 * </summary>
 *
 * @author jojo
 *
 */
public class ResultInfo extends BasePOJO
{

    /**   */
    private static final long serialVersionUID = 2027023019344847391L;

    private int resultCode = -1;
    private String resultDesc;

    public int getResultCode()
    {
        return resultCode;
    }

    public void setResultCode(int resultCode)
    {
        this.resultCode = resultCode;
    }

    public String getResultDesc()
    {
        return resultDesc;
    }

    public void setResultDesc(String resultDesc)
    {
        this.resultDesc = resultDesc;
    }
}
