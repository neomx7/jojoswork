/**
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.util.pojo;

/**
 * <summary>
 * 部门信息查询
 * </summary>
 *
 * @author jojo
 *
 */
public class DataRequest4DeptUser extends DataRequest {

    /**   */
    private static final long serialVersionUID = 6897407012995256274L;

    private String deptCode;

    public String getDeptCode()
    {
        return deptCode;
    }

    public void setDeptCode(String deptCode)
    {
        this.deptCode = deptCode;
    }

}
