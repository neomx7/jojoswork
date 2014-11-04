/**
 *
 * JOJO
 * Copyright (c) 2014,Inc.All Rights Reserved.
 */
package com.jojo.webapp.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jojo.biz.SysMgrBiz;
import com.jojo.dal.common.postgre.domain.OrgDO;
import com.jojo.dal.common.postgre.domain.UserDO;
import com.jojo.util.common.MD5Encrypt;
import com.jojo.util.pojo.DataRequest4Dept;
import com.jojo.util.pojo.DataRequest4DeptUser;
import com.jojo.util.pojo.DataResponse;
import com.jojo.webapp.form.DeptForm;
import com.jojo.webapp.form.UserForm;

/**
 * 系统管理
 * @author JOJO
 */
@Controller
public class SysmgrController extends BaseController {

    @Autowired
    private SysMgrBiz sysMgrBiz;

    /**
     *
     * <summary>
     * [进入修改个人密码]<br>
     * <br>
     * </summary>
     *
     * @author jojo
     *
     * @param form
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     */
    @RequestMapping(value = "/sysmgr/toChangePwd")
    public String toChangePwd(@ModelAttribute("form") UserForm form,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse){
        return "view/sysmgr/usr/edit-pwd";
    }

    @RequestMapping(value = "/sysmgr/changePwd")
    @ResponseBody
    public UserForm changePwd(@RequestBody UserForm form,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse){

        form.getErrors().clear();
        try
        {
            Map<String, Object> params = new HashMap<String, Object>(2);
            String loginUserName = getLoginUsrId(httpServletRequest, httpServletResponse);
            params.put("usrId", loginUserName);
            UserDO userDODB = sysMgrBiz.getUser(params);
            UserDO userDO = form.getUserDO();
            userDO.setUsrId(loginUserName);

            //密码字段为空，抛异常
            if (StringUtils.isBlank(form.getPwd()))
            {
                form.setResultCode(4);
                form.getErrors().put("result_error", "当前密码为空!");
                return form;
            }

            //密码是否一致，不一致直接抛出错误
            if (!userDODB.getPwd().equals(MD5Encrypt.encode(form.getPwd())))
            {
                form.setResultCode(4);
                form.getErrors().put("result_error", "当前密码不正确!");
                return form;
            } ;

            //新密码是否一致
            if (!form.getNewPwd().equals(form.getReNewPwd()))
            {
                form.setResultCode(4);
                form.getErrors().put("result_error", "新密码两次输入不一致!");
                return form;
            }

            //修改用户密码  form.getUsrId().trim()+
            userDO.setPwd(MD5Encrypt.encode(form.getNewPwd().trim()));
            sysMgrBiz.saveUser(userDO);
            form.setResultCode(1);
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(),e);
            form.setResultCode(4);
            form.getErrors().put("result_error", e.getMessage());
        }

        return form;
    }

    /**
     *
     * <summary>
     * [进入编辑用户信息]<br>
     * <br>
     * </summary>
     *
     * @author jojo
     *
     * @return
     */
    @RequestMapping(value = "/sysmgr/toEditUsr")
    public String toEditUsr(@ModelAttribute("form") UserForm form,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse){

        Map<String, Object> params = new HashMap<String, Object>(2);
        String loginUserName = getLoginUsrId(httpServletRequest, httpServletResponse);
        params.put("usrId", loginUserName);
        UserDO userDODB = sysMgrBiz.getUser(params);
        UserDO userDO = form.getUserDO();
        form.getErrors().clear();
        try
        {
            userDO.setTheId(userDODB.getTheId());
            userDO.setTheName(userDODB.getTheName());
            userDO.setTheRemark(userDODB.getTheRemark());
            userDO.setEmail(userDODB.getEmail());
            userDO.setMobile(userDODB.getMobile());
            userDO.setOperId(userDODB.getOperId());
            userDO.setPwd(userDODB.getPwd());
            userDO.setSex(userDODB.getSex());
            userDO.setTel(userDODB.getTel());
            userDO.setUsrId(userDODB.getUsrId());
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(),e);
            form.setResultCode(4);
            form.getErrors().put("result_error", e.getMessage());
        }

        return "view/sysmgr/usr/edit-usr";
    }

    @RequestMapping(value = "/sysmgr/saveUsr")
    @ResponseBody
    public UserForm saveUsr(@RequestBody UserForm form,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse){

        form.getErrors().clear();
        try
        {
            //保存
            UserDO userDO = form.getUserDO();
            userDO.setUpdUserId(getLoginUsrId(httpServletRequest, httpServletResponse));
            userDO.setTheId(form.getTheId());
            userDO.setTheName(form.getTheName());
            userDO.setTheRemark(form.getTheRemark());
            userDO.setEmail(form.getEmail());
            userDO.setMobile(form.getMobile());
//        userDO.setOperId(form.getOperId());
            userDO.setPwd(form.getPwd());
            userDO.setSex(form.getSex());
            userDO.setTel(form.getTel());
            userDO.setUsrId(form.getUsrId());
            sysMgrBiz.saveUser(userDO);
            form.setResultCode(1);
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(),e);
            form.setResultCode(4);
            form.getErrors().put("result_error", e.getMessage());
        }

        return form;
//        return "redirect:/sysmgr/toEditUsr";
    }

    /**
     *
     * <summary>
     * [进入部门树页面]<br>
     * <br>
     * </summary>
     *
     * @author jojo
     *
     * @param form
     * @return
     */
    @RequestMapping(value = "/sysmgr/toShowDeptTree")
    public String toShowDeptTree(@ModelAttribute("form") DeptForm form) {
        logger.info("match url 4 '/sysmgr/toShowDeptTree'");
        return "view/sysmgr/dept/dept-tree";

    }


    /**
     *
     * <summary>
     * [展示部门树]<br>
     * <br>
     * </summary>
     *
     * @author jojo
     *
     * @param form
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     */
    @RequestMapping(value = "/sysmgr/showDeptTree")
    @ResponseBody
    public  List<OrgDO> queryDepts(@ModelAttribute("form")DeptForm form,HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse){
        logger.info("match url 4 '/sysmgr/showDeptTree'");
        if (StringUtils.isBlank(form.getCurrendNodeCode()))
        {
            throw new RuntimeException("无法展示：当前节点的code为空。");
        }
        DataRequest4Dept request = new DataRequest4Dept();
        request.setParentCode(form.getCurrendNodeCode());
        request.setSubCodeLength(form.getCurrendNodeCode().trim().length()*2);
        return findJsTreeResult(request, OrgDO.class, sysMgrBiz, "queryDeptsByParentCode4JsTree");
    }

    /**
     *
     * <summary>
     * [查询部门用户]<br>
     * <br>
     * </summary>
     *
     * @author jojo
     *
     * @param form
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     */
    @RequestMapping(value = "/sysmgr/queryDeptUsers")
    public String queryDeptUsers(@ModelAttribute("form")DeptForm form,HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse){
        logger.info("match url 4 '/sysmgr/queryDeptUsers'");
        DataRequest4DeptUser request = new DataRequest4DeptUser();
        request.setDeptCode(form.getCurrendNodeCode());
        DataResponse<UserDO> dataResponse = findResult(request, UserDO.class, sysMgrBiz, "queryDeptUsers");
        form.setUserDOs(dataResponse.getRows());

        return "view/sysmgr/dept/dept-usr";
    }
}