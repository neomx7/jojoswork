/**
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.biz.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jojo.biz.WorkFlowBiz;
import com.jojo.dal.common.postgre.WorkFlowTaskApprovalMapper;
import com.jojo.dal.common.postgre.domain.WorkFlowTaskApprovalDO;
import com.jojo.util.pojo.DataRequest;
import com.jojo.util.pojo.DataRequest4WFTaskApprv;

/**
 * <summary>
 *
 * </summary>
 *
 * @author jojo
 *
 */
@Repository
public class WorkFlowBizImpl implements WorkFlowBiz
{

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private WorkFlowTaskApprovalMapper mapper;

    @Override
    public WorkFlowTaskApprovalDO findTaskApproval(Map<String, Object> params)
    {
        return mapper.select(params);
    }

    @Override
    public List<WorkFlowTaskApprovalDO> queryTaskApproval(DataRequest request)
    {
        DataRequest4WFTaskApprv dataRequest4WFTaskApprv = (DataRequest4WFTaskApprv)request;
        Map<String, Object> params = new HashMap<String, Object>(4);
        params.put("businessKey", dataRequest4WFTaskApprv.getBusinessKey());
        return mapper.query(params);
    }

    @Override
    public void addTaskApproval(WorkFlowTaskApprovalDO approvalDO)
    {
        mapper.insert(approvalDO);
    }

    @Override
    public void updateTaskApproval(WorkFlowTaskApprovalDO approvalDO)
    {
        Map<String, Object> params = new HashMap<String, Object>(16);
        params.put("theId", approvalDO.getTheId());
        params.put("updTime", new Timestamp(new Date().getTime()));
        params.put("apprvFlg", approvalDO.getApprvFlg());
        params.put("updUserId", approvalDO.getUpdUserId());
        params.put("updUserName", approvalDO.getUpdUserName());
        mapper.update(params);

    }



}
