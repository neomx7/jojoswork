/**
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.webapp.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jojo.dal.common.postgre.domain.UserDO;
import com.jojo.util.common.CookieUtil;
import com.jojo.util.common.MD5Encrypt;
import com.jojo.web.common.authenticate.AuthenticationUtil;
import com.jojo.web.common.context.ContextHolder;
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


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@ModelAttribute("form") LogInOutForm form) {
        return form.hasLogined() ? getRedirectURL(form.getRedirectURL()) : "view/loginout/login";
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String submitLogin(@ModelAttribute("form") LogInOutForm form, HttpServletRequest request,
                              HttpServletResponse response) {
        if (form.hasLogined()) {
            return getRedirectURL(form.getRedirectURL());
        }

//        PunLoginInfo punLoginInfo = punLoginService.queryPunLoginInfoByLogUserNameAndPassword(
//            form.getUserId(), MD5Encrypt.encode(form.getPwd()));
        UserDO userDO = ContextHolder.getUserMap().get(form.getUserId());
        if (userDO == null || !userDO.isValid()) {
//            form.putError("login_error", "用户无效（用户无效或用户角色不存在），登录失败！");

            return "view/loginout/login";
        }
        boolean validUsr = false;
        if (userDO.getPwd() == null || form.getPwd() == null)
        {
            //TODO 用户密码为空
            return "view/loginout/login";
        }
        validUsr = (userDO != null) && userDO.isValid() && userDO.getPwd().equals((MD5Encrypt.encode(form.getPwd()))) ;
        if (!validUsr) {
//            form.putError("login_error", "用户名或者密码不正确，登录失败！");

            return "view/loginout/login";
        }

        // 登录成功
        CookieUtil cookieUtil = new CookieUtil(request, response);
        cookieUtil.setCookie(AuthenticationUtil.APP_CID,
                userDO.getUsrId() + ":" + userDO.getTheId());

        // 更新最后登录时间以及登陆IP
//        punLoginService.update(punLoginInfo.getId(), form.getContextIP(),
//            DateUtil.getCurrentLongDateTimeMs());
//
//        log(getOperateType(), punUser.getUserName(), "登录控台");
        System.out.println(getRedirectURL(form.getRedirectURL()));
        return getRedirectURL(form.getRedirectURL());

    }

    private String getRedirectURL(String redirectURL) {
        return "redirect:" + (StringUtils.isBlank(redirectURL) ? "/index" : redirectURL);
    }
}
