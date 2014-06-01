package com.jojo.facade.workflow;

import java.util.List;

import com.jojo.util.pojo.DataRequest;
import com.jojo.util.pojo.ProcessTaskForm;
import com.jojo.util.ui.vo.workflow.WorkFlowDefine;

/**
 * <summary>
 * 工作流 API 提供者,实现时  封装 activiti5 的服务
 * </summary>
 *
 * @author jojo
 *
 */
public interface WorkFlowExecutor
{

    /**
     *
     * <summary>
     * [获取流程定义的列表]<br>
     * <br>
     * </summary>
     *
     * @author jojo
     *
     * @return
     */
    public List<WorkFlowDefine> queryFlowDefines(DataRequest request);

    /**
     *
     * <summary>
     * <p>根据状态和当前操作者 id 得到工作流列表</p>
     *
     * </summary>
     *
     * @author jojo
     *
     * @param status  分为 1 :待办;2:在办;3:已完成;
     * @param operId  工作流的相关者 id
     * @return
     */
    public List<?> queryList(int status,String operId);

    /**
     *
     * <summary>
     * <p>启动工作流流程</p>
     * </summary>
     *
     * @author jojo
     *
     */
    public void startProcessInstanceByKey(String processKey,String operId);

    /**
     *
     * <summary>
     * <p>认领工作流的节点任务</p>
     * </summary>
     *
     * @author jojo
     *
     * @param taskId
     */
    public void claimTask(String taskId,String operId);

    /**
     *
     * <summary>
     * <p>结束当前任务</p>
     * </summary>
     *
     * @author jojo
     *
     * @param taskId
     */
    public void completeTask(String taskId,String operId);

    /**
     *
     * <summary>
     * <p>挂起待执行的流程</p>
     * </summary>
     *
     * @author jojo
     *
     * @param processKey
     * @param operId
     */
    public void pendProcessByKey(String processKey,String operId);

    /**
     *
     * <summary>
     * <p>得到任务绑定的 form 表单对象</p>
     * </summary>
     *
     * @author jojo
     *
     * @param taskId
     * @return
     */
    public ProcessTaskForm getTaskForm(String taskId);

}
