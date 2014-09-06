/**
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.util.pojo;

/**
 * <summary>
 *
 * </summary>
 *
 * @author jojo
 *
 */
public class DataRequest implements java.io.Serializable {


    /**   */
    private static final long serialVersionUID = -375556306545378363L;
    /**当前页码，从1开始 */
    private int page = 0;
    /**页面可显示行数 */
    private int rows;
    /**用于排序的列名*/
    private String sidx;
    /**排序的方式desc/asc*/
    private String sord;
    /**是否是搜索请求*/
    private boolean search;
    /**已经发送的请求的次数*/
    private String nd;

    /**
     * 操作员id，通常是登录的用户名
     */
    private String operatorId;

    private String searchField;
    private String searchOper;
    private String searchString;
    public int getPage()
    {
        return page;
    }
    public void setPage(int page)
    {
        this.page = page;
    }
    public int getRows()
    {
        return rows;
    }
    public void setRows(int rows)
    {
        this.rows = rows;
    }
    public String getSidx()
    {
        return sidx;
    }
    public void setSidx(String sidx)
    {
        this.sidx = sidx;
    }
    public String getSord()
    {
        return sord;
    }
    public void setSord(String sord)
    {
        this.sord = sord;
    }
    public boolean isSearch()
    {
        return search;
    }
    public void setSearch(boolean search)
    {
        this.search = search;
    }
    public String getNd()
    {
        return nd;
    }
    public void setNd(String nd)
    {
        this.nd = nd;
    }
    public String getOperatorId()
    {
        return operatorId;
    }
    public void setOperatorId(String operatorId)
    {
        this.operatorId = operatorId;
    }
    public String getSearchField()
    {
        return searchField;
    }
    public void setSearchField(String searchField)
    {
        this.searchField = searchField;
    }
    public String getSearchOper()
    {
        return searchOper;
    }
    public void setSearchOper(String searchOper)
    {
        this.searchOper = searchOper;
    }
    public String getSearchString()
    {
        return searchString;
    }
    public void setSearchString(String searchString)
    {
        this.searchString = searchString;
    }


}
