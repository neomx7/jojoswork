/**
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.service.process;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jojo.integration.workflow.WorkFlowTaskFormDataParser;
import com.jojo.util.dal.domain.ProcessTaskFormDO;

/**
 *
 * <summary>
 * [从数据库中获取流程所需的task 对应的表单 form 数据]<br>
 * <br>
 * </summary>
 *
 * @author jojo
 *
 */
public class WorkFlowTaskFormDataParserImpl implements WorkFlowTaskFormDataParser
{
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public ProcessTaskFormDO getTaskFormData(String taskId)
    {
        // TODO Auto-generated method stub
        logger.info("getTaskFormData by task Id.");
        return null;
    }


}
