/**
 *
 * JOJO
 * Copyright (c) 2014,Inc.All Rights Reserved.
 */
package com.jojo.webapp.Controller;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractRefreshableApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jojo.web.common.context.ContextHolder;
import com.jojo.web.common.context.SystemMgrCtxHolder;
import com.jojo.webapp.form.BaseForm;

/**
 * 运维使用的菜单
 * @author JOJO
 */
@Controller
public class OperationController extends BaseController {

    @Resource(name="systemMgrCtxHolder")
    private SystemMgrCtxHolder systemMgrCtxHolder;

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
    @RequestMapping(value = "/operation/reloadPrivilege")
    public String reloadPrivilege(@ModelAttribute("form") BaseForm form) {
        systemMgrCtxHolder.init();
        return "view/operation/privilege";

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
    @RequestMapping(value = "/operation/reloadContext")
    public String reloadContext(@ModelAttribute("form") BaseForm form) {
        ApplicationContext context = ContextHolder.getApplicationContext();
        if (context != null)
        {
            ((AbstractRefreshableApplicationContext) context).refresh();
        }
        return "view/operation/context";

    }

}
