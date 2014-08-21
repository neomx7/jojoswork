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
     * [获取用户list,每个用户联立得到部门用户list]<br>
     * <br>
     * </summary>
     *
     * @author jojo
     *
     * @return
     */
    public List<UserDO> queryUsers(Map<String, Object> params);

}
