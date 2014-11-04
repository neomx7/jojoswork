/**
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.dal.common.postgre;

import java.util.List;
import java.util.Map;

import com.jojo.dal.common.postgre.domain.UserDO;

/**
 * <summary>
 * <br>
 * </summary>
 *
 * @author jojo
 *
 */
public interface UserMapper
{

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
     * [保存用户信息]<br>
     * <br>
     * </summary>
     *
     * @author jojo
     *
     * @param params
     */
    public void saveUser(Map<String, Object> params);

}
