/**
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.webapp.Controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jojo.biz.MenuBiz;
import com.jojo.util.biz.bo.MenuBO;
import com.jojo.web.common.context.SystemMgrCtxHolder;

/**
 * <summary>
 * 菜单基本操作，含菜单基本操作（CURD）和点击导航页面
 * </summary>
 *
 * @author jojo
 *
 */
@Controller
public class MenuController extends BaseController
{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MenuBiz menuBiz;

    @Resource(name = "systemMgrCtxHolder")
    private SystemMgrCtxHolder systemMgrCtxHolder;

    /**
     *
     * <summary>
     * <p>
     * 点击top栏的上层菜单,页面刷新 top 栏目的二级菜单
     * </p>
     * </summary>
     *
     * @author jojo
     *
     * @param form
     * @return
     */
    @RequestMapping(value = "/menu/show", method = RequestMethod.POST)
    @ResponseBody
    public List<MenuBO> show(@RequestBody MenuBO bo, HttpServletRequest request, HttpServletResponse response)
    {
        logger.info("menuId : [{}],menuCode : [{}]", new Object[] { bo.getTheId(), bo.getDictCode() });

        List<MenuBO> list = systemMgrCtxHolder.getSubMenus4NextLv(bo.getTheId(), bo.getDictCode(),getLoginUsrId(request, response));
        // menuBiz.queryChildren(bo.getTheId(), bo.getDictCode());
        return list;
    }

    // ajax 示例
    // PrintWriter out = null;
    // String resultJson = "";
    // response.setContentType("text/html;charset=UTF-8");
    // response.setCharacterEncoding("UTF-8");
    // try
    // {
    // out = response.getWriter();
    // out.write(resultHtml);
    // }
    // catch (IOException e)
    // {
    // logger.error("parse html failed. exception info : [{}]", e);
    // }

}
