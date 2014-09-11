package com.jojo.util.ui.vo.workflow;

import com.jojo.util.pojo.ProcessInstanceTask;

/**
 *
 * <summary>
 * [流程节点任务对象，列表查询时使用]<br>
 * <br>
 * </summary>
 *
 * @author jojo
 *
 */
public class WorkFlowTaskDTO extends ProcessInstanceTask
{

    /**   */
    private static final long serialVersionUID = -858320274900041485L;

    private String deploymentId;
    private String resourceName;
    private String diagramResourceName;

//    //流程发起人ID
//    private String processInitor;
//    //流程发起人名称
//    private String processInitorName;

    //流程名称
    private String processName;
    //流程版本
    private String processVersion;

    //流程key，对应流程定义xml中的<process id="lishengProcess1"
    private String processKey;
    //业务key
    private String businessKey;

    public String getDeploymentId()
    {
        return deploymentId;
    }
    public void setDeploymentId(String deploymentId)
    {
        this.deploymentId = deploymentId;
    }
    public String getResourceName()
    {
        return resourceName;
    }
    public void setResourceName(String resourceName)
    {
        this.resourceName = resourceName;
    }
    public String getDiagramResourceName()
    {
        return diagramResourceName;
    }
    public void setDiagramResourceName(String diagramResourceName)
    {
        this.diagramResourceName = diagramResourceName;
    }
    public String getProcessName()
    {
        return processName;
    }
    public void setProcessName(String processName)
    {
        this.processName = processName;
    }
    public String getBusinessKey()
    {
        return businessKey;
    }
    public void setBusinessKey(String businessKey)
    {
        this.businessKey = businessKey;
    }
    public String getProcessVersion()
    {
        return processVersion;
    }
    public void setProcessVersion(String processVersion)
    {
        this.processVersion = processVersion;
    }
    public String getProcessKey()
    {
        return processKey;
    }
    public void setProcessKey(String processKey)
    {
        this.processKey = processKey;
    }

}
