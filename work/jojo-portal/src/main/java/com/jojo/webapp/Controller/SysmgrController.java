/**
 *
 * JOJO
 * Copyright (c) 2014,Inc.All Rights Reserved.
 */
package com.jojo.webapp.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jojo.biz.SysMgrBiz;
import com.jojo.dal.common.postgre.domain.OrgDO;
import com.jojo.dal.common.postgre.domain.UserDO;
import com.jojo.util.pojo.DataRequest4Dept;
import com.jojo.util.pojo.DataRequest4DeptUser;
import com.jojo.util.pojo.DataResponse;
import com.jojo.webapp.form.DeptForm;

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
    public String auth(@ModelAttribute("form") DeptForm form) {
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