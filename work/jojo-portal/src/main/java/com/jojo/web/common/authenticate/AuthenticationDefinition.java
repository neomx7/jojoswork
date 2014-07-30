/**
 *
 *汇付天下有限公司
 * Copyright (c) 2006-2013 ChinaPnR,Inc.All Rights Reserved.
 */
package com.jojo.web.common.authenticate;

import java.util.Set;

/**
 *
 * @author finley.yao
 * @version $Id: AuthenticationDefinition.java, v 0.1 2013-6-13 下午3:49:12 finley.yao Exp $
 */
public class AuthenticationDefinition {

    private boolean     needLogin = true;

    private String      uri;

    private Set<String> roles;

    /**
     * Getter method for property <tt>needLogin</tt>.
     *
     * @return property value of needLogin
     */
    public boolean isNeedLogin() {
        return needLogin;
    }

    /**
     * Setter method for property <tt>needLogin</tt>.
     *
     * @param needLogin value to be assigned to property needLogin
     */
    public void setNeedLogin(boolean needLogin) {
        this.needLogin = needLogin;
    }

    /**
     * Getter method for property <tt>uri</tt>.
     *
     * @return property value of uri
     */
    public String getUri() {
        return uri;
    }

    /**
     * Setter method for property <tt>uri</tt>.
     *
     * @param uri value to be assigned to property uri
     */
    public void setUri(String uri) {
        this.uri = uri;
    }

    /**
     * Getter method for property <tt>roles</tt>.
     *
     * @return property value of roles
     */
    public Set<String> getRoles() {
        return roles;
    }

    /**
     * Setter method for property <tt>roles</tt>.
     *
     * @param roles value to be assigned to property roles
     */
    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public boolean validRole(String role) {
        return roles != null && roles.contains(role);
    }

}
