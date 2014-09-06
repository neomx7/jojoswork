/**
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.webapp.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jojo.util.biz.bo.UserBO;
import com.jojo.util.pojo.DataRequest;
import com.jojo.util.pojo.DataResponse;
import com.jojo.web.common.context.SystemMgrCtxHolder;
import com.jojo.webapp.form.IndexForm;

/**
 * <summary>
 *
 * </summary>
 *
 * @author jojo
 *
 */
@Controller
public class IndexController extends BaseController
{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource(name="systemMgrCtxHolder")
    private SystemMgrCtxHolder systemMgrCtxHolder;

    // , method = RequestMethod.GET
    @RequestMapping(value = "/index")
    public String index(@ModelAttribute("form") IndexForm form,HttpServletRequest request,HttpServletResponse response)
    {
        logger.info("enter index navigation.");
        String loginUserName = getLoginUsrId(request, response);
        form.setMenus(systemMgrCtxHolder.getSubMenus4NextLv(form.getMenuId(),form.getMenuCode(), loginUserName));
        form.getUserDO().setUsrId(loginUserName);

        return "view/index";  // 设置返回页面，这里对应 /WEB-INF/ 目录下的 {0}.ftl 文件
    }

    @RequestMapping(value = "/user/list", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getUserList()
    {
        logger.info("列表");
        List<UserBO> list = new ArrayList<UserBO>();
        UserBO um = new UserBO();
        um.setId("1");
        um.setUserName("sss");
        um.setAge(222);
        list.add(um);
        Map<String, Object> modelMap = new HashMap<String, Object>(3);
        modelMap.put("total", "1");
        modelMap.put("data", list);
        modelMap.put("success", "true");
        return modelMap;
    }

    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> addUser(@RequestBody UserBO model)
    {
        logger.info("新增");
        logger.info("捕获到前台传递过来的Model，名称为：" + model.getUserName());
        Map<String, String> map = new HashMap<String, String>(1);
        map.put("success", "true");
        return map;
    }

    /**
     *
     * <summary>
     * <p>排序字段sord作为参数传入</p>
     * </summary>
     *
     * @author jojo
     *
     * @return
     */
    @RequestMapping(value = "/demo/list")
    @ResponseBody
    public DataResponse<UserBO> query(
            @RequestParam(defaultValue = "1", value = "page") String page,
            @RequestParam(defaultValue = "20", value = "rows") String rows,
            @RequestParam("sidx") String sidx,
            @RequestParam("sord") String sord,
//            @RequestParam("_search") boolean search,
            @RequestParam(required = false, value = "searchField") String searchField,
            @RequestParam(required = false, value = "searchOper") String searchOper,
            @RequestParam(required = false, value = "searchString") String searchString,
            @RequestParam(required = false, value = "filters") String filters,
            HttpServletRequest httpServletRequest
     )
    {

        try
        {
            DataRequest request = new DataRequest();
            request.setPage(StringUtils.isEmpty(page) ? 1 : Integer.valueOf(page));
            request.setRows(StringUtils.isEmpty(rows) ? 20 : Integer.valueOf(rows));
            request.setSidx(sidx);
            request.setSord(sord);
//            request.setSearch(search);
            request.setSearchField(searchField);
            request.setSearchOper(searchOper);
            request.setSearchString(searchString);
            return findResult(request, UserBO.class);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
        // // logger.info("query");
        //
        // int page = 1;
        // int rows = 2;
        // // page = request.getParameter("page");
        // // rows = request.getParameter("rows");
        // int totalRecord = 12; // 总记录数(应根据数据库取得，在此只是模拟)
        // int totalPage = totalRecord % rows == 0 ? totalRecord / rows :
        // totalRecord
        // / rows + 1; // 计算总页数
        // try
        // {
        // int index = (page - 1) * (rows); // 开始记录数0
        // int pageSize = (rows);// 2
        // // 以下模拟构造JSON数据对象 ,该对象是jqgrid的默认返回对象
        // String json = "{total: " + totalPage + ", page: " + page +
        // ", records: " + totalRecord + ", rows: [";
        // for (int i = index; i < pageSize + index && i < totalRecord; i++)
        // {
        // json += "{'id':'" + i + "','invdate':'" + i + "','name':'" + i +
        // "','amount':'" + i + "','tax':'" + i
        // + "','total':'" + i + "','note':'" + i + "'}";
        // if (i != pageSize + index - 1 && i != totalRecord - 1)
        // {
        // json += ",";
        // }
        // }
        // json += "]}";
        // logger.info(json);
        // response.getWriter().write(json); // 将JSON数据返回页面
        // }
        // catch (Exception e)
        // {
        // logger.error("query jqGridlist failed. exception info : [{}]",e);
        // }

    }





    @SuppressWarnings("unchecked")
    public <T> DataResponse<T> findResult(DataRequest request, Class<T> cls)
    {
        DataResponse<T> response = new DataResponse<T>();
        int count = 0;// 总记录数
        int limit = request.getRows() <= 0 ? 20 : request.getRows();// 每页显示数量
        int totalPages = 0;// 总页数
        int page = request.getPage() <= 0 ? 1 : request.getPage();// 当前显示页码
        List<T> list = new ArrayList<T>();


        // Set<Criterion> set = initSearchCondition(request.isSearch(),
        // request.getSearchField(), request.getSearchOper(),
        // request.getSearchString());
        // count = customDao.count(cls, set);
        count = 12;
        totalPages =  (int)Math.ceil( (double)count / limit );
//        if (count % limit != 0)
//        {
//            totalPages++;
//        }
        int currPage = Math.min(totalPages, page);

        int start = currPage * limit - limit;
        start = start < 0 ? 0 : start;

        for (int i = 0; i < 10; i++)
        {
            list.add((T) createOneUser(String.valueOf(i), "userName" + i, 1));
        }
        response.setRecords(count);
        response.setTotal(totalPages);
        response.setPage(currPage);
        response.setRows(list);

        return response;
    }

    private UserBO createOneUser(String id, String userName, int status)
    {
        UserBO userModel = new UserBO();
        userModel.setId(id);
        userModel.setUserName(userName);
        userModel.setStatus(status);
        return userModel;
    }
}
