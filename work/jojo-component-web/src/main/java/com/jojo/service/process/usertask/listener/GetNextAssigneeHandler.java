///**
// * JOJO
// *
// * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
// */
//package com.jojo.service.process.usertask.listener;
//
//import org.activiti.engine.RuntimeService;
//import org.activiti.engine.delegate.DelegateTask;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.jojo.util.constants.JOJOConstants;
//
///**
// * <summary>/
// * [分派下一个节点的任务负责人]<br>
// * <br>
// * </summary>
// *
// * @author jojo
// *
// */
//public class GetNextAssigneeHandler extends TaskHandler
//{
//
//    /**   */
//    private static final long serialVersionUID = 6504653808165347949L;
//
//    @Override
//    public void notify(DelegateTask delegateTask)
//    {
////        // 获取存储在流程实例中的nextAssignee变量的值
////        String next = (String) runtimeService.getVariable(
////                delegateTask.getExecutionId(), JOJOConstants.NEXT_ASSIGNEE);
//
//
//        // 设置节点的下个执行人
//        delegateTask.setAssignee(next);
//    }
//
//}
