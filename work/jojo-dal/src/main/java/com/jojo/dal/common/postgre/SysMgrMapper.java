/**
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.dal.common.postgre;

import java.util.List;
import java.util.Map;

import com.jojo.dal.common.postgre.domain.OrgUserDO;
import com.jojo.dal.common.postgre.domain.RoleDO;
import com.jojo.dal.common.postgre.domain.UserDO;

/**
 * <summary>
 * [系统启动时，首先生成好3个map对象：]<br>
 * 1 用户id : List<部门用户> 部门用户list <br>
 * 2.部门用户id : List<Role> roles <br>
 * 3.roleId: List<Privalige> prvs <br>
 *
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
public interface SysMgrMapper
{

    /**
     *
     * <summary>
     * [获取用户list,每个用户联立得到部门用户list]<br>
     * <br>
     * </summary>
     *
     * @author jojo
     *
     * @return
     */
    public List<UserDO> queryUsers(Map<String, Object> params);

    /**
    *
    * <summary>
    * [获取部门用户list,每个部门用户联立得到权限对象的list]<br>
    * <br>
    * </summary>
    *
    * @author jojo
    *
    * @return
    */
   public List<OrgUserDO> queryOrgUsers(Map<String, Object> params);

    /**
     *
     * <summary>
     * [获取角色list,每个角色联立得到权限对象的list]<br>
     * <br>
     * </summary>
     *
     * @author jojo
     *
     * @return
     */
    public List<RoleDO> queryRoles();

    /**
     *
     * <summary>
     * [获取单个用户,联立得到部门用户list]<br>
     * <br>
     * </summary>
     *
     * @author jojo
     *
     * @param params
     * @return
     */
    public UserDO getUser(Map<String, Object> params);


}
