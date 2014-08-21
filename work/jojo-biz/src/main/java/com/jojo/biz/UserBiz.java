/**
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.biz;

import java.util.List;
import java.util.Map;

import com.jojo.util.biz.bo.UserBO;

/**
 * <summary>
 *  用户的业务服务
 * </summary>
 *
 * @author jojo
 *
 */
public interface UserBiz
{
    /**
    *
    * <summary>
    * <p>根据用户信息 得到用户列表</p>
    * 参数可用的key- value-
    * 1.
    * </summary>
    *
    * @author jojo
    *
    * @param params
    * @return
    */
   public List<UserBO> queryUsers(Map<String, Object> params);
}
