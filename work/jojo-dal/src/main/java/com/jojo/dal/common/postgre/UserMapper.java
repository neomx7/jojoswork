/**
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.dal.common.postgre;

import java.util.List;

import com.jojo.dal.common.postgre.domain.UserDO;

/**
 * <summary>
 * [系统户Mapper]<br>
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
     * [获取所有用户]<br>
     * <br>
     * </summary>
     *
     * @author jojo
     *
     * @return
     */
    public List<UserDO> queryAll();



}
