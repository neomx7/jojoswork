/**
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.util.constants;

/**
 * <summary>
 * []<br>
 * <br>
 * </summary>
 *
 * @author jojo
 *
 */
public final class JOJOConstants
{

    /**
     * 防止初始化该常量类
     */
    private JOJOConstants(){}
    /**
     * 工作流的spring httpinvoker 的bean名称
     */
    public static final String WORKFLOW_SERVICE = "workFlowServiceProxy";

    public static final String WORKFLOW_PROCESS_KEY_MATERIALAPPLYPROCESS = "materialApplyProcess";

    /**
     *""
     */
    public static final String WORKFLOW_TASK_VARIABLES_KYE_APPROVEDREQUIRED = "approvedRequired";
    public static final String WORKFLOW_TASK_VARIABLES_KYE_APPROVEDBYMANAGER = "approvedByManager";

    public static final int PAGE_STARTPAGE_DEFAULT = 1;
    public static final int PAGE_LIMIT_DEFAULT = 20;


    public static final Integer WORKFLOW_TASKMODE_TODO = 1;
    public static final Integer WORKFLOW_TASKMODE_DOING = 2;
    public static final Integer WORKFLOW_TASKMODE_DONE = 9;

    public static final String SESSION_KEY_LOGINUSER = "loginUser";

    public static final String COMMA = ",";
    public static final String UNDERLINE = "_";

    public static final int NUM_ONE = 1;

    public static final String VALID_STATUS = "1";
    public static final int VALID_STATUS_FLG = 1;


    public static final boolean WORKFLOW_PROCESSINST_TASK_APPRV_YES = true;
    public static final boolean WORKFLOW_PROCESSINST_TASK_APPRV_NO = false;


    /**
     * 流程创建者
     */
    public static final String WORKFLOW_PROCESSINST_START_USRID = "startUserId";
    /**
     * 下一个流程处理者
     */
    public static final String WORKFLOW_PROCESSINST_NEXT_ASSIGNEE = "nextAssignee";

    /**
     * 流程对应的业务key,查询表单时的url
     */
    public static final String WORKFLOW_PROCESSINST_BIZ_KEY_URL = "processInstanceBusinessKeyURL";


    /**
     *  流程taks详情跟踪的键值对中的键 ：是否活跃流程（true 待办 ; false已办）
     */
    public static final String WORKFLOW_PROCESSINST_TASK_ACTIVITI = "currentActiviti";
    /**
     *  流程taks详情跟踪的键值对中的键 ：流程图开始的x坐标
     */
    public static final String WORKFLOW_PROCESSINST_TASK_GRH_X = "x";
    /**
     *  流程taks详情跟踪的键值对中的键 ：流程图开始的y坐标
     */
    public static final String WORKFLOW_PROCESSINST_TASK_GRH_Y = "y";
    /**
     *  流程taks详情跟踪的键值对中的键 ：流程图的宽度
     */
    public static final String WORKFLOW_PROCESSINST_TASK_GRH_WIDTH = "width";
    /**
     *  流程taks详情跟踪的键值对中的键 ：流程图的高度
     */
    public static final String WORKFLOW_PROCESSINST_TASK_GRH_HEIGHT = "height";
    /**
     * 流程相关参数的键
     */
    public static final String WORKFLOW_PROCESSINST_VARS_KEY ="vars";
    /**
     * 流程相关参数:task名称
     */
    public static final String WORKFLOW_PROCESSINST_VALS_TASK_TIP = "taskTip";
    /**
     * 流程相关参数:task描述
     */
    public static final String WORKFLOW_PROCESSINST_VALS_TASK_DESC = "taskDesc";

    /**
     * 流程相关参数:task处理人
     */
    public static final String WORKFLOW_PROCESSINST_VALS_TASK_ASSIGNEE = "taskAssignee";

    /**
     * 任务类型
     */
    public static final String WORKFLOW_PROCESSINST_VALS_TASK_TYPE = "taskType";
}
