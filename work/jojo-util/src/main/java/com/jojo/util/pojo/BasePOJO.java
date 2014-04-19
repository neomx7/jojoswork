/**
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.util.pojo;

import java.io.Serializable;

/**
 * <summary>
 *
 * </summary>
 *
 * @author jojo
 *
 */
public class BasePOJO implements Serializable
{
    /**   */
    private static final long serialVersionUID = 2726013478153609205L;

    private String theId;
    private String theName;
    private String theRemark;
    private String status;
    private String crtUserId;
    private String crtUserName;
    //17位定长,精确到毫秒
    private String crtTime;
    private String updUserId;
    private String updUserName;
  //17位定长,精确到毫秒
    private String updTime;

    public String getTheId()
    {
        return theId;
    }
    public void setTheId(String theId)
    {
        this.theId = theId;
    }
    public String getTheName()
    {
        return theName;
    }
    public void setTheName(String theName)
    {
        this.theName = theName;
    }
    public String getTheRemark()
    {
        return theRemark;
    }
    public void setTheRemark(String theRemark)
    {
        this.theRemark = theRemark;
    }
    public String getCrtUserId()
    {
        return crtUserId;
    }
    public void setCrtUserId(String crtUserId)
    {
        this.crtUserId = crtUserId;
    }
    public String getCrtUserName()
    {
        return crtUserName;
    }
    public void setCrtUserName(String crtUserName)
    {
        this.crtUserName = crtUserName;
    }
    public String getCrtTime()
    {
        return crtTime;
    }
    public void setCrtTime(String crtTime)
    {
        this.crtTime = crtTime;
    }
    public String getUpdUserId()
    {
        return updUserId;
    }
    public void setUpdUserId(String updUserId)
    {
        this.updUserId = updUserId;
    }
    public String getUpdUserName()
    {
        return updUserName;
    }
    public void setUpdUserName(String updUserName)
    {
        this.updUserName = updUserName;
    }
    public String getUpdTime()
    {
        return updTime;
    }
    public void setUpdTime(String updTime)
    {
        this.updTime = updTime;
    }
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("BasePOJO [");
        if (theId != null)
        {
            builder.append("theId=");
            builder.append(theId);
            builder.append(", ");
        }
        if (theName != null)
        {
            builder.append("theName=");
            builder.append(theName);
            builder.append(", ");
        }
        if (theRemark != null)
        {
            builder.append("theRemark=");
            builder.append(theRemark);
            builder.append(", ");
        }
        if (crtUserId != null)
        {
            builder.append("crtUserId=");
            builder.append(crtUserId);
            builder.append(", ");
        }
        if (crtUserName != null)
        {
            builder.append("crtUserName=");
            builder.append(crtUserName);
            builder.append(", ");
        }
        if (crtTime != null)
        {
            builder.append("crtTime=");
            builder.append(crtTime);
            builder.append(", ");
        }
        if (updUserId != null)
        {
            builder.append("updUserId=");
            builder.append(updUserId);
            builder.append(", ");
        }
        if (updUserName != null)
        {
            builder.append("updUserName=");
            builder.append(updUserName);
            builder.append(", ");
        }
        if (updTime != null)
        {
            builder.append("updTime=");
            builder.append(updTime);
        }
        builder.append("]");
        return builder.toString();
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
