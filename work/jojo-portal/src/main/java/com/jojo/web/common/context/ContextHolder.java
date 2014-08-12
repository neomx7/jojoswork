/**
 *
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.web.common.context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;

import com.jojo.dal.common.postgre.domain.PrivilegeDO;
import com.jojo.dal.common.postgre.domain.ResourceDO;
import com.jojo.dal.common.postgre.domain.RoleDO;
import com.jojo.dal.common.postgre.domain.UserDO;

/**
 * 系统的上下文保持类，包含了系统管理的实例内容.<br>
 * 其中部门、用户、角色、权限、菜单这些作为内容对象存在，系统启动时，由 {@link SystemMgrCtxHolder#init()} 存入，<br>
 * 在其被变更时，由 {@link SystemMgrCtxHolder#init()} 进行更新内存内容。<br>
 *
 * @author jojo
 */
public class ContextHolder
{

    /**
     * 防止实例化
     */
    private ContextHolder()
    {

    }

    public static ApplicationContext applicationContext;

    public static ServletContext servletContext;

    private static final Map<String, UserDO> USER_MAP = new HashMap<String, UserDO>(200);
    // private static final Map<String, List<OrgUserDO>> ORGUSER_MAP = new
    // HashMap<String, List<OrgUserDO>>(200);
    private static final Map<String, List<RoleDO>> USR2ROLE_MAP = new HashMap<String, List<RoleDO>>(200);
    private static final Map<String, List<PrivilegeDO>> Role2PRV_MAP = new HashMap<String, List<PrivilegeDO>>(200);
    private static final Map<String, List<ResourceDO>> Usr2RESOURCE_MAP = new HashMap<String, List<ResourceDO>>(200);

    /**
     *
     * <summary>
     * [根据用户登录名判断用户状态是否有效]<br>
     * <br>
     * </summary>
     *
     * @author jojo
     *
     * @return
     */
    public static boolean isValidUsr(String loginUsrId)
    {
        UserDO userDO = getUserMap().get(loginUsrId);
        boolean validRoleExists = false;
        if (userDO != null)
        {
            // 得到用户的role，只要还有一个是有效的，且用户也是有效的，则用户有效
            List<RoleDO> roleDOs = getUsr2RoleMap().get(loginUsrId);
            if (roleDOs != null)
            {
                for (RoleDO roleDO : roleDOs)
                {
                    if (roleDO.isValid())
                    {
                        validRoleExists = true;
                        break;
                    }
                }
            }

            return userDO.isValid() && validRoleExists;

        }
        return false;
    }

    public static boolean isValidUri(String loginUsrId, String uri)
    {
        List<ResourceDO> resourceDOs = getUsr2ResourceMap().get(loginUsrId);
        boolean valid = false;
        if (resourceDOs != null)
        {
            for (ResourceDO resourceDO : resourceDOs)
            {
                if (resourceDO.isValid()&&resourceDO.getUri().equals(uri))
                {
                    valid = true;
                    break;
                }
            }
        }
        return valid;
    }

    /**
     *
     * <summary>
     * [从系统上下文中获取spring的bean对象]<br>
     * <br>
     * </summary>
     *
     * @author jojo
     *
     * @param beanName
     * @return
     */
    public static Object getBean(String beanName)
    {
        return applicationContext.getBean(beanName);
    }

    public static <T> T getBean(String name, Class<T> requiredType)
    {
        return applicationContext.getBean(name, requiredType);
    }

    public static <T> T getBean(Class<T> requiredType)
    {
        return applicationContext.getBean(requiredType);
    }

    public static ApplicationContext getApplicationContext()
    {
        return applicationContext;
    }

    public static ServletContext getServletContext()
    {
        return servletContext;
    }

    public static void setApplicationContext(ApplicationContext applicationContext)
    {
        ContextHolder.applicationContext = applicationContext;
    }

    public static void setServletContext(ServletContext servletContext)
    {
        ContextHolder.servletContext = servletContext;
    }

    // public static Map<String, List<OrgUserDO>> getOrguserMap()
    // {
    // return ORGUSER_MAP;
    // }

    public static Map<String, List<RoleDO>> getUsr2RoleMap()
    {
        return USR2ROLE_MAP;
    }

    public static Map<String, List<PrivilegeDO>> getRole2PrvMap()
    {
        return Role2PRV_MAP;
    }

    public static Map<String, List<ResourceDO>> getUsr2ResourceMap()
    {
        return Usr2RESOURCE_MAP;
    }

    public static Map<String, UserDO> getUserMap()
    {
        return USER_MAP;
    }

}
