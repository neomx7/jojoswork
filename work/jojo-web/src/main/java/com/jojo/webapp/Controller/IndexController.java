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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jojo.webapp.form.IndexForm;

/**
 * <summary>
 * 
 * </summary>
 * 
 * @author jojo
 * 
 */
@Controller
public class IndexController
{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    //, method = RequestMethod.GET
    @RequestMapping(value = "/index" , method = RequestMethod.GET)
    public String test(@ModelAttribute("form") IndexForm form)
    {
        logger.info("进Action了");
        return "index";  // 设置返回页面，这里对应 /WEB-INF/view 目录下的 index.ftl 文件
    }
}
