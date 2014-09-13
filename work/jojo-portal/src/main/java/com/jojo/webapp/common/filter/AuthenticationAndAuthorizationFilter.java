/**
 *
 * 有限公司
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
import org.springframework.web.filter.OncePerRequestFilter;

import com.jojo.dal.common.postgre.domain.UserDO;
import com.jojo.util.common.CookieUtil;
import com.jojo.util.common.ExceptionUtil;
import com.jojo.util.common.IPUtil;
import com.jojo.web.common.authenticate.AuthenticationUtil;
import com.jojo.web.common.context.AppContext;
import com.jojo.web.common.context.AppContextHolder;
import com.jojo.web.common.context.ContextHolder;

/**
 * 认证授权拦截器，主要解决登录认证、功能授权 <br>
 * cookieUtil的作用只是记录用户登录过，至于用户当前最新的role和菜单是否有权限，需要每次filter时重新确认。<br>
 *
 *
 * @author jojo
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

        AppContext sc = AppContextHolder.get();
        if (sc != null)
        {
            return;
        }

        sc = getAppContext(request, response);
        AppContextHolder.setAppContext(sc);

        // webApplicationContext.getBean(arg0)
        try
        {

            // 通过验证
            if (AuthenticationUtil.hasAuthentication(sc.getUri(), sc.hasLogined(), sc.getUserId()))
            {
                filterChain.doFilter(request, response);
                return;
            }

            String redirectURL = uri;
            String encodeURL = URLEncoder.encode((redirectURL + (StringUtils.isBlank(request.getQueryString()) ? ""
                    : "?" + request.getQueryString())), "UTF-8");
            // 修改，如果 已登录&&没有菜单权限，则在当前页面提示；未登录，则直接转向重新登录页面。
            if (sc.hasLogined() && !sc.getUri().equals("/index"))
            {
                response.setContentType("text/html;charset=UTF-8");
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "unavailable URI: current User have no privilege 4 this url : [" + sc.getUri() + "]");
            }
            else if (sc.hasLogined())
            {
                response.sendRedirect(request.getContextPath() + "/index");
            }
            else
            {
                response.sendRedirect(request.getContextPath() + "/login?redirectURL=" + encodeURL);
            }

            // response.sendRedirect( ? request.getContextPath() + "/index" :
            // request.getContextPath() + "/login?redirectURL=" + encodeURL);
            return;
        }
        catch (Exception e)
        {
            logger.warn(e.getMessage(), e);
            // response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
            // URLEncoder.encode(ExceptionUtil.getSimpleExceptionStackTrace(e),
            // "UTF-8"));
            response.sendRedirect(request.getContextPath() + "/tip/exception?tip="
                    + URLEncoder.encode("当前用户没有该菜单权限，错误信息: " +  e.getMessage(), "UTF-8") + "&tipDesc="
                    + URLEncoder.encode(ExceptionUtil.getSimpleExceptionStackTrace(e), "UTF-8"));

        }
        finally
        {
            AppContextHolder.removeAppContext();
        }
    }

    /**
     *
     * <summary>
     * [登录的Controller已经设定cookie的格式为loginUsrId:usrRole(逗号分隔字符串)]<br>
     * <br>
     * </summary>
     *
     * @author jojo
     *
     * @param request
     * @param response
     * @return
     */
    private AppContext getAppContext(HttpServletRequest request, HttpServletResponse response)
    {
        AppContext sc = new AppContext();

        CookieUtil cookieUtil = new CookieUtil(request, response);
        final JOJOCid appCid = new JOJOCid(StringUtils.trimToEmpty(cookieUtil.getCookie(AuthenticationUtil.APP_CID)));

        if (appCid.isValid())
        {
            String loginUsrId = appCid.getUserId();
            sc.setUserId(appCid.getUserId());

            // 从内存中获取用户信息
            UserDO user = ContextHolder.getUserMap().get(appCid.getUserId());

            if (user != null && ContextHolder.isValidUsr(loginUsrId))
            {
                sc.setLogon(true);
                sc.setUserStatus(user.getStatus());
                sc.setUserName(user.getTheName());
            }
        }
        // sc.setUri(request.getRequestURI());
        String uri = request.getRequestURI();
        uri = uri.endsWith("/") ? uri.substring(0, uri.length() - 1) : uri;
        sc.setUri(uri.replaceFirst(request.getContextPath(), ""));
        sc.setIp(IPUtil.getIpAddr(request));

        return sc;
    }

    public static class JOJOCid
    {
        private final String[] jojoCidSplits;

        public JOJOCid(String cid)
        {
            jojoCidSplits = cid.split(":");
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
            return jojoCidSplits.length >= 2;
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
