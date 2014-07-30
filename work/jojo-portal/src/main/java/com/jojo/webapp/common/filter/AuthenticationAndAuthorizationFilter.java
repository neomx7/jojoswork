/**
 *
 * 汇付天下有限公司
 *
 */
package com.jojo.webapp.common.filter;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.OncePerRequestFilter;

import com.jojo.util.common.ExceptionUtil;

/**
 * 认证授权拦截器，主要解决登录认证、功能授权
 *
 * @author finley.yao
 * @version $Id: AuthenticationAndAuthorizationFilter.java, v 0.1 2013-6-13
 *          下午3:15:13 finley.yao Exp $
 */
public class AuthenticationAndAuthorizationFilter extends OncePerRequestFilter
{

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationAndAuthorizationFilter.class);

    private static final Set<String> notFilters = new HashSet<String>(40);

    private String notFilter;

    public void init()
    {
        String[] strs = notFilter.split(",");
        for (String string : strs)
        {
            notFilters.add(string);
        }
    }


    /**
     * @see org.springframework.web.filter.GenericFilterBean#initFilterBean()
     */
    @Override
    protected void initFilterBean() throws ServletException
    {

    }

    /**
     * @see org.springframework.web.filter.OncePerRequestFilter#doFilterInternal(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse, javax.servlet.FilterChain)
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException
    {

        if (getNotfilters().isEmpty())
        {
            init();
        }
        // 请求的uri
        String uri = request.getRequestURI();

        // 是否过滤
        boolean doFilter = true;
        for (String noFStr : notFilters)
        {
            if (uri.indexOf(noFStr) >= 0)
            {
                // 如果uri中包含不过滤的uri，则不进行过滤
                doFilter = false;
                break;
            }
        }

        if (!doFilter)
        {
            filterChain.doFilter(request, response);
            return;
        }

        WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
        try
        {

            uri = uri.endsWith("/") ? uri.substring(0, uri.length() - 1) : uri;
            // TODO 通过验证
            // if (AuthenticationUtil.hasAuthentication(uri, sc.isLogon(),
            // sc.getUserRole())) {
            // filterChain.doFilter(request, response);
            // return;
            // }

            String redirectURL = uri;
            String encodeURL = URLEncoder.encode((redirectURL + (StringUtils.isBlank(request.getQueryString()) ? ""
                    : "?" + request.getQueryString())), "UTF-8");
//
            response.sendRedirect(isLogin() ?  "/index" : request.getContextPath() + "/login?redirectURL=" + encodeURL);
            return;
        }
        catch (Exception e)
        {
            logger.warn(e.getMessage(), e);

            response.sendRedirect(request.getContextPath() +"/exception?tip=" + URLEncoder.encode(e.getMessage(), "UTF-8")
                    + "&tipDesc=" + URLEncoder.encode(ExceptionUtil.getSimpleExceptionStackTrace(e), "UTF-8"));

        }
        finally
        {
        }
    }

    private boolean isLogin()
    {
        // TODO Auto-generated method stub
        return false;
    }

    // private ScppunContext getScppunContext(HttpServletRequest request,
    // HttpServletResponse response) {
    // ScppunContext sc = new ScppunContext();
    //
    // CookieUtil cookieUtil = new CookieUtil(request, response);
    // final JOJOCid scppunCid = new JOJOCid(StringUtils.trimToEmpty(cookieUtil
    // .getCookie(AuthenticationUtil.SCPPUN_CID)));
    //
    // if (scppunCid.isValid()) {
    // sc.setUserId(scppunCid.getUserId());
    // sc.setUserRole(scppunCid.getUserRole());
    //
    // PunUserService punUserService = (PunUserService) ContextHolder
    // .getBean("punUserServiceImpl");
    // PunUser punUser =
    // punUserService.queryPunUserByUserId(scppunCid.getUserId());
    //
    // if (punUser != null && punUser.isValidUser()) {
    // sc.setLogon(true);
    // sc.setUserStatus(punUser.getStatus());
    // sc.setUserName(punUser.getUserName());
    //
    // // 非系统管理员，初始化角色权限
    // if (!punUser.isAdministrator()) {
    // MenuService menuService = (MenuService) ContextHolder
    // .getBean("menuServiceImpl");
    // sc.setUris(menuService.findMenuLinksByIds(punUser.getRole().getMenuIds()));
    // }
    // }
    // }
    // sc.setUri(request.getRequestURI());
    // sc.setIp(IPUtil.getIpAddr(request));
    //
    // return sc;
    // }

    public static class JOJOCid
    {
        private final String[] jojoCidSplits;

        public JOJOCid(String scppunCid)
        {
            jojoCidSplits = scppunCid.split(":");
        }

        public String getUserId()
        {
            return jojoCidSplits[0];
        }

        public String getUserRole()
        {
            return jojoCidSplits[1];
        }

        public boolean isValid()
        {
            return jojoCidSplits.length == 2;
        }

    }

    public String getNotFilter()
    {
        return notFilter;
    }


    public void setNotFilter(String notFilter)
    {
        this.notFilter = notFilter;
    }


    public static Set<String> getNotfilters()
    {
        return notFilters;
    }

}
