/**
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.dal.common.postgre;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jojo.dal.common.postgre.domain.MaterialApplyDO;
import com.jojo.dal.common.postgre.domain.UserDO;
import com.jojo.util.common.DateUtils;

/**
 * <summary>
 * 流程测试类
 * </summary>
 * classpath*:
 * classpath*:
 *
 * @author jojo
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-applicationContext-dao.xml" })
public class UserMapperTest
{
    private static Logger logger = LoggerFactory.getLogger(UserMapperTest.class);

    private List<String> ids = new ArrayList<String>(8);

    @Autowired
    private UserMapper mapper;

    @BeforeClass
    public static void preClass()
    {
        // RepositoryService repositoryService = (RepositoryService)
        // applicationContext.getBean("repositoryService");
    }

    @Before
    public void setUP()
    {
        ids.clear();
    }

    @After
    public void cleanUp()
    {
        // Map<String, Object> params = new HashMap<String, Object>();
        // for (String id : ids)
        // {
        // params.put("id", id);
        // mapper.delete(params);
        // }
        ids.clear();
    }

    /**
     *
     * <summary>
     * []<br>
     * <br>
     * </summary>
     *
     * @author jojo
     *
     */
    @Test
    public void checkQuery()
    {
        Map<String, Object> params = new HashMap<String, Object>(4);
        params.put("userName", "jojo");
        List<UserDO> list = null;
        try
        {
            list = mapper.queryUsers(params);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Assert.fail("查询异常，测试不通过");
        }

        Assert.assertTrue(list != null && !list.isEmpty());
    }
}
