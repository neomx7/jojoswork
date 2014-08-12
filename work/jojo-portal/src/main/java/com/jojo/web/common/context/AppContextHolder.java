/**
 *
 * JOJO
 * Copyright (c) 2014-  .All Rights Reserved.
 */
package com.jojo.web.common.context;

/**
 *
 * @author jojo
 */
public class AppContextHolder {

    private static final ThreadLocal<AppContext> APP_CONTEXT_HOLDER = new ThreadLocal<AppContext>();

    public AppContextHolder() {
    }

    public static AppContext get() {
        return APP_CONTEXT_HOLDER.get();
    }

    public static void setScppunContext(AppContext context) {
        APP_CONTEXT_HOLDER.set(context);
    }

    public static void removeScppunContext() {
        APP_CONTEXT_HOLDER.set(null);
    }
}
