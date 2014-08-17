package com.jojo.facade.workflow;

import java.awt.Point;
import java.util.List;
import java.util.Map;

import com.jojo.util.biz.bo.PageResultBO;
import com.jojo.util.pojo.DataRequest;
import com.jojo.util.pojo.ProcessTask;
import com.jojo.util.pojo.ProcessTaskForm;
import com.jojo.util.ui.vo.workflow.WorkFlowDefine;
import com.jojo.util.ui.vo.workflow.WorkFlowDefineGraph;
import com.jojo.util.ui.vo.workflow.WorkFlowQuery;
import com.jojo.util.ui.vo.workflow.WorkFlowTaskDTO;

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


    public PageResultBO<?> queryWorkFlowTODOTask(WorkFlowQuery query);


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
    public List<WorkFlowTaskDTO> queryList(int status,String operId);

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
     * @param buinessKey  这里填写业务entry的数据库id
     * @param variables
     */
    public String startProcessInstanceByKey(String processKey,String operId,String buinessKey, Map<String, Object> variables);

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
     * @param processTask
     */
    public void completeTask(ProcessTask processTask);

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
     * @param userId    用户ID
     */
    public List<ProcessTask> createUnsignedTaskQuery(String userId,String processDefKey);

    /**
     * 获取正在处理的任务查询对象
     * @param userId    用户ID
     */
    public List<ProcessTask> createTodoTaskQuery(String userId, String processDefKey) ;

//    /**
//     * 获取未经完成的流程实例查询对象
//     * @param userId    用户ID
//     */
//    public List<ProcessTask> createUnFinishedProcessInstanceQuery(String userId, String processDefKey);
//    /**
//     * 获取已经完成的流程实例查询对象
//     * @param userId    用户ID
//     */
//    public List<ProcessTask> createFinishedProcessInstanceQuery(String userId, String processDefKey);
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

}




