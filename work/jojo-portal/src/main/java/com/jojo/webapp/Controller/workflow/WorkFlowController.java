/**
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.webapp.Controller.workflow;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jojo.facade.workflow.WorkFlowExecutor;
import com.jojo.util.ui.vo.workflow.WorkFlowDefine;
import com.jojo.util.ui.vo.workflow.WorkFlowQuery;
import com.jojo.web.common.context.ContextHolder;

/**
 *
 * <summary>
 * [工作流处理]<br>
 * <br>
 * </summary>
 *
 * @author jojo
 *
 */
@Controller
public class WorkFlowController
{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/workflow/queryDefines")
    public List<WorkFlowDefine> queryDefines(@ModelAttribute("condition") WorkFlowQuery query)
    {
        //调用工作流服务获取列表
//        // Simple Service
//        TempConverter converter = ContextHolder.getBean("simpleGateway", TempConverter.class);
//        System.out.println(converter.fahrenheitToCelcius(68.0f));

        WorkFlowExecutor workFlowExecutor = (WorkFlowExecutor) ContextHolder.getBean("workFlowServiceProxy");
        List<?> result = workFlowExecutor.queryFlowDefines();
        System.out.println(result);

        return null;
    }

}
