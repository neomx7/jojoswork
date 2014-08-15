/**
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.webapp.form;

import java.util.ArrayList;
import java.util.List;

import com.jojo.util.biz.bo.MenuBO;

/**
 * <summary>
 *
 * </summary>
 *
 * @author jojo
 *
 */
public class IndexForm extends BaseForm
{
    List<MenuBO> menus = new ArrayList<MenuBO>();

    private String menuId;

    private String menuCode;

    public List<MenuBO> getMenus()
    {
        return menus;
    }

    public void setMenus(List<MenuBO> menus)
    {
        this.menus = menus;
    }

    public String getMenuId()
    {
        return menuId;
    }

    public void setMenuId(String menuId)
    {
        this.menuId = menuId;
    }

    public String getMenuCode()
    {
        return menuCode;
    }

    public void setMenuCode(String menuCode)
    {
        this.menuCode = menuCode;
    }


}
