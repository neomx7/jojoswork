/**
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.webapp.form;

import java.util.List;

import com.jojo.dal.common.postgre.domain.UserDO;


/**
 * <summary>
 * 部门表单
 * </summary>
 *
 * @author jojo
 *
 */
public class DeptForm extends BaseForm
{

    /**   */
    private static final long serialVersionUID = -5435128763540539644L;


    private String currendNodeCode;

    private List<UserDO> userDOs;

    public String getCurrendNodeCode()
    {
        return currendNodeCode;
    }


    public void setCurrendNodeCode(String currendNodeCode)
    {
        this.currendNodeCode = currendNodeCode;
    }


    public List<UserDO> getUserDOs()
    {
        return userDOs;
    }


    public void setUserDOs(List<UserDO> userDOs)
    {
        this.userDOs = userDOs;
    }

}
