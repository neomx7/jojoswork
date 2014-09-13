/**
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.webapp.form;

import java.util.List;

import com.jojo.dal.common.postgre.domain.WorkFlowTaskApprovalDO;
import com.jojo.util.pojo.ProcessInstanceTask;

/**
 * <summary>
 *
 * </summary>
 *
 * @author jojo
 *
 */
public class ProcessInstanceTaskForm extends BaseForm
{

    /**   */
    private static final long serialVersionUID = 3403839722668738733L;


    private ProcessInstanceTask processInstanceTask = new ProcessInstanceTask();

    private String businessKey;

    /**
     * 查询表单内容的spring bean名称，即配置在applicationContext-biz.xml的 bizBean们
     */
    private String businessKeyURL;

    /**
     * 当前任务id
     */
    private String theTaskId;
    private String theInstId;
    private String nextAssignee;


    private String apprvContent;
    private int apprvFlg;

    /**
     */
    private List<WorkFlowTaskApprovalDO> approvals;


    public ProcessInstanceTask getProcessInstanceTask()
    {
        return processInstanceTask;
    }


    public void setProcessInstanceTask(ProcessInstanceTask processInstanceTask)
    {
        this.processInstanceTask = processInstanceTask;
    }


    public String getBusinessKey()
    {
        return businessKey;
    }


    public void setBusinessKey(String businessKey)
    {
        this.businessKey = businessKey;
    }


    public String getBusinessKeyURL()
    {
        return businessKeyURL;
    }


    public void setBusinessKeyURL(String businessKeyURL)
    {
        this.businessKeyURL = businessKeyURL;
    }


    public String getTheTaskId()
    {
        return theTaskId;
    }


    public void setTheTaskId(String theTaskId)
    {
        this.theTaskId = theTaskId;
    }


    public String getTheInstId()
    {
        return theInstId;
    }


    public void setTheInstId(String theInstId)
    {
        this.theInstId = theInstId;
    }


    public String getNextAssignee()
    {
        return nextAssignee;
    }


    public void setNextAssignee(String nextAssignee)
    {
        this.nextAssignee = nextAssignee;
    }


    public String getApprvContent()
    {
        return apprvContent;
    }


    public void setApprvContent(String apprvContent)
    {
        this.apprvContent = apprvContent;
    }


    public int getApprvFlg()
    {
        return apprvFlg;
    }


    public void setApprvFlg(int apprvFlg)
    {
        this.apprvFlg = apprvFlg;
    }


    public List<WorkFlowTaskApprovalDO> getApprovals()
    {
        return approvals;
    }


    public void setApprovals(List<WorkFlowTaskApprovalDO> approvals)
    {
        this.approvals = approvals;
    }




}
