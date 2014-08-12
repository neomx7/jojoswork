/**
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.webapp.form;

import java.util.List;

import com.jojo.web.common.authenticate.AuthenticationUtil;
import com.jojo.web.common.context.AppContext;
import com.jojo.web.common.context.AppContextHolder;

/**
 * <summary>
 *
 * </summary>
 *
 * @author jojo
 *
 */
public class BaseForm
{

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

    private String key;
    private String category;
    private int version = 0;


    /**
     * 获取上下文信息
     *
     * @return
     */
    public AppContext getAppContext() {
        AppContext sc = AppContextHolder.get();
        return sc == null ? new AppContext() : sc;
    }

    public boolean hasLogined() {
        return getAppContext().hasLogined();
    }

    public String getContextUserName() {
        return getAppContext().getUserName();
    }

    public boolean hasAuthentication(String uri) {
        return AuthenticationUtil.hasAuthentication(uri, hasLogined(), getContextUserId());
    }

//    public boolean getAuthenticationUri(String uri) {
//        return AuthenticationUtil.hasAuthentication(uri, hasLogined(), getContextUserId(),
//            getContextURIs());
//    }

    public String getContextIP() {
        return getAppContext().getIp();
    }

    public String getContextURI() {
        return getAppContext().getUri();
    }

    public List<String> getContextURIs() {
        return getAppContext().getUris();
    }

    public String getContextUserId() {
        return getAppContext().getUserId();
    }

    public boolean isAdministrator() {
        return AuthenticationUtil.USER_ID_ADMINISTRATOR.equals(getContextUserId());
    }


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
    public String getStatus()
    {
        return status;
    }
    public void setStatus(String status)
    {
        this.status = status;
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
    public String getKey()
    {
        return key;
    }
    public void setKey(String key)
    {
        this.key = key;
    }
    public String getCategory()
    {
        return category;
    }
    public void setCategory(String category)
    {
        this.category = category;
    }
    public int getVersion()
    {
        return version;
    }
    public void setVersion(int version)
    {
        this.version = version;
    }
}
