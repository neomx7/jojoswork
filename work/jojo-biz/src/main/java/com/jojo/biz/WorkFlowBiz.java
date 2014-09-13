/**
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.biz;

import java.util.List;
import java.util.Map;

import com.jojo.dal.common.postgre.domain.MaterialApplyDO;
import com.jojo.dal.common.postgre.domain.WorkFlowTaskApprovalDO;
import com.jojo.util.pojo.DataRequest;

/**
 * <summary>
 * 物料申请的biz
 * </summary>
 *
 * @author jojo
 *
 */
public interface WorkFlowBiz
{

    /**
     *
     * <summary>
     * [根据条件找到申请对象，1条]<br>
     * <br>
     * </summary>
     *
     * @author jojo
     *
     * @param params
     * @return
     */
    public WorkFlowTaskApprovalDO findTaskApproval(Map<String, Object> params);

    /**
     *
     * <summary>
     * [根据businesKey等查询工作流的task审批节点]<br>
     * <br>
     * </summary>
     *
     * @author jojo
     *
     * @param params
     * @return
     */
   public List<WorkFlowTaskApprovalDO> queryTaskApproval(DataRequest request);

   /**
    *
    * <summary>
    * [新增物料申请]<br>
    * <br>
    * </summary>
    *
    * @author jojo
    *
    * @param applyDO
    */
   public void addTaskApproval(WorkFlowTaskApprovalDO approvalDO);

//   /**
//    *
//    * <summary>
//    * [保存物料申请]<br>
//    * <br>
//    * </summary>
//    *
//    * @author jojo
//    *
//    * @param applyDO
//    */
//   public void editApply(Map<String, Object> params);
}
