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

import com.jojo.webapp.form.LogInOutForm;

/**
 * <summary>
 * 登入登出
 * </summary>
 *
 * @author jojo
 *
 */
@Controller
public class LogInOutController
{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    // , method = RequestMethod.GET
    @RequestMapping(value = "/login")
    public String toLogin(@ModelAttribute("form") LogInOutForm form)
    {
        return "view/loginout/login";
    }

}
