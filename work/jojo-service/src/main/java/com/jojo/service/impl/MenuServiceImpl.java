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

import com.jojo.facade.service.MenuService;
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
        //管理
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

            MenuMO mo90 = new MenuMO();
            mo90.setTheId("090");
            mo90.setTheName("已部署的流程定义");
            mo90.setLevel(3);
            mo90.setAction("workflow/toList");
            list.add(mo90);
        }
        //流程
        else  if (parentId.trim().equals("003"))
        {
            MenuMO mo = new MenuMO();
            mo.setTheId("0030");
            mo.setTheName("发起物料申请");
            mo.setLevel(2);
            mo.setAction("demo/toCreateApplyList");
            list.add(mo);

            MenuMO mo2 = new MenuMO();
            mo2.setTheId("0031");
            mo2.setTheName("需要我处理的任务");
            mo2.setLevel(2);
            mo2.setAction("demo/toMyTaskList");
            list.add(mo2);

        }
        //发起物料申请
        else  if (parentId.trim().equals("0030"))
        {
            MenuMO mo = new MenuMO();
            mo.setTheId("003030");
            mo.setTheName("发起物料申请");
            mo.setLevel(3);
            mo.setAction("demo/toCreateApplyList");
            list.add(mo);

        }
        //需要我处理的任务
        else  if (parentId.trim().equals("0031"))
        {
            MenuMO mo = new MenuMO();
            mo.setTheId("003131");
            mo.setTheName("需要我处理的任务");
            mo.setLevel(3);
            mo.setAction("demo/toMyTaskList");
            list.add(mo);

        }

        return list;
    }

}
