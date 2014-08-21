package com.jojo.util.biz.bo;

import java.io.Serializable;

public class AutoCompleteVO implements Serializable
{

    /**   */
    private static final long serialVersionUID = -5470711166957566855L;

    private String id;
    private String label;
    private String value;

    public String getId()
    {
        return id;
    }
    public void setId(String id)
    {
        this.id = id;
    }
    public String getLabel()
    {
        return label;
    }
    public void setLabel(String label)
    {
        this.label = label;
    }
    public String getValue()
    {
        return value;
    }
    public void setValue(String value)
    {
        this.value = value;
    }
}
