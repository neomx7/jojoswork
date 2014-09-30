/**
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.biz;

import java.util.List;
import java.util.Map;

import com.jojo.biz.model.JsTreeNode;
import com.jojo.dal.common.postgre.domain.OrgDO;
import com.jojo.dal.common.postgre.domain.OrgUserDO;
import com.jojo.dal.common.postgre.domain.RoleDO;
import com.jojo.dal.common.postgre.domain.UserDO;
import com.jojo.dal.common.postgre.domain.UserResourcesDO;
import com.jojo.util.pojo.DataRequest;

/**
 * <summary>
 * 系统管理:查询系统用户、角色、菜单、权限、部门等基本信息。
 * </summary>
 *
 * @author jojo
 *
 */
public interface SysMgrBiz
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
    public List<RoleDO> queryRoles(Map<String, Object> params);

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

    /**
     *
     * <summary>
     * [获取用户的资源列表，形式String: usrId - List<ResourceDO>]<br>
     * <br>
     * </summary>
     *
     * @author jojo
     *
     * @param params
     * @return
     */
    public List<UserResourcesDO> queryUsrResources(Map<String, Object> params);

    /**
     *
     * <summary>
     * [根据父部门的编号获取其下一级的子类部门列表]<br>
     * <br>
     * </summary>
     *
     * @author jojo
     *
     * @param params
     * @return
     */
    public List<OrgDO> queryDeptsByParentCode(DataRequest dataRequest);

    /**
    *
    * <summary>
    * [根据父部门的编号获取其下一级的子类部门列表]<br>
    * <br>
    * </summary>
    *
    * @author jojo
    *
    * @param params
    * @return
    */
   public List<JsTreeNode> queryDeptsByParentCode4JsTree(DataRequest dataRequest);

   /**
    *
    * <summary>
    * [获取部门的用户]<br>
    * <br>
    * </summary>
    *
    * @author jojo
    *
    * @param dataRequest
    * @return
    */
   public List<UserDO> queryDeptUsers(DataRequest dataRequest);
}
