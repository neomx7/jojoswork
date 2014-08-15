package com.jojo.facade.service;

import java.util.List;

import com.jojo.util.service.model.MenuMO;

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
     * <p>
     * 根据父级 id 得到下一层的子类菜单列表
     * </p>
     * </summary>
     *
     * @author jojo
     *
     * @param parentId 父级菜单ID
     * @param parentCode 父级菜单编号
     * @return
     */
    public List<MenuMO> queryChildren(String parentId, String parentCode);

}
