/**
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.webapp.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jojo.biz.MenuBiz;
import com.jojo.biz.model.MenuBO;

/**
 * <summary>
 * 菜单基本操作，含菜单基本操作（CURD）和点击导航页面
 * </summary>
 *
 * @author jojo
 *
 */
@Controller
public class MenuController
{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MenuBiz menuBiz;

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
    public List<MenuBO> show(@RequestBody  MenuBO bo)
    {
        logger.info("menuId : [{}]", bo.getTheId());

        List<MenuBO> list = menuBiz.queryChildren(bo.getTheId());
        return list;
    }

    //ajax 示例
//  PrintWriter out = null;
//  String resultJson = "";
//  response.setContentType("text/html;charset=UTF-8");
//  response.setCharacterEncoding("UTF-8");
//  try
//  {
//      out = response.getWriter();
//      out.write(resultHtml);
//  }
//  catch (IOException e)
//  {
//      logger.error("parse html failed. exception info : [{}]", e);
//  }

}
