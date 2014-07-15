/**
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.webapp.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jojo.util.pojo.ProcessTaskForm;

/**
 * <summary>
 * demo的 jqGridList 展示
 * </summary>
 *
 * @author jojo
 *
 */
@Controller
public class DemoListController
{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     *
     * <summary>
     * <p>
     * 点击左侧边栏的3级菜单,显示内容页面
     * </p>
     * </summary>
     *
     * @author jojo
     *
     * @return
     */
    @RequestMapping(value = "/demo/toList")
    public String toList()
    {
        logger.info("match url 4 '/demo/toList'");
     // 设置返回页面，这里对应 /WEB-INF/ 目录下的 {0}.ftl 文件
        return "view/demo-list";
    }



    @RequestMapping(value = "/demo/toCreateApplyList")
    public String toCreateApplyList()
    {
        logger.info("match url 4 '/demo/toCreateApplyList'");
     // 设置返回页面，这里对应 /WEB-INF/ 目录下的 {0}.ftl 文件
        return "view/createApply-list";
    }

    @RequestMapping(value = "/demo/toMyTaskList")
    public String toMyTaskList()
    {
        logger.info("match url 4 '/demo/toMyTaskList'");
     // 设置返回页面，这里对应 /WEB-INF/ 目录下的 {0}.ftl 文件
        return "view/todoTaskList-list";
    }

    @RequestMapping(value = "/demo/toProcessTask")
    public String toProcessTaskList(@RequestBody ProcessTaskForm form,@ModelAttribute("form") ProcessTaskForm model)
    {
        logger.info("match url 4 '/demo/toProcessTask'");
        model.setTaskId(form.getTaskId());
     // 设置返回页面，这里对应 /WEB-INF/ 目录下的 {0}.ftl 文件
        return "view/processTask";
    }
}
