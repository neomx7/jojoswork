/**
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.webapp.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jojo.biz.UserBiz;
import com.jojo.util.biz.bo.AutoCompleteVO;
import com.jojo.util.biz.bo.UserBO;

/**
 * <summary>
 * 人员基本操作，包括功能：
 * 1.根据姓名模糊查询用户信息
 * </summary>
 *
 * @author jojo
 *
 */
@Controller
public class UserController extends BaseController
{

    @Autowired
    private UserBiz userBiz;




    /**
     *
     * <summary>
     * <p>
     * 根据用户名字(不是登录名)模糊查询用户列表
     * </p>
     * </summary>
     *
     * @author jojo
     *
     *
     * @param form
     * @return
     */
    @RequestMapping(value = "/user/search", method = RequestMethod.GET)
    @ResponseBody
    public List<AutoCompleteVO> search(@RequestBody UserBO bo)
    {
        logger.info("get userName : [{}]", new Object[] { bo.getUserName() });

        if (StringUtils.isBlank(bo.getUserName()))
        {
            return null;
        }

        Map<String, Object> params = new HashMap<String, Object>(10);
        params.put("userName", bo.getUserName());
        List<UserBO> bos = userBiz.queryUsers(params);
        List<AutoCompleteVO> list = new ArrayList<AutoCompleteVO>(bos.size());

        // List<String> list = new ArrayList<String>(bos.size());
         for (UserBO userBO : bos)
         {
             AutoCompleteVO vo = new AutoCompleteVO();
             vo.setId(userBO.getUsrId());
             vo.setLabel(userBO.getTheName());
             vo.setValue(userBO.getTheName());
             list.add(vo);
         }
        return list;
    }

    @RequestMapping(value = "/user/search", method = RequestMethod.POST)
    @ResponseBody
    public List<AutoCompleteVO> query(@RequestBody UserBO bo)
    {
        logger.info("post userName : [{}]", new Object[] { bo.getUserName() });

        if (StringUtils.isBlank(bo.getUserName()))
        {
            return null;
        }

        Map<String, Object> params = new HashMap<String, Object>(10);
        params.put("userName", bo.getUserName());
        List<UserBO> bos = userBiz.queryUsers(params);
        List<AutoCompleteVO> list = new ArrayList<AutoCompleteVO>(bos.size());

        // List<String> list = new ArrayList<String>(bos.size());
         for (UserBO userBO : bos)
         {
             AutoCompleteVO vo = new AutoCompleteVO();
             vo.setId(userBO.getUsrId());
             vo.setLabel(userBO.getTheName());
             vo.setValue(userBO.getTheName());
             list.add(vo);
         }
        return list;
    }

    // @RequestMapping(value = "/user/search")
    // @ResponseBody
    // public List<UserBO> search(String userName, HttpServletRequest request,
    // HttpServletResponse response)
    // {
    // logger.info("userName : [{}]", new Object[] { userName });
    //
    // if (StringUtils.isBlank(userName))
    // {
    // return null;
    // }
    //
    // Map<String, Object> params = new HashMap<String, Object>(10);
    // params.put("userName", userName);
    // List<UserBO> bos = userBiz.queryUsers(params);
    //
    // return bos;
    // }
}
