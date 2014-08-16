/**
 * JOJO
 * 
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.web.common.context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jojo.biz.SysMgrBiz;
import com.jojo.dal.common.postgre.domain.OrgUserDO;
import com.jojo.dal.common.postgre.domain.ResourceDO;
import com.jojo.dal.common.postgre.domain.RoleDO;
import com.jojo.dal.common.postgre.domain.UserDO;
import com.jojo.dal.common.postgre.domain.UserResourcesDO;
import com.jojo.util.biz.bo.MenuBO;
import com.jojo.util.service.model.MenuMO;

/**
 * <summary>
 * [系统维护的实现类，初始化系统的用户、角色、权限、菜单等数据到内存中，作为缓存使用]<br>
 * [注意]<br>
 * 只有超级管理员可以进行用户的维护和菜单及权限分配<br>
 * 角色菜单权限等修改后，要在内存中的map对象也跟着修改，相当于内存map作为缓存使用<br>
 * 对于有些部门用户是全部门通用的情况，此时可以考虑加入一个特别的'通用部门'的部门概念<br>
 * <br>
 * </summary>
 * 
 * @author jojo
 * 
 */
@Repository
public class SystemMgrCtxHolder
{
    @Autowired
    private SysMgrBiz sysMgrBiz;

    /**
     * 
     * <summary>
     * [得到登录用户某级菜单的下一级子菜单]<br>
     * <br>
     * </summary>
     * 
     * @author jojo
     * 
     * @param parentId
     * @param parentCode
     * @return
     */
    public List<MenuBO> getSubMenus4NextLv(String parentId, String parentCode, String loginUserName)
    {
        // AppContextHolder.get().getUserName()
        List<ResourceDO> resourceDOs = ContextHolder.getUsr2ResourceMap().get(loginUserName);
        List<MenuBO> subMenus = new ArrayList<MenuBO>(10);
        // String menuId = StringUtils.isBlank(parentId) ? "": parentId;
        String menuCode = StringUtils.isBlank(parentCode) ? "" : parentCode;

        if (resourceDOs != null)
        {

            for (ResourceDO resourceDO : resourceDOs)
            {
                // if (StringUtils.isBlank(resourceDO.getCode()) ||
                // StringUtils.isBlank(resourceDO.getParentId()))
                // {
                // continue;
                // }
                // 去掉当前菜单id
                // if (resourceDO.getTheId().equals(parentId))
                // {
                // }
                // 只保留下一级的菜单列表，默认的菜单编号是01-99个，实在不够可以考虑使用Aa-Zz
                if (resourceDO.getCode().length() == (menuCode.length() + 2)
                        && resourceDO.getCode().startsWith(menuCode))
                {
                    MenuBO menuBO = new MenuBO();
                    menuBO.setAction(resourceDO.getUri());
                    menuBO.setDictCode(resourceDO.getCode());
                    menuBO.setLevel(resourceDO.getLevel());
                    menuBO.setParentId(parentId);
                    // menuMO.setSortWay(resourceDO.get);
                    menuBO.setStatus(resourceDO.getStatus());
                    menuBO.setTheId(resourceDO.getTheId());
                    menuBO.setTheName(resourceDO.getTheName());
                    menuBO.setTheRemark(resourceDO.getTheRemark());
                    subMenus.add(menuBO);
                }
            }

        }
        return subMenus;
    }

    /**
     * 
     * <summary>
     * [初始化系统管理所需要的缓存数据，包括]<br>
     * ★.用户id：用户对象<br>
     * ★.部门用户id : List<RoleDO> roles <br>
     * ★.roleId: List<PrivilegeDO> prvs <br>
     * ★.用户id:List<ResourceDO> resources <br>
     * <br>
     * </summary>
     * 
     * @author jojo
     */
    public void init()
    {
        Map<String, Object> params4User = new HashMap<String, Object>(10);
        List<UserDO> userDOs = sysMgrBiz.queryUsers(params4User);

        for (UserDO userDO : userDOs)
        {

            ContextHolder.getUserMap().put(userDO.getUsrId(), userDO);
        }

        Map<String, Object> params4OrgUser = new HashMap<String, Object>(10);
        List<OrgUserDO> orgUserDOs = sysMgrBiz.queryOrgUsers(params4OrgUser);
        for (OrgUserDO orgUserDO : orgUserDOs)
        {
            ContextHolder.getUsr2RoleMap().put(orgUserDO.getUsrId(), orgUserDO.getRoleDOs());
        }

        Map<String, Object> parmas4Role = new HashMap<String, Object>(10);
        List<RoleDO> roleDOs = sysMgrBiz.queryRoles(parmas4Role);
        for (RoleDO roleDO : roleDOs)
        {
            ContextHolder.getRole2PrvMap().put(roleDO.getTheId(), roleDO.getPrivilegeDOs());
        }

        Map<String, Object> params4UsrResources = new HashMap<String, Object>(10);
        List<UserResourcesDO> userResourcesDOs = sysMgrBiz.queryUsrResources(params4UsrResources);
        for (UserResourcesDO userResourcesDO : userResourcesDOs)
        {
            ContextHolder.getUsr2ResourceMap().put(userResourcesDO.getUsrId(), userResourcesDO.getResourceDOs());
        }

    }
}
