package com.jojo.util.ui.vo.workflow;

import com.jojo.util.pojo.ProcessTask;

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
public class WorkFlowTaskDTO extends ProcessTask
{

    /**   */
    private static final long serialVersionUID = -858320274900041485L;

    private String deploymentId;
    private String resourceName;
    private String diagramResourceName;

    //流程发起人ID
    private String processInitor;
    //流程发起人名称
    private String processInitorName;

    //流程名称
    private String processName;

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
    public String getProcessInitor()
    {
        return processInitor;
    }
    public void setProcessInitor(String processInitor)
    {
        this.processInitor = processInitor;
    }
    public String getProcessName()
    {
        return processName;
    }
    public void setProcessName(String processName)
    {
        this.processName = processName;
    }
    public String getProcessInitorName()
    {
        return processInitorName;
    }
    public void setProcessInitorName(String processInitorName)
    {
        this.processInitorName = processInitorName;
    }

}
