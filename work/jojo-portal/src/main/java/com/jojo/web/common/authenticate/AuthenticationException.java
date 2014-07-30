/**
 *
 *JOJO
 */
package com.jojo.web.common.authenticate;

/**
 *
 * @author finley.yao
 * @version $Id: AuthenticationException.java, v 0.1 2013-6-13 下午4:58:36 finley.yao Exp $
 */
public class AuthenticationException extends RuntimeException {

    /**  */
    private static final long serialVersionUID = -8225379592025466134L;

    public AuthenticationException() {
        super();
    }

    public AuthenticationException(String message) {
        super(message);
    }

    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }

}
