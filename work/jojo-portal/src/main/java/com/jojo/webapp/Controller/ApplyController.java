/**
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.webapp.Controller;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jojo.biz.ApplyBiz;
import com.jojo.dal.common.postgre.domain.MaterialApplyDO;
import com.jojo.integration.workflow.WorkFlowExecutor;
import com.jojo.util.CommonUtils;
import com.jojo.util.biz.bo.ApplyBO;
import com.jojo.util.biz.bo.PageResultBO;
import com.jojo.util.common.DateUtils;
import com.jojo.util.constants.JOJOConstants;
import com.jojo.util.pojo.BasePOJO;
import com.jojo.util.pojo.DataRequest;
import com.jojo.util.pojo.DataRequest4Apply;
import com.jojo.util.pojo.DataResponse;
import com.jojo.util.pojo.PageQuery;
import com.jojo.util.ui.vo.workflow.WorkFlowQuery;
import com.jojo.util.ui.vo.workflow.WorkFlowTaskDTO;
import com.jojo.web.common.context.ContextHolder;
import com.jojo.webapp.form.ApplyForm;

/**
 * <summary>
 * 物料申请Controller
 * </summary>
 *
 * @author jojo
 *
 */
@Controller
public class ApplyController extends BaseController
{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ApplyBiz applyBiz;

    // @RequestMapping(value = "/equipment/saveApply")
    // @ResponseBody
    // public DataResponse<MaterialApplyDO> saveApply(@RequestBody ApplyForm
    // form){
    // System.out.println("saveApply........");
    // DataResponse<MaterialApplyDO> dataResponse = new
    // DataResponse<MaterialApplyDO>();
    // return dataResponse;
    // }

    /**
     *
     * <summary>
     * [新增申请草稿]<br>
     * <br>
     * </summary>
     *
     * @author jojo
     *
     * @param form
     * @return
     */
    @RequestMapping(value = "/equipment/addApply")
    @ResponseBody
    public DataResponse<MaterialApplyDO> addApply(HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse, @RequestBody ApplyForm form)
    {
        logger.info("match url 4 '/equipment/addApply'");
        DataResponse<MaterialApplyDO> dataResponse = new DataResponse<MaterialApplyDO>();

        try
        {
            addNewApply(httpServletRequest, httpServletResponse, form);
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), e);
            dataResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            dataResponse.setTip(e.getMessage());
            dataResponse.setTipDesc(ExceptionUtils.getStackTrace(e));
        }
        return dataResponse;
    }

    /**
     * <summary>
     * []<br>
     * <br>
     * </summary>
     *
     * @author jojo
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param form
     */
    private void addNewApply(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
            ApplyForm form)
    {
        MaterialApplyDO applyDO = new MaterialApplyDO();
        applyDO.setTheId(UUID.randomUUID().toString());
        applyDO.setTheName(form.getTheName());
        applyDO.setTheRemark(form.getTheRemark());
        applyDO.setCrtTime(DateUtils.getCurrentDateTimeMs());
        applyDO.setCrtTimestamp(new Timestamp(new Date().getTime()));
        applyDO.setCrtUserId(getLoginUsrId(httpServletRequest, httpServletResponse));
        applyDO.setStatus(0);
        applyBiz.addApply(applyDO);
        form.setTheId(applyDO.getTheId());
    }

    /**
     *
     * <summary>
     * [保存申请草稿]<br>
     * <br>
     * </summary>
     *
     * @author jojo
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param form
     * @return
     */
    @RequestMapping(value = "/equipment/editApply")
    @ResponseBody
    public DataResponse<MaterialApplyDO> editApply(HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse, @RequestBody ApplyForm form)
    {
        logger.info("match url 4 '/equipment/editApply'");
        DataResponse<MaterialApplyDO> dataResponse = new DataResponse<MaterialApplyDO>();
        if (StringUtils.isBlank(form.getTheId()))
        {
            dataResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            dataResponse.setTip("未获取到有效的申请id");
            dataResponse.setTipDesc("无法保存申请：申请id为空。");
            return dataResponse;
        }

        if (form.getStatus() > 0)
        {
            dataResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
            dataResponse.setTip("当前表单已经在流程中流转，无法保存草稿");
            dataResponse.setTipDesc("无法保存申请：申请已经提交流程，需等待流程处理结束");
            return dataResponse;
        }

        Map<String, Object> params = new HashMap<String, Object>(10);
        params.put("theId", form.getTheId());
        params.put("updUserId", getLoginUsrId(httpServletRequest, httpServletResponse));
        // params.put("status", form.getStatus());
        params.put("updTime", new Date());
        params.put("theName", form.getTheName());
        params.put("theRemark", form.getTheRemark());

        try
        {
            applyBiz.editApply(params);
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), e);
            dataResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            dataResponse.setTip(e.getMessage());
            dataResponse.setTipDesc(ExceptionUtils.getStackTrace(e));
        }
        return dataResponse;
    }

    @RequestMapping(value = "/equipment/showApply")
    public String showApply(HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse, @ModelAttribute("form") ApplyForm form)
    {
        logger.info("match url 4 '/equipment/showApply'");
//        DataResponse<MaterialApplyDO> dataResponse = new DataResponse<MaterialApplyDO>();
        if (StringUtils.isBlank(form.getTheId()))
        {
//            dataResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//            dataResponse.setTip("未获取到有效的申请id");
//            dataResponse.setTipDesc("无法展示流程：申请id未设置。");
//            return dataResponse;
            form.setErrorMsg("未获取到有效的申请id");
            return "view/common/common-error";
        }

        //
        Map<String, Object> params = new HashMap<String, Object>(2);
        params.put("theId", form.getTheId().trim());
        MaterialApplyDO applyDO = applyBiz.findApply(params);
//        dataResponse.setOne(applyDO);
//        form.setNextUsrId(nextUsrId);
        form.setTheName(applyDO.getTheName());
        form.setTheRemark(applyDO.getTheRemark());
        form.setStatus(applyDO.getStatus());
//        return dataResponse;


        //把流程审批记录也拿到

        return "view/equipment/edit-apply";
    }


    /**
     *
     * <summary>
     * [查看表单内容，进入流程task后查看]<br>
     * <br>
     * </summary>
     *
     * @author jojo
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param form
     * @return
     */
    @RequestMapping(value = "/equipment/viewApply4WorkFlow")
    public String viewApply(HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse, @ModelAttribute("form") ApplyForm form)
    {
        logger.info("match url 4 '/equipment/viewApply'");
        if (StringUtils.isBlank(form.getTheId()))
        {
            form.setErrorMsg("未获取到有效的申请id");
            return "view/common/common-error";
        }

        //
        Map<String, Object> params = new HashMap<String, Object>(2);
        params.put("theId", form.getTheId().trim());
        MaterialApplyDO applyDO = applyBiz.findApply(params);
        form.setTheName(applyDO.getTheName());
        form.setTheRemark(applyDO.getTheRemark());
        form.setStatus(applyDO.getStatus());

        return "view/equipment/view-apply";
    }

    @RequestMapping(value = "/equipment/startProcess4Apply")
    @ResponseBody
    public DataResponse<MaterialApplyDO> startProcess4Apply(HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse, @RequestBody ApplyForm form)
    {
        logger.info("match url 4 '/equipment/startProcess4Apply'");
        DataResponse<MaterialApplyDO> dataResponse = new DataResponse<MaterialApplyDO>();

        // 启动流程并且同时也设定下一个流程处理人，同时下方代码的variables也设置
        if (StringUtils.isBlank(form.getNextUsrId()))
        {
            dataResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            dataResponse.setTip("未获取到有效的下一个流程处理人的id");
            dataResponse.setTipDesc("无法启动申请流程：下一个流程节点的申请人id未设置。");
            return dataResponse;
        }

        // 检查用户是否为有效用户，无效则抛出异常

        if (!ContextHolder.isValidUsr(form.getNextUsrId()))
        {
            dataResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            dataResponse.setTip("所选下一个流程处理人的id无效");
            dataResponse.setTipDesc("无法启动申请流程：下一个流程节点的申请人id无效。");
            return dataResponse;
        }

        try
        {
            // 从session中获取operId
            String operId = getLoginUsrId(httpServletRequest, httpServletResponse);
            // 没有申请则新建一个
            if (StringUtils.isBlank(form.getTheId()))
            {
                addNewApply(httpServletRequest, httpServletResponse, form);
            }
            else
            {
                Map<String, Object> params = new HashMap<String, Object>(10);
                params.put("theId", form.getTheId());
                params.put("updUserId", operId);
                params.put("updTime", new Date());
                params.put("theName", form.getTheName());
                params.put("theRemark", form.getTheRemark());
                applyBiz.editApply(params);
            }

            // 启动工作流
            String businessKey = form.getTheId().trim();
            WorkFlowExecutor workFlowExecutor = (WorkFlowExecutor) (ContextHolder.getBean(JOJOConstants.WORKFLOW_SERVICE));
            Map<String, Object> variables = new HashMap<String, Object>(2);
            variables.put(JOJOConstants.WORKFLOW_PROCESSINST_START_USRID, getLoginUsrId(httpServletRequest, httpServletResponse));
            variables.put(JOJOConstants.WORKFLOW_PROCESSINST_NEXT_ASSIGNEE, form.getNextUsrId());
            //直接给url，这样就不用转换spring bean和方法来查看表单内容了
            variables.put(JOJOConstants.WORKFLOW_PROCESSINST_BIZ_KEY_URL, "/equipment/viewApply4WorkFlow?theId="+businessKey);

           //lishengProcess1:8:15904 //TODO 需要把里面写死的procDefKey换成动态改变的
            String processInstanceId = workFlowExecutor.startProcessInstanceByKey("materialApplyProcess", operId, businessKey, variables);

            WorkFlowTaskDTO flowTaskDTO = workFlowExecutor.getProcessActivity(processInstanceId);

            // 更新到申请表中
            Map<String, Object> params = new HashMap<String, Object>(10);
            params.put("theId", form.getTheId());
            params.put("updUserId", operId);
            params.put("updTime", new Date());
            params.put("instanceId", processInstanceId);
            params.put("status", JOJOConstants.WORKFLOW_TASKMODE_DOING);
            params.put("statusDsc", flowTaskDTO.getTheName());
            applyBiz.editApply(params);
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), e);
            dataResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            dataResponse.setTip(e.getMessage());
            dataResponse.setTipDesc(ExceptionUtils.getStackTrace(e));
        }

        return dataResponse;
    }

    /**
     *
     * <summary>
     * [进入到物料管理申请界面]<br>
     * <br>
     * </summary>
     *
     * @author jojo
     *
     * @param form
     * @return
     */
    @RequestMapping(value = "/equipment/toApplyEquipment")
    public String toCreateApply(@ModelAttribute("form") ApplyForm form)
    {
        logger.info("match url 4 '/equipment/toApplyEquipment'");
        return "view/equipment/create-apply";
    }

    @RequestMapping(value = "/equipment/toListEquipment")
    public String toListEquipment(@ModelAttribute("form") ApplyForm form)
    {
        logger.info("match url 4 '/equipment/toListEquipment'");
        return "view/equipment/apply-list";
    }

    /**
     *
     * <summary>
     * [查询已保存的申请]<br>
     * <br>
     * </summary>
     *
     * @author jojo
     *
     * @param page
     * @param rows
     * @param sidx
     * @param sord
     * @param searchField
     * @param searchOper
     * @param searchString
     * @param filters
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "/equipment/listEquipment")
    @ResponseBody
    public DataResponse<MaterialApplyDO> queryApply(
            @RequestParam(required = false, defaultValue = "1", value = "page") String page,
            @RequestParam(required = false, defaultValue = "20", value = "rows") String rows,
            @RequestParam(required = false, value = "sidx") String sidx,
            @RequestParam(required = false, value = "sord") String sord,
            // @RequestParam("_search") boolean search,
            @RequestParam(required = false, value = "searchField") String searchField,
            @RequestParam(required = false, value = "searchOper") String searchOper,
            @RequestParam(required = false, value = "searchString") String searchString,
            @RequestParam(required = false, value = "filters") String filters, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse)
    {
        try
        {
            DataRequest4Apply request = new DataRequest4Apply();
            request.setPage(StringUtils.isBlank(page) ? 1 : Integer.valueOf(page));
            request.setRows(StringUtils.isBlank(rows) ? 20 : Integer.valueOf(rows));
            request.setSidx(sidx);
            request.setSord(sord);
            request.setSearchField(searchField);
            request.setSearchOper(searchOper);
            request.setSearchString(searchString);

            request.setOperatorId(getLoginUsrId(httpServletRequest, httpServletResponse));

            return findResult(request, MaterialApplyDO.class, applyBiz, "queryPage");
        }
        catch (Exception e)
        {
            // e.printStackTrace();
            logger.error("query createApply list failed. [{}]", e);
        }
        return null;
    }

    /**
     *
     * <summary>
     * [访问待办列表]<br>
     * <br>
     * </summary>
     *
     * @author jojo
     *
     * @param page
     * @param rows
     * @param sidx
     * @param sord
     * @param searchField
     * @param searchOper
     * @param searchString
     * @param filters
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "/process/qryMyTaskList")
    @ResponseBody
    public DataResponse<WorkFlowTaskDTO> queryMyTaskList(
            @RequestParam(defaultValue = "1", value = "page") String page,
            @RequestParam(defaultValue = "20", value = "rows") String rows,
            @RequestParam("sidx") String sidx,
            @RequestParam("sord") String sord,
            // @RequestParam("_search") boolean search,
            @RequestParam(required = false, value = "searchField") String searchField,
            @RequestParam(required = false, value = "searchOper") String searchOper,
            @RequestParam(required = false, value = "searchString") String searchString,
            @RequestParam(required = false, value = "filters") String filters, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse)
    {
        DataResponse<WorkFlowTaskDTO> dataResponse = new DataResponse<WorkFlowTaskDTO>();
        try
        {
            DataRequest request = new DataRequest();
            request.setPage(StringUtils.isEmpty(page) ? 1 : Integer.valueOf(page));
            request.setRows(StringUtils.isEmpty(rows) ? 20 : Integer.valueOf(rows));
            request.setSidx(sidx);
            request.setSord(sord);
            request.setSearchField(searchField);
            request.setSearchOper(searchOper);
            request.setSearchString(searchString);

            WorkFlowQuery query = new WorkFlowQuery();
            query.setTaskMode(JOJOConstants.WORKFLOW_TASKMODE_TODO);
            // 从session中得到当前用户
            query.setOperId(getLoginUsrId(httpServletRequest, httpServletResponse));

            return findWorkFlowResult(request, JOJOConstants.WORKFLOW_SERVICE, "queryWorkFlowTODOTask", query,
                    WorkFlowQuery.class, dataResponse
            // ,httpServletRequest,httpServletResponse
            );

        }
        catch (Exception e)
        {
            logger.error("调用工作流服务出错", e);
            redirectGolbalErr(dataResponse, e);
        }
        return dataResponse;
    }

    @SuppressWarnings("unchecked")
    public <T> DataResponse<T> findWorkFlowResult(DataRequest request, String qryBean, String qryMethodName,
            PageQuery qryParamObj
            // ,Class<T> cls
            , Class<?> cls, DataResponse<T> response
    // ,HttpServletRequest httpServletRequest
    // ,HttpServletResponse httpServletResponse
    ) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException
    {
        Object wfService = (ContextHolder.getBean(qryBean));// JOJOConstants.WORKFLOW_SERVICE

        int count = 0;// 总记录数
        int limit = request.getRows() <= 0 ? 20 : request.getRows();// 每页显示数量
        int totalPages = 0;// 总页数
        int page = request.getPage() <= 0 ? 1 : request.getPage();// 当前显示页码

        qryParamObj.setPageLimit(limit);
        qryParamObj.setCurPage(page);

        // 反射通过查询方法得到对象
        PageResultBO<T> resultBO = null;

        Class<? extends Object> clazz = wfService.getClass();
        Method qryMethod = null;
        qryMethod = clazz.getDeclaredMethod(qryMethodName, cls);
        resultBO = (PageResultBO<T>) (qryMethod.invoke(wfService, qryParamObj));

        int currPage = resultBO.getCurPage();
        // count = resultBO.getTotalCount();
        // totalPages = (int) Math.ceil((double) count / limit);
        // int currPage = Math.min(totalPages, page);
        // qryParamObj.setCurPage(currPage);
        //
        // int start = currPage * limit - limit;
        // start = start < 0 ? 0 : start;
        List<T> list = resultBO.getResults();

        if (CollectionUtils.isNotEmpty(list))
        {
            for (int i=0;i<list.size();i++)
            {
                BasePOJO pojo = (BasePOJO)(list.get(i));
                pojo.setNumber((i+1));
            }
        }


        response.setRecords(count);
        response.setTotal(totalPages);
        response.setPage(currPage);
        response.setRows(list);

        return response;
    }

    @SuppressWarnings("unused")
    private ApplyBO createOne(String id, String applyName, String remark, int number)
    {
        ApplyBO bo = new ApplyBO();
        bo.setId(id);
        bo.setTheName(applyName);
        bo.setTheRemark(remark);
        bo.setCrtTime(CommonUtils.getDateFormat2Ms(new Date()));
        bo.setNumber(number);
        return bo;
    }
}
