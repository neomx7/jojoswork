/**
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.web.common.context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jojo.biz.SysMgrBiz;
import com.jojo.dal.common.postgre.domain.OrgUserDO;
import com.jojo.dal.common.postgre.domain.RoleDO;
import com.jojo.dal.common.postgre.domain.UserDO;
import com.jojo.dal.common.postgre.domain.UserResourcesDO;

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
            ContextHolder.getUsr2ResourceMap().put(userResourcesDO.getTheUsrId(), userResourcesDO.getResourceDOs());
        }

    }
}
