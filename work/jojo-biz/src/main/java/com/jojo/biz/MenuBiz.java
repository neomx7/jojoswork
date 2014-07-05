/**
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.biz;

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
public interface MenuBiz
{
    /**
    *
    * <summary>
    * <p>根据父级 id 得到下一层的子类菜单列表</p>
    * </summary>
    *
    * @author jojo
    *
    * @param parentId
    * @return
    */
   public List<MenuBO> queryChildren(String parentId);
}
