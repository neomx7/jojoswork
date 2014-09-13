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
public class JOJOConstants
{
    /**
     * 工作流的spring httpinvoker 的bean名称
     */
    public static final String WORKFLOW_SERVICE = "workFlowServiceProxy";


    public static final Integer WORKFLOW_TASKMODE_TODO = 1;
    public static final Integer WORKFLOW_TASKMODE_DOING = 1;
    public static final Integer WORKFLOW_TASKMODE_TDONE = 1;

    public static final String SESSION_KEY_LOGINUSER = "loginUser";

    public static final String COMMA = ",";
    public static final String UNDERLINE = "_";

    public static final int NUM_ONE = 1;

    public static final String VALID_STATUS = "1";
    public static final int VALID_STATUS_FLG = 1;

    /**
     * 下一个流程处理者
     */
    public static final String WORKFLOW_PROCESSINST_NEXT_ASSIGNEE = "nextAssignee";

    /**
     * 流程对应的业务key,查询表单时的url
     */
    public static final String WORKFLOW_PROCESSINST_BIZ_KEY_URL = "processInstanceBusinessKeyURL";

}
