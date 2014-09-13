/**
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.biz.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jojo.biz.MenuBiz;
import com.jojo.service.MenuService;
import com.jojo.util.biz.bo.MenuBO;
import com.jojo.util.common.ExtBeanUtils;
import com.jojo.util.service.model.MenuMO;

/**
 * <summary>
 *
 * </summary>
 *
 * @author jojo
 *
 */
@Repository
public class MenuBizImpl implements MenuBiz
{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MenuService menuService;

    /*
     * (non-Javadoc)
     *
     * @see com.jojo.biz.MenuBiz#queryChildren(java.lang.String)
     */
    @Override
    public List<MenuBO> queryChildren(String parentId, String parentCode)
    {
        List<MenuMO> mos = menuService.queryChildren(parentId, parentCode);
        List<MenuBO> list = new ArrayList<MenuBO>(mos.size());
        for (MenuMO mo : mos)
        {
            MenuBO bo = new MenuBO();
            ExtBeanUtils.copyProperties(bo, mo);
            list.add(bo);
        }
        return list;
    }

}
