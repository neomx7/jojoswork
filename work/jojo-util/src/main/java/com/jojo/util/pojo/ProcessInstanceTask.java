/**
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.util.pojo;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <summary>
 * 流程实例中的task节点对象
 * </summary>
 *
 * @author jojo
 *
 */
public class ProcessInstanceTask extends BasePOJO
{

    /**   */
    private static final long serialVersionUID = 3403839722668738733L;

    private int revision;
    private String owner;
    private String assignee;
    private String initialAssignee;
    private String parentTaskId;

    private String taskName;
    private String description;
    private int priority = 0;
    private Date createTime; // The time when the task has been created
    private Date dueDate;
    private int suspensionState = 0;
    private String category;
    private String executionId;
    private String processDefinitionId;
    private String processInstanceId;
    private String tenantId;
    private String taskDefinitionKey;
    private String taskId;

    //下个节点审批人
    private String nextAssignee;

    private Map<String, Object> localVariables = new HashMap<String, Object>();

    public int getRevision()
    {
        return revision;
    }
    public void setRevision(int revision)
    {
        this.revision = revision;
    }
    public String getOwner()
    {
        return owner;
    }
    public void setOwner(String owner)
    {
        this.owner = owner;
    }
    public String getAssignee()
    {
        return assignee;
    }
    public void setAssignee(String assignee)
    {
        this.assignee = assignee;
    }
    public String getInitialAssignee()
    {
        return initialAssignee;
    }
    public void setInitialAssignee(String initialAssignee)
    {
        this.initialAssignee = initialAssignee;
    }
    public String getParentTaskId()
    {
        return parentTaskId;
    }
    public void setParentTaskId(String parentTaskId)
    {
        this.parentTaskId = parentTaskId;
    }
    public String getDescription()
    {
        return description;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }
    public int getPriority()
    {
        return priority;
    }
    public void setPriority(int priority)
    {
        this.priority = priority;
    }
    public Date getCreateTime()
    {
        return createTime;
    }
    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }
    public Date getDueDate()
    {
        return dueDate;
    }
    public void setDueDate(Date dueDate)
    {
        this.dueDate = dueDate;
    }
    public int getSuspensionState()
    {
        return suspensionState;
    }
    public void setSuspensionState(int suspensionState)
    {
        this.suspensionState = suspensionState;
    }
    public String getCategory()
    {
        return category;
    }
    public void setCategory(String category)
    {
        this.category = category;
    }
    public String getExecutionId()
    {
        return executionId;
    }
    public void setExecutionId(String executionId)
    {
        this.executionId = executionId;
    }
    public String getProcessDefinitionId()
    {
        return processDefinitionId;
    }
    public void setProcessDefinitionId(String processDefinitionId)
    {
        this.processDefinitionId = processDefinitionId;
    }
    public String getProcessInstanceId()
    {
        return processInstanceId;
    }
    public void setProcessInstanceId(String processInstanceId)
    {
        this.processInstanceId = processInstanceId;
    }
    public String getTenantId()
    {
        return tenantId;
    }
    public void setTenantId(String tenantId)
    {
        this.tenantId = tenantId;
    }
    public String getTaskDefinitionKey()
    {
        return taskDefinitionKey;
    }
    public void setTaskDefinitionKey(String taskDefinitionKey)
    {
        this.taskDefinitionKey = taskDefinitionKey;
    }
    public String getTaskId()
    {
        return taskId;
    }
    public void setTaskId(String taskId)
    {
        this.taskId = taskId;
    }
    public String getTaskName()
    {
        return taskName;
    }
    public void setTaskName(String taskName)
    {
        this.taskName = taskName;
    }
    public String getNextAssignee()
    {
        return nextAssignee;
    }
    public void setNextAssignee(String nextAssignee)
    {
        this.nextAssignee = nextAssignee;
    }
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("ProcessTask [revision=");
        builder.append(revision);
        builder.append(", owner=");
        builder.append(owner);
        builder.append(", assignee=");
        builder.append(assignee);
        builder.append(", initialAssignee=");
        builder.append(initialAssignee);
        builder.append(", parentTaskId=");
        builder.append(parentTaskId);
        builder.append(", taskName=");
        builder.append(taskName);
        builder.append(", description=");
        builder.append(description);
        builder.append(", priority=");
        builder.append(priority);
        builder.append(", createTime=");
        builder.append(createTime);
        builder.append(", dueDate=");
        builder.append(dueDate);
        builder.append(", suspensionState=");
        builder.append(suspensionState);
        builder.append(", category=");
        builder.append(category);
        builder.append(", executionId=");
        builder.append(executionId);
        builder.append(", processDefinitionId=");
        builder.append(processDefinitionId);
        builder.append(", processInstanceId=");
        builder.append(processInstanceId);
        builder.append(", tenantId=");
        builder.append(tenantId);
        builder.append(", taskDefinitionKey=");
        builder.append(taskDefinitionKey);
        builder.append(", taskId=");
        builder.append(taskId);
        builder.append(", nextAssignee=");
        builder.append(nextAssignee);
        builder.append(", toString()=");
        builder.append(super.toString());
        builder.append("]");
        return builder.toString();
    }
    public Map<String, Object> getLocalVariables()
    {
        return localVariables;
    }
    public void setLocalVariables(Map<String, Object> localVariables)
    {
        this.localVariables = localVariables;
    }

}
