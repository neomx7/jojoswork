/**
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.webapp.form;

/**
 * <summary>
 * 登入登出的表单
 * </summary>
 *
 * @author jojo
 *
 */
public class LogInOutForm extends BaseForm
{
    private String userId;

    private String userName;

    private String pwd;

    private String redirectURL;

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getPwd()
    {
        return pwd;
    }

    public void setPwd(String pwd)
    {
        this.pwd = pwd;
    }

    public String getRedirectURL()
    {
        return redirectURL;
    }

    public void setRedirectURL(String redirectURL)
    {
        this.redirectURL = redirectURL;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("LogInOutForm [userId=");
        builder.append(userId);
        builder.append(", userName=");
        builder.append(userName);
        builder.append(", pwd=");
        builder.append(pwd);
        builder.append(", redirectURL=");
        builder.append(redirectURL);
        builder.append("]");
        return builder.toString();
    }

}
