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
public class DataRequest4Apply extends DataRequest {

    /**   */
    private static final long serialVersionUID = -6627992392376251212L;

    private String status ;
    private String title;

    public String getStatus()
    {
        return status;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }
    public String getTitle()
    {
        return title;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }

}
