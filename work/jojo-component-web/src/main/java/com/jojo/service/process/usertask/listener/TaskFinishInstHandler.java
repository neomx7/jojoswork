/**
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.service.process.usertask.listener;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

import org.activiti.engine.delegate.DelegateTask;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jojo.process.dal.postgre.ProcessMgrMapper;
import com.jojo.util.constants.JOJOConstants;
import com.jojo.util.ui.vo.workflow.WorkFlowTaskBusinessResultDO;

/**
 * <summary>
 * [结束流程时，更新流程业务处理结果]<br>
 * <br>
 * </summary>
 *
 * @author jojo
 *
 */
@Service
public class TaskFinishInstHandler extends TaskHandler
{
    /**   */
    private static final long serialVersionUID = 8067425871680689433L;

    @Autowired
    private ProcessMgrMapper processMgrMapper;

    @Override
    public void notify(DelegateTask delegateTask)
    {
        String uuid = UUID.randomUUID().toString();
        String instanceId = delegateTask.getProcessInstanceId();
        String instTaskId = delegateTask.getId();
        String businessKeyURL = String.valueOf(delegateTask.getVariable(JOJOConstants.WORKFLOW_PROCESSINST_BIZ_KEY_URL)) ;
        String[] busKeyUrls = StringUtils.split(businessKeyURL,"theId=");
        String businessKey =busKeyUrls[1];
        WorkFlowTaskBusinessResultDO taskBusinessResultDO = new WorkFlowTaskBusinessResultDO();
        taskBusinessResultDO.setTheId(uuid);
        taskBusinessResultDO.setBusinessKey(businessKey);
        taskBusinessResultDO.setInstanceId(instanceId);
        taskBusinessResultDO.setInstTaskId(instTaskId);
        taskBusinessResultDO.setStatus(Integer.valueOf(getStatus().getExpressionText()));
        taskBusinessResultDO.setCrtTimestamp(new Timestamp(new Date().getTime()));
        processMgrMapper.insertInstBusinessResult(taskBusinessResultDO);
    }

}
