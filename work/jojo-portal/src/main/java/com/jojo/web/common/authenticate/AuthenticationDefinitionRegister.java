/**
 *
 *JOJO
 */
package com.jojo.web.common.authenticate;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author finley.yao
 * @version $Id: AuthenticationDefinitionRegister.java, v 0.1 2013-6-13 下午4:00:19 finley.yao Exp $
 */
public class AuthenticationDefinitionRegister {

    private static Map<String, AuthenticationDefinition> REGISTER = new ConcurrentHashMap<String, AuthenticationDefinition>();

    public static void regist(String key, AuthenticationDefinition authenticationDefinition) {
        REGISTER.put(key, authenticationDefinition);
    }

    /**
     * 从角色和菜单信息中加载权限定义
     *
     * @param rolesByLinkMap
     */
    public static void loadAuthenticationDefinition(Map<String, Set<String>> rolesByLinkMap) {
        if (rolesByLinkMap == null || rolesByLinkMap.isEmpty()) {
            return;
        }

        Map<String, AuthenticationDefinition> register = new ConcurrentHashMap<String, AuthenticationDefinition>();
        for (String key : rolesByLinkMap.keySet()) {
            AuthenticationDefinition authenticationDefinition = new AuthenticationDefinition();
            authenticationDefinition.setNeedLogin(true);
            authenticationDefinition.setRoles(rolesByLinkMap.get(key));
            authenticationDefinition.setUri(key);

            register.put(key, authenticationDefinition);
        }

        REGISTER = register;
    }

    public static AuthenticationDefinition get(String key) {
        return REGISTER.get(key);
    }

}
