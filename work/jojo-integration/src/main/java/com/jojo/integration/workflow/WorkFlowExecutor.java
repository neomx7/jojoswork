package com.jojo.integration.workflow;

import java.awt.Point;
import java.util.List;
import java.util.Map;

import com.jojo.util.biz.bo.PageResultBO;
import com.jojo.util.constants.JOJOConstants;
import com.jojo.util.pojo.DataRequest;
import com.jojo.util.pojo.ProcessInstance;
import com.jojo.util.pojo.ProcessInstanceTask;
import com.jojo.util.ui.vo.workflow.WorkFlowDefine;
import com.jojo.util.ui.vo.workflow.WorkFlowDefineGraph;
import com.jojo.util.ui.vo.workflow.WorkFlowQuery;
import com.jojo.util.ui.vo.workflow.WorkFlowTaskDTO;

/**
 * <summary>
 * 工作流 API 提供者,实现时 封装 activiti5 的服务
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
     * [跟踪并获取流程详情list，每个流程的task都会生成一个Map<String, Object>结构的详情对象]<br>
     * <br>
     * 详情对象可用的key有---- <br>
     * 1. boolean 流程实例是待办task，还是是已完成的task : {@linkplain JOJOConstants#WORKFLOW_PROCESSINST_TASK_ACTIVITI}<br>
     * 2. int 流程图的宽度:
     * {@linkplain JOJOConstants#WORKFLOW_PROCESSINST_TASK_GRH_WIDTH}
     * ;<br>
     * 3. int 高度:
     * {@linkplain JOJOConstants#WORKFLOW_PROCESSINST_TASK_GRH_HEIGHT}
     * <br>
     * 4. int 流程图的开始坐标x:
     * {@linkplain JOJOConstants#WORKFLOW_PROCESSINST_TASK_GRH_X} ;<br>
     * 5. int 开始坐标y:
     * {@linkplain JOJOConstants#WORKFLOW_PROCESSINST_TASK_GRH_Y}<br>
     *
     * 6. Map<String, Object> task相关的属性键值对，:
     * {@linkplain JOJOConstants#WORKFLOW_PROCESSINST_VARS_KEY}
     * 内层的Map对象结构:<br>
     * <table>
     *  <tr>
     *      <th>
     *          序号
     *      </th>
     *      <th>
     *          说明
     *      </th>
     *      <th>
     *          Key
     *      </th>
     *      <th>
     *          java类型
     *      </th>
     *  </tr>
     *  <tr>
     *      <td>
     *       6.1
     *      </td>
     *      <td>
     *       task名称
     *      </td>
     *      <td>
     *       {@linkplain JOJOConstants#WORKFLOW_PROCESSINST_VALS_TASK_TIP}
     *      </td>
     *      <td>String</td>
     *  </tr>
     *  <tr>
     *      <td>
     *       6.2
     *      </td>
     *      <td>
     *        task描述
     *      </td>
     *      <td>
     *       {@linkplain JOJOConstants#WORKFLOW_PROCESSINST_VALS_TASK_DESC}
     *      </td>
     *       <td>String</td>
     *  </tr>
     *  <tr>
     *      <td>
     *       6.3
     *      </td>
     *      <td>
     *        task处理人
     *      </td>
     *      <td>
     *       {@linkplain JOJOConstants#WORKFLOW_PROCESSINST_VALS_TASK_ASSIGNEE}
     *      </td>
     *       <td>String</td>
     *  </tr>
     *  <tr>
     *      <td>
     *       6.5
     *      </td>
     *      <td>
     *        task类型
     *      </td>
     *      <td>
     *       {@linkplain JOJOConstants#WORKFLOW_PROCESSINST_VALS_TASK_TYPE}
     *      </td>
     *       <td>String</td>
     *  </tr>
     *
     * </table>
     *
     * </summary>
     *
     * @author jojo
     *
     * @param processInstanceId
     * @return
     * @throws Exception
     */
    public List<Map<String, Object>> traceProcessDetails(String processInstanceId) throws Exception;

    /**
     *
     * <summary>
     * [检视并生成流程图片]<br>
     * <br>
     * </summary>
     *
     * @author jojo
     *
     */
    public void genProcessGraph();

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
     * [查询当前登录用户的待办任务列表]<br>
     * <br>
     * </summary>
     *
     * @author jojo
     *
     * @param query
     * @return
     */
    public PageResultBO<?> queryWorkFlowTODOTask(WorkFlowQuery query);

    /**
    *
    * <summary>
    * [查询当前登录用户的在办任务列表]<br>
    * <br>
    * </summary>
    *
    * @author jojo
    *
    * @param query
    * @return
    */
    public PageResultBO<WorkFlowTaskDTO> queryWorkFlowDOingTask(WorkFlowQuery query);

    /**
     *
     * <summary>
     * <p>
     * 根据状态和当前操作者 id 得到工作流列表
     * </p>
     *
     * </summary>
     *
     * @author jojo
     *
     * @param status
     *            分为 1 :待办;2:在办;3:已完成;
     * @param operId
     *            工作流的相关者 id
     * @return
     */
    public List<WorkFlowTaskDTO> queryList(int status, String operId);

    /**
     *
     * <summary>
     * [启动工作流流程]<br>
     * <br>
     * </summary>
     *
     * @author jojo
     *
     * @param processKey
     * @param operId
     * @param buinessKey
     *            这里填写业务entry的数据库id
     * @param variables
     */
    public String startProcessInstanceByKey(String processKey, String operId, String buinessKey,
            Map<String, Object> variables);

    public WorkFlowTaskDTO getProcessActivity(String processInstanceId);

    /**
     *
     * <summary>
     * <p>
     * 认领工作流的节点任务
     * </p>
     * </summary>
     *
     * @author jojo
     *
     * @param taskId
     */
    public void claimTask(String taskId, String operId);

    /**
     *
     * <summary>
     * <p>
     * 结束当前任务 <br>
     * 如果使用 task.setVariables方法，则设置的值全流程范围有效；由于当前流程没有并发执行，所以目前不会有问题。<br>
     * 后续如果出现并发的话，还是设置全局变量，但是要为每个分支的候选人都设一个变量名，如${nextAssignee1}  ${nextAssignee2} ，避免重复。<br>
     * </p>
     * </summary>
     *
     * @author jojo
     *
     * @param processTask
     */
    public void completeTask(ProcessInstanceTask processTask);

    /**
     *
     * <summary>
     * <p>
     * 挂起待执行的流程
     * </p>
     * </summary>
     *
     * @author jojo
     *
     * @param processKey
     * @param operId
     */
    public void pendProcessByKey(String processKey, String operId);

    public ProcessInstanceTask getProcessInstanceTask(String taskId);

    /**
     *
     * <summary>
     * [得到流程图片]<br>
     * <br>
     * </summary>
     *
     * @author jojo
     *
     * @param defineId
     * @return
     */
    public WorkFlowDefineGraph getProcessGraph(String proDefId);

    /**
     *
     * <summary>
     * [得到流程图片的位置]<br>
     * <br>
     * </summary>
     *
     * @author jojo
     *
     * @param proDefId
     * @return
     */
    public Point locationWorkFlowGraph(String proDefId);

    /**
     * 获取未签收的任务查询对象
     *
     * @param userId
     *            用户ID
     */
    public List<ProcessInstanceTask> createUnsignedTaskQuery(String userId, String processDefKey);

    /**
     * 获取正在处理的任务查询对象
     *
     * @param userId
     *            用户ID
     */
    public List<ProcessInstanceTask> createTodoTaskQuery(String userId, String processDefKey);

    /**
     *
     * <summary>
     * [根据流程实例id获取任务列表]<br>
     * <br>
     * </summary>
     *
     * @author jojo
     *
     * @param instanceId
     * @return
     */
    public List<ProcessInstanceTask> getInstTasks(String instanceId);

    // /**
    // * 获取未经完成的流程实例查询对象
    // * @param userId 用户ID
    // */
    // public List<ProcessTask> createUnFinishedProcessInstanceQuery(String
    // userId, String processDefKey);
    // /**
    // * 获取已经完成的流程实例查询对象
    // * @param userId 用户ID
    // */
    // public List<ProcessTask> createFinishedProcessInstanceQuery(String
    // userId, String processDefKey);
    /**
     *
     * <summary>
     * [获取流程跟踪图]<br>
     * <br>
     * </summary>
     *
     * @author jojo
     *
     * @param processInstanceId
     * @return
     */
    public Map<String, Object> traceProcess(String processInstanceId);

    /**
     *
     * <summary>
     * [获取流程实例对象]<br>
     * <br>
     * </summary>
     *
     * @author jojo
     *
     * @param processInstanceId
     * @return
     */
    public ProcessInstance getProcessInstance(String processInstanceId);

}
