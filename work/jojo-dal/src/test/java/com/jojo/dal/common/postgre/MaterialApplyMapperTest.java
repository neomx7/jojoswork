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

import com.jojo.dal.common.postgre.MaterialApplyMapper;
import com.jojo.dal.common.postgre.domain.MaterialApplyDO;
import com.jojo.util.common.DateUtils;

/**
 * <summary>
 * 流程测试类
 * </summary>
 * classpath*:
 *classpath*:
 * @author jojo
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-applicationContext-dao.xml" })
public class MaterialApplyMapperTest
{
    private static Logger logger = LoggerFactory.getLogger(MaterialApplyMapperTest.class);

    private List<String> ids = new ArrayList<String>(8);

    @Autowired
    private MaterialApplyMapper mapper;

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
        Map<String, Object> params = new HashMap<String, Object>();
        for (String id : ids)
        {
            params.put("id", id);
            mapper.delete(params);
        }
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
    public void checkAdd()
    {
        MaterialApplyDO applyDO = createOne();
        String id = applyDO.getTheId();
        try
        {
            mapper.insert(applyDO);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Assert.fail("新增异常，测试不通过");
        }

        // 查询一条记录
        Map<String, Object> params = new HashMap<String, Object>(2);
        params.put("id", id);
        MaterialApplyDO applyDO2 = null;

        try
        {
            applyDO2 = mapper.select(params);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Assert.fail("查询异常，测试不通过");
        }

        Assert.assertTrue(applyDO2.getTheName().equals(applyDO.getTheName()));
    }

    public MaterialApplyDO createOne()
    {
        MaterialApplyDO applyDO = new MaterialApplyDO();
        applyDO.setTheId(UUID.randomUUID().toString());
        applyDO.setTheName("测试add new申请");
        applyDO.setTheRemark("jojo test");
        applyDO.setCrtTime(DateUtils.getCurrentDateTimeMs());
        applyDO.setCrtUserName("新来的");
        applyDO.setCrtUserId("jojo");
        return applyDO;
    }
}
