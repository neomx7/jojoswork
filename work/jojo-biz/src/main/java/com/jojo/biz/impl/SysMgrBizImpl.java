/**
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.biz.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jojo.biz.SysMgrBiz;
import com.jojo.dal.common.postgre.domain.OrgUserDO;
import com.jojo.dal.common.postgre.domain.RoleDO;
import com.jojo.dal.common.postgre.domain.UserDO;
import com.jojo.dal.common.postgre.domain.UserResourcesDO;
import com.jojo.facade.service.SysMgrService;

/**
 * <summary>
 * []<br>
 * <br>
 * </summary>
 *
 * @author jojo
 *
 */
@Repository
public class SysMgrBizImpl implements SysMgrBiz
{
    @Autowired
    private SysMgrService sysMgrService;

    @Override
    public List<UserDO> queryUsers(Map<String, Object> params)
    {
        return sysMgrService.queryUsers(params);
    }

    @Override
    public List<OrgUserDO> queryOrgUsers(Map<String, Object> params)
    {
        return sysMgrService.queryOrgUsers(params);
    }

    @Override
    public List<RoleDO> queryRoles(Map<String, Object> params)
    {
        return sysMgrService.queryRoles(params);
    }

    @Override
    public UserDO getUser(Map<String, Object> params)
    {
        return sysMgrService.getUser(params);
    }

    @Override
    public List<UserResourcesDO> queryUsrResources(Map<String, Object> params)
    {
        return sysMgrService.queryUsrResources(params);
    }

}
