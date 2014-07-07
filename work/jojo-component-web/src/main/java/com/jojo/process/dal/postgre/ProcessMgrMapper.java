/**
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.process.dal.postgre;

import java.util.List;
import java.util.Map;

import com.jojo.util.ui.vo.workflow.WorkFlowTaskDTO;

/**
 * <summary>
 * []<br>
 * <br>
 * </summary>
 *
 * @author jojo
 *
 */
public interface ProcessMgrMapper
{

    /**
     *
     * <summary>
     * [得到待办列表总数]<br>
     * <br>
     * </summary>
     *
     * @author jojo
     *
     * @return
     */
    public Long getTODOCount(Map<String, Object> params);

    /**
     *
     * <summary>
     * [得到待办列表]<br>
     * <br>
     * </summary>
     *
     * @author jojo
     *
     * @param params
     * @return
     */
    public List<WorkFlowTaskDTO> qryTODOList(Map<String, Object> params);
}
