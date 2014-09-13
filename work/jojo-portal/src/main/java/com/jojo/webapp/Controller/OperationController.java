/**
 *
 * JOJO
 * Copyright (c) 2014,Inc.All Rights Reserved.
 */
package com.jojo.webapp.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jojo.webapp.form.BaseForm;

/**
 * 运维使用的菜单
 * @author JOJO
 */
@Controller
public class OperationController extends BaseController {

    /**
     *
     * <summary>
     * [重载系统权限数据]<br>
     * <br>
     * </summary>
     *
     * @author jojo
     *
     * @param form
     * @return
     */
    @RequestMapping(value = "/operation/reloadPrivilege", method = RequestMethod.GET)
    public String reloadPrivilege(@ModelAttribute("form") BaseForm form) {

        return "WEB-INF/operation/privilege";

    }

    /**
     *
     * <summary>
     * [重载系统上下文]<br>
     * <br>
     * </summary>
     *
     * @author jojo
     *
     * @param form
     * @return
     */
    @RequestMapping(value = "/operation/reloadContext", method = RequestMethod.GET)
    public String reloadContext(@ModelAttribute("form") BaseForm form) {

        return "WEB-INF/operation/context";

    }

}
