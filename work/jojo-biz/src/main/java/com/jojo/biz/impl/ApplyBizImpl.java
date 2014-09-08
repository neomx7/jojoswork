/**
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.biz.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jojo.biz.ApplyBiz;
import com.jojo.dal.common.postgre.MaterialApplyMapper;
import com.jojo.dal.common.postgre.domain.MaterialApplyDO;
import com.jojo.util.pojo.DataRequest;
import com.jojo.util.pojo.DataRequest4Apply;

/**
 * <summary>
 *
 * </summary>
 *
 * @author jojo
 *
 */
@Repository
public class ApplyBizImpl implements ApplyBiz
{

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MaterialApplyMapper mapper;

    @Override
    public List<MaterialApplyDO> queryPage(DataRequest request)
    {
        Map<String, Object> params = new HashMap<String, Object>(12);
        params.put("status", ((DataRequest4Apply)request).getStatus());
        params.put("theName", ((DataRequest4Apply)request).getTitle());
        params.put("limitPage", request.getRows());
        params.put("offset", (request.getPage() - 1)*request.getRows());
        return mapper.queryPage(params);
    }

    @Override
    public void addApply(MaterialApplyDO applyDO)
    {
        mapper.insert(applyDO);
    }

    @Override
    public void editApply(Map<String, Object> params)
    {
        mapper.update(params);
    }

    @Override
    public MaterialApplyDO findApply(Map<String, Object> params)
    {
        return mapper.select(params);
    }


}
