/**
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.util.pojo;

import com.jojo.util.pojo.BasePOJO;

/**
 * <summary>
 * []<br>
 * <br>
 * </summary>
 *
 * @author jojo
 *
 */
public class PageQuery extends BasePOJO
{

    /**   */
    private static final long serialVersionUID = 0L;

    private int totalCount = 0;
    private int start = 0;
    private int pageLimit = 20;
    private int curPage=1;


    public int getTotalCount()
    {
        return totalCount;
    }

    public void setTotalCount(int totalCount)
    {
        this.totalCount = totalCount;
    }

    public int getStart()
    {
        return start;
    }

    public void setStart(int start)
    {
        this.start = start;
    }

    public int getPageLimit()
    {
        return pageLimit;
    }

    public void setPageLimit(int pageLimit)
    {
        this.pageLimit = pageLimit;
    }

    public int getCurPage()
    {
        return curPage;
    }

    public void setCurPage(int curPage)
    {
        this.curPage = curPage;
    }

}
