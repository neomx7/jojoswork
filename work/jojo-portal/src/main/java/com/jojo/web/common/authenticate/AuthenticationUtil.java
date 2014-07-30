/**
 *
 *JOJO
 */
package com.jojo.web.common.authenticate;

import java.util.List;

/**
 *
 * @author finley.yao
 * @version $Id: AuthenticationUtil.java, v 0.1 2013-7-30 下午1:42:11 finley.yao Exp $
 */
public class AuthenticationUtil {

    public static final String USER_ROLE_ADMINISTRATOR = "administrator";
    public static final String SCPPUN_CID              = "scppun_cid";

    public static boolean hasAuthentication(String uri, boolean isLogon, String userRole) {

        AuthenticationDefinition authentication = AuthenticationDefinitionRegister.get(uri);

        // 管理员或则无权限配置，权限直接通过
        if (USER_ROLE_ADMINISTRATOR.equals(userRole) || null == authentication) {
            return true;
        }

        // 需要登录，登录并且角色合法，则权限通过，否则权限失败；不需要登录，角色合法，则权限通过，否则权限失败
        return (authentication.isNeedLogin() ? isLogon : true)
               && authentication.validRole(userRole);

    }


}
