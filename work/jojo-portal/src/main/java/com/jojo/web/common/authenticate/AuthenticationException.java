/**
 *
 *JOJO
 */
package com.jojo.web.common.authenticate;

/**
 *
 * @author JOJO
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
