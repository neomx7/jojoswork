/**
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.util.service.model;

import com.jojo.util.pojo.BasePOJO;

/**
 * <summary>
 *
 * </summary>
 *
 * @author jojo
 *
 */
public class MenuMO extends BasePOJO
{
    /**   */
    private static final long serialVersionUID = -2841164734439213926L;

    private int level;
    private String dictCode;
    private int sortWay;
    private String parentId;
    private String iconURL;
    private String action;
    private String extraParams;

    public int getLevel()
    {
        return level;
    }
    public void setLevel(int level)
    {
        this.level = level;
    }
    public String getDictCode()
    {
        return dictCode;
    }
    public void setDictCode(String dictCode)
    {
        this.dictCode = dictCode;
    }
    public int getSortWay()
    {
        return sortWay;
    }
    public void setSortWay(int sortWay)
    {
        this.sortWay = sortWay;
    }
    public String getParentId()
    {
        return parentId;
    }
    public void setParentId(String parentId)
    {
        this.parentId = parentId;
    }
    public String getIconURL()
    {
        return iconURL;
    }
    public void setIconURL(String iconURL)
    {
        this.iconURL = iconURL;
    }
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("MenuMO [level=");
        builder.append(level);
        builder.append(", ");
        if (dictCode != null)
        {
            builder.append("dictCode=");
            builder.append(dictCode);
            builder.append(", ");
        }
        builder.append("sortWay=");
        builder.append(sortWay);
        builder.append(", ");
        if (parentId != null)
        {
            builder.append("parentId=");
            builder.append(parentId);
            builder.append(", ");
        }
        if (iconURL != null)
        {
            builder.append("iconURL=");
            builder.append(iconURL);
            builder.append(", ");
        }
        if (action != null)
        {
            builder.append("action=");
            builder.append(action);
            builder.append(", ");
        }
        if (extraParams != null)
        {
            builder.append("extraParams=");
            builder.append(extraParams);
            builder.append(", ");
        }
        if (super.toString() != null)
        {
            builder.append("toString()=");
            builder.append(super.toString());
        }
        builder.append("]");
        return builder.toString();
    }
    public String getAction()
    {
        return action;
    }
    public void setAction(String action)
    {
        this.action = action;
    }
    public String getExtraParams()
    {
        return extraParams;
    }
    public void setExtraParams(String extraParams)
    {
        this.extraParams = extraParams;
    }

}
