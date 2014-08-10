/**
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jojo.dal.common.postgre.SysMgrMapper;
import com.jojo.dal.common.postgre.domain.OrgUserDO;
import com.jojo.dal.common.postgre.domain.RoleDO;
import com.jojo.dal.common.postgre.domain.UserDO;
import com.jojo.dal.common.postgre.domain.UserResourcesDO;

/**
 * <summary>
 * [系统管理的dal层测试]<br>
 * <br>
 * </summary>
 *
 * @author jojo
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext-dao.xml"})
public class SysMgrMapperTest
{
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private SysMgrMapper sysMgrMapper;
    private ApplicationContext ctx = new FileSystemXmlApplicationContext(
            "classpath*:applicationContext-dao.xml");

    @Before
    public void before()
    {
        sysMgrMapper = (SysMgrMapper) ctx
                .getBean("sysMgrMapper");
        Assert.assertNotNull(sysMgrMapper);
    }

    @After
    public void after()
    {

    }

    /**
     *
     * <summary>
     * [查询系统用户]<br>
     * <br>
     * </summary>
     *
     * @author jojo
     *
     */
    @Test
    public void checkQryUsrs()
    {
        Map<String, Object> params = new HashMap<String, Object>();
        List<UserDO> userDOs = sysMgrMapper.queryUsers(params);
        logger.info(userDOs.toString());
        Assert.assertTrue(userDOs != null);
    }

    @Test
    public void checkQryOrgUsrs()
    {
        Map<String, Object> params = new HashMap<String, Object>();
        List<OrgUserDO> list = sysMgrMapper.queryOrgUsers(params);
        logger.info(list.toString());
        Assert.assertTrue(list != null);
    }


    @Test
    public void checkQryRoles()
    {
        Map<String, Object> params = new HashMap<String, Object>();
        List<RoleDO> list = sysMgrMapper.queryRoles(params);
        logger.info(list.toString());
        Assert.assertTrue(list != null);
    }

    @Test
    public void checkUsrResources()
    {
        Map<String, Object> params = new HashMap<String, Object>();
        List<UserResourcesDO> list = sysMgrMapper.queryUsrResources(params);
        logger.info(list.toString());
        Assert.assertTrue(list != null);
    }
}

