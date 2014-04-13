/**
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.biz.model;

import java.io.Serializable;

/**
 * <summary>
 * 
 * </summary>
 * 
 * @author jojo
 * 
 */
public class BaseModel implements Serializable
{

    /**   */
    private static final long serialVersionUID = 5477205619023388101L;

    
    private String id;
    private String status;
    
    public String getId()
    {
        return id;
    }
    public void setId(String id)
    {
        this.id = id;
    }
    public String getStatus()
    {
        return status;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }
}
