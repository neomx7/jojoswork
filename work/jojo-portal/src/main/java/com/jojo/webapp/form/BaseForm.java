/**
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.webapp.form;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
public class BaseForm implements Serializable
{
    /**   */
    private static final long serialVersionUID = -5741199782580229224L;

    /**
     * 错误信息的key,页面使用
     */
    protected static final String RESULT_ERROR = "result_error";

    private String theId;
    private String theName;
    private String theRemark;
    private int status = 0;
    private String crtUserId;
    private String crtUserName;
    // 17位定长,精确到毫秒
    private String crtTime;
    private String updUserId;
    private String updUserName;
    // 17位定长,精确到毫秒
    private String updTime;

    private String key;
    private String category;
    private int version = 0;

    private int resultCode = 0;

    private String errorMsg;

    /**
     * 错误信息
     */
    private Map<String, String> errors = new LinkedHashMap<String, String>();
    /**
     * 提示消息概要
     */
    private String tip;
    /**
     * 提示消息详细
     */
    private String tipDesc;

    /**
     * 获取上下文信息
     *
     * @return
     */
    public AppContext getAppContext()
    {
        AppContext sc = AppContextHolder.get();
        return sc == null ? new AppContext() : sc;
    }

    public boolean hasLogined()
    {
        return getAppContext().hasLogined();
    }

    public String getContextUserName()
    {
        return getAppContext().getUserName();
    }

    public boolean hasAuthentication(String uri)
    {
        return AuthenticationUtil.hasAuthentication(uri, hasLogined(), getContextUserId());
    }

    // public boolean getAuthenticationUri(String uri) {
    // return AuthenticationUtil.hasAuthentication(uri, hasLogined(),
    // getContextUserId(),
    // getContextURIs());
    // }

    public String getContextIP()
    {
        return getAppContext().getIp();
    }

    public String getContextURI()
    {
        return getAppContext().getUri();
    }

    public List<String> getContextURIs()
    {
        return getAppContext().getUris();
    }

    public String getContextUserId()
    {
        return getAppContext().getUserId();
    }

    public boolean isAdministrator()
    {
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

    public int getStatus()
    {
        return status;
    }

    public void setStatus(int status)
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

    public String getTip()
    {
        return tip;
    }

    public void setTip(String tip)
    {
        this.tip = tip;
    }

    public String getTipDesc()
    {
        return tipDesc;
    }

    public void setTipDesc(String tipDesc)
    {
        this.tipDesc = tipDesc;
    }

    public int getResultCode()
    {
        return resultCode;
    }

    public void setResultCode(int resultCode)
    {
        this.resultCode = resultCode;
    }

    public Map<String, String> getErrors()
    {
        return errors;
    }

    public void setErrors(Map<String, String> errors)
    {
        this.errors = errors;
    }

    public String getErrorMsg()
    {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg)
    {
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("BaseForm [theId=");
        builder.append(theId);
        builder.append(", theName=");
        builder.append(theName);
        builder.append(", theRemark=");
        builder.append(theRemark);
        builder.append(", status=");
        builder.append(status);
        builder.append(", crtUserId=");
        builder.append(crtUserId);
        builder.append(", crtUserName=");
        builder.append(crtUserName);
        builder.append(", crtTime=");
        builder.append(crtTime);
        builder.append(", updUserId=");
        builder.append(updUserId);
        builder.append(", updUserName=");
        builder.append(updUserName);
        builder.append(", updTime=");
        builder.append(updTime);
        builder.append(", key=");
        builder.append(key);
        builder.append(", category=");
        builder.append(category);
        builder.append(", version=");
        builder.append(version);
        builder.append(", resultCode=");
        builder.append(resultCode);
        builder.append(", errorMsg=");
        builder.append(errorMsg);
        builder.append(", errors=");
        builder.append(errors);
        builder.append(", tip=");
        builder.append(tip);
        builder.append(", tipDesc=");
        builder.append(tipDesc);
        builder.append("]");
        return builder.toString();
    }

}
