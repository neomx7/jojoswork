package com.jojo.facade.service;

import java.util.List;
import java.util.Map;

import com.jojo.dal.common.postgre.domain.OrgUserDO;
import com.jojo.dal.common.postgre.domain.RoleDO;
import com.jojo.dal.common.postgre.domain.UserDO;
import com.jojo.dal.common.postgre.domain.UserResourcesDO;
import com.jojo.util.service.model.MenuMO;

/**
 *
 * <summary>
 * 系统维护，初始化系统的用户、角色、权限、菜单等数据到内存中，作为缓存使用
 *
 * </summary>
 *
 * @author jojo
 *
 */
public interface SysMgrService
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
}
