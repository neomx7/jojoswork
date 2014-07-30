package com.jojo.dal.common.postgre.domain;

import com.jojo.util.pojo.BasePOJO;

/**
 * <summary>
 * [权限]<br>
 * <br>
 * </summary>
 *
 * @author jojo
 *
 */
public class ResourceDO extends BasePOJO
{

    /**   */
    private static final long serialVersionUID = 8066901364825551897L;

    private int type;
    private String uri;
    private int level;
    private String code;
    private String parentId;
    public int getType()
    {
        return type;
    }
    public void setType(int type)
    {
        this.type = type;
    }
    public String getUri()
    {
        return uri;
    }
    public void setUri(String uri)
    {
        this.uri = uri;
    }
    public int getLevel()
    {
        return level;
    }
    public void setLevel(int level)
    {
        this.level = level;
    }
    public String getCode()
    {
        return code;
    }
    public void setCode(String code)
    {
        this.code = code;
    }
    public String getParentId()
    {
        return parentId;
    }
    public void setParentId(String parentId)
    {
        this.parentId = parentId;
    }



}
