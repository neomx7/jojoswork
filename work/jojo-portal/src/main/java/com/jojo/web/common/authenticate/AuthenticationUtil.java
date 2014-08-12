/**
 *
 * JOJO
 */
package com.jojo.web.common.authenticate;

import com.jojo.web.common.context.ContextHolder;

/**
 *
 * @author jojo
 */
public class AuthenticationUtil
{

    public static final String USER_ROLE_ADMINISTRATOR = "administrator";
    public static final String USER_ID_ADMINISTRATOR = "administrator";
    public static final String APP_CID = "app_cid";

    public static boolean hasAuthentication(String uri, boolean isLogon, String loginUsrId)
    {

        // 管理员或则无权限配置，权限直接通过
        if (USER_ID_ADMINISTRATOR.equals(loginUsrId))
        {
            return true;
        }

        // 需要登录，登录并且uri有权限合法，则鉴权通过，否则鉴权失败；
        return (isLogon) && ContextHolder.isValidUri(loginUsrId, uri);

    }

}
