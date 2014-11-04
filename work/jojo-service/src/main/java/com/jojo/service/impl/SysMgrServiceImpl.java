/**
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.activemq.filter.function.splitFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jojo.dal.common.postgre.SysMgrMapper;
import com.jojo.dal.common.postgre.UserMapper;
import com.jojo.dal.common.postgre.domain.OrgDO;
import com.jojo.dal.common.postgre.domain.OrgUserDO;
import com.jojo.dal.common.postgre.domain.RoleDO;
import com.jojo.dal.common.postgre.domain.UserDO;
import com.jojo.dal.common.postgre.domain.UserResourcesDO;
import com.jojo.service.SysMgrService;

/**
 * <summary>
 * TODO 该部分最好提取到biz层，或在biz层包装一下
 * <br>
 * </summary>
 *
 * @author jojo
 *
 */
@Service
public class SysMgrServiceImpl implements SysMgrService
{
    @Autowired
    private SysMgrMapper mapper;

    @Autowired
    private UserMapper userMapper;


    @Override
    public List<UserDO> queryUsers(Map<String, Object> params)
    {
        return mapper.queryUsers(params);
    }

    @Override
    public List<OrgUserDO> queryOrgUsers(Map<String, Object> params)
    {
        return mapper.queryOrgUsers(params);
    }

    @Override
    public List<RoleDO> queryRoles(Map<String, Object> params)
    {
        return mapper.queryRoles(params);
    }

    @Override
    public UserDO getUser(Map<String, Object> params)
    {
        return userMapper.getUser(params);
    }

    @Override
    public List<UserResourcesDO> queryUsrResources(Map<String, Object> params)
    {
        return mapper.queryUsrResources(params);
    }

    @Override
    public List<OrgDO> queryDeptsByParentCode(Map<String, Object> params)
    {
        return mapper.queryDeptsByParentCode(params);
    }

    @Override
    public List<UserDO> queryDeptUser(Map<String, Object> params)
    {
        return mapper.queryDeptUsers(params);
    }

    @Override
    public void saveUser(Map<String, Object> params)
    {
        userMapper.saveUser(params);
    }
}
