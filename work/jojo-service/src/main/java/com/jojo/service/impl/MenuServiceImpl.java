/**
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.jojo.service.MenuService;
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
public class MenuServiceImpl implements MenuService
{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    /*
     * (non-Javadoc)
     *
     * @see com.jojo.service.MenuService#queryChildren(java.lang.String)
     */
    @Override
    public List<MenuMO> queryChildren(String parentId)
    {

        List<MenuMO> list = new ArrayList<MenuMO>();
        if (StringUtils.isBlank(parentId))
        {
            return list;
        }

        // TODO 先造内存数据
        if (parentId.trim().equals("001"))
        {
            MenuMO mo = new MenuMO();
            mo.setTheId("002");
            mo.setTheName("用户管理");
            mo.setLevel(2);
            list.add(mo);

            MenuMO mo2 = new MenuMO();
            mo2.setTheId("020");
            mo2.setTheName("部门管理");
            mo2.setLevel(2);
            list.add(mo2);

        }
        else  if (parentId.trim().equals("002") || parentId.trim().equals("020"))
        {
            MenuMO mo = new MenuMO();
            mo.setTheId("003");
            mo.setTheName("demo1");
            mo.setLevel(3);
            mo.setAction("demo/toList");
            list.add(mo);

            MenuMO mo2 = new MenuMO();
            mo2.setTheId("030");
            mo2.setTheName("demo2");
            mo2.setLevel(3);
            mo2.setAction("demo/toList");
            list.add(mo2);
        }



        return list;
    }

}
