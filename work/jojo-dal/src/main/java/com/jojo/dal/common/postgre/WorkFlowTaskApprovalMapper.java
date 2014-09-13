/**
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.dal.common.postgre;

import java.util.List;
import java.util.Map;

import com.jojo.dal.common.postgre.domain.WorkFlowTaskApprovalDO;

/**
 * <summary>
 * <br>工作流的task节点审批
 * </summary>
 *
 * @author jojo
 *
 */
public interface WorkFlowTaskApprovalMapper
{

    /**
     *
     * <summary>
     * [[新增]]<br>
     * <br>
     * </summary>
     *
     * @author jojo
     *
     * @param applyDO
     */
    public void insert(WorkFlowTaskApprovalDO approvalDO);

    /**
     *
     * <summary>
     * [更新]<br>
     * <br>
     * </summary>
     *
     * @author jojo
     *
     * @param params
     */
    public void update(Map<String, Object> params);

    /**
     *
     * <summary>
     * [删除]<br>
     * <br>
     * </summary>
     *
     * @author jojo
     *
     * @param params
     */
    public void delete(Map<String, Object> params);

    /**
     *
     * <summary>
     * [获取一个申请]<br>
     * <br>
     * </summary>
     *
     * @author jojo
     *
     * @param params
     * @return
     */
    public WorkFlowTaskApprovalDO select(Map<String, Object> params);

    /**
     *
     * <summary>
     * [查询，翻页]<br>
     * <br>
     * </summary>
     *
     * @author jojo
     *
     * @param params
     * @return
     */
    public List<WorkFlowTaskApprovalDO> query(Map<String, Object> params);

}
