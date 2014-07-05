package com.jojo.util.biz.bo;

import java.util.List;


public class PageResultBO<T> extends BaseBO
{

    /**   */
    private static final long serialVersionUID = 0L;

    private int totalCount = 0;
    private int start = 0;
    private int pageLimit = 20;
    private int curPage=1;

    private List<T> results;

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
    public List<T> getResults()
    {
        return results;
    }
    public void setResults(List<T> results)
    {
        this.results = results;
    }



}
