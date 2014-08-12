/**
 *
 * JOJO
 * Copyright (c) 2014-  .All Rights Reserved.
 */
package com.jojo.web.common.context;

import java.util.List;

/**
 *
 * @author jojo
 */
public class AppContext {

    private String       userId;

    private String       logUserName;

    /**
     * 用户角色，逗号分隔的字符串,冗余
     */
    private String userRoles;

    private String       userName;

    private String       userStatus;

    private boolean      logon;

    private String       ip;

    private String       uri;

    private List<String> uris;

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getLogUserName()
    {
        return logUserName;
    }

    public void setLogUserName(String logUserName)
    {
        this.logUserName = logUserName;
    }

    public String getUserRoles()
    {
        return userRoles;
    }

    public void setUserRoles(String userRoles)
    {
        this.userRoles = userRoles;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getUserStatus()
    {
        return userStatus;
    }

    public void setUserStatus(String userStatus)
    {
        this.userStatus = userStatus;
    }

    public boolean hasLogined()
    {
        return logon;
    }

    public void setLogon(boolean logon)
    {
        this.logon = logon;
    }

    public String getIp()
    {
        return ip;
    }

    public void setIp(String ip)
    {
        this.ip = ip;
    }

    public String getUri()
    {
        return uri;
    }

    public void setUri(String uri)
    {
        this.uri = uri;
    }

    public List<String> getUris()
    {
        return uris;
    }

    public void setUris(List<String> uris)
    {
        this.uris = uris;
    }


}
