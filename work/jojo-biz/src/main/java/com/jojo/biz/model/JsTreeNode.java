/**
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.biz.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * <summary>
 * []<br>
 * <br>
 * </summary>
 *
 * @author jojo
 *
 */
public class JsTreeNode implements Serializable
{

    /**   */
    private static final long serialVersionUID = -927828116850318767L;

    public JsTreeNode()
    {
        getState().put("opened", false);
        getState().put("disabled", false);
        getState().put("selected", false);
    }

    private String id = "";

    private String text = "";

    private String icon = "";

    private Map<String, Boolean> state = new HashMap<String, Boolean>();

//    private List<JsTreeNode> children = new ArrayList<JsTreeNode>(0);
    private Object children = true;

    private Map<String, Object> li_attr = new HashMap<String, Object>();
    private Map<String, Object> a_attr = new HashMap<String, Object>();

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public String getIcon()
    {
        return icon;
    }

    public void setIcon(String icon)
    {
        this.icon = icon;
    }

    public Map<String, Boolean> getState()
    {
        return state;
    }

    public void setState(Map<String, Boolean> state)
    {
        this.state = state;
    }



    public Map<String, Object> getLi_attr()
    {
        return li_attr;
    }

    public void setLi_attr(Map<String, Object> li_attr)
    {
        this.li_attr = li_attr;
    }

    public Map<String, Object> getA_attr()
    {
        return a_attr;
    }

    public void setA_attr(Map<String, Object> a_attr)
    {
        this.a_attr = a_attr;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("JsTreeNode [id=");
        builder.append(id);
        builder.append(", text=");
        builder.append(text);
        builder.append(", icon=");
        builder.append(icon);
        builder.append(", state=");
        builder.append(state);
        builder.append(", children=");
        builder.append(children);
        builder.append(", li_attr=");
        builder.append(li_attr);
        builder.append(", a_attr=");
        builder.append(a_attr);
        builder.append("]");
        return builder.toString();
    }

    public Object getChildren()
    {
        return children;
    }

    public void setChildren(Object children)
    {
        this.children = children;
    }
}
