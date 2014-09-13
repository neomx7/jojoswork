/**
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.biz.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jojo.biz.UserBiz;
import com.jojo.dal.common.postgre.UserMapper;
import com.jojo.dal.common.postgre.domain.UserDO;
import com.jojo.util.biz.bo.UserBO;
import com.jojo.util.common.ExtBeanUtils;

/**
 * <summary>
 *
 * </summary>
 *
 * @author jojo
 *
 */
@Repository
public class UserBizImpl implements UserBiz
{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserBO> queryUsers(Map<String, Object> params)
    {
        List<UserDO> userDOs = null;
        try
        {
            userDOs = userMapper.queryUsers(params);
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), e);
        }
        List<UserBO> list = new ArrayList<UserBO>();
        if (userDOs != null && !userDOs.isEmpty())
        {
            for (UserDO userDO : userDOs)
            {
                UserBO userBO = new UserBO();
                ExtBeanUtils.copyProperties(userBO, userDO);
                list.add(userBO);
            }
        }

        return list;
    }

}
