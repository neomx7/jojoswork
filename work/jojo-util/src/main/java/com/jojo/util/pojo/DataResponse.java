/**
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.util.pojo;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

/**
 * <summary>
 *
 * </summary>
 *
 * @author jojo
 *
 */
public class DataResponse <T> {

    //需要显示的数据集
    private List<T> rows;

    //每页显示数量
    private int page;

    //数据总数
    private int records;

    //可显示的页数
    private int total;

    //自定义数据
    private Map<String, Object> userdata;

    /**
     * 返回结果编号，取值为 javax.servlet.http.HttpServletResponse 的状态码
     */
    private int status = HttpServletResponse.SC_OK;
    /**
     * 提示消息概要
     */
    private String tip;
    /**
     * 提示消息详细
     */
    private String tipDesc;


    public List<T> getRows()
    {
        return rows;
    }

    public void setRows(List<T> rows)
    {
        this.rows = rows;
    }

    public int getPage()
    {
        return page;
    }

    public void setPage(int page)
    {
        this.page = page;
    }

    public int getRecords()
    {
        return records;
    }

    public void setRecords(int records)
    {
        this.records = records;
    }

    public int getTotal()
    {
        return total;
    }

    public void setTotal(int total)
    {
        this.total = total;
    }

    public Map<String, Object> getUserdata()
    {
        return userdata;
    }

    public void setUserdata(Map<String, Object> userdata)
    {
        this.userdata = userdata;
    }

    public String getTip()
    {
        return tip;
    }

    public void setTip(String tip)
    {
        this.tip = tip;
    }

    public String getTipDesc()
    {
        return tipDesc;
    }

    public void setTipDesc(String tipDesc)
    {
        this.tipDesc = tipDesc;
    }

    public int getStatus()
    {
        return status;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }
}
