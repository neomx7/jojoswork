package com.jojo.service;

import java.util.List;

import com.jojo.service.model.MenuMO;

/**
 *
 * <summary>
 *
 * </summary>
 *
 * @author jojo
 *
 */
public interface MenuService
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
    public List<MenuMO> queryChildren(String parentId);


}
