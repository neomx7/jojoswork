package com.jojo.facade.workflow;

import com.jojo.util.dal.domain.ProcessTaskFormDO;

/**
 * <summary>
 * taskid 和表单 formid 存在一对一的关联,这里取出对应的关联数据(即用户自定义数据)
 * </summary>
 *
 * @author jojo
 *
 */
public interface WorkFlowTaskFormDataParser
{

    /**
     *
     * <summary>
     * <p>
     * [得到任务绑定的 form 表单对象的数据内容]
     * </p>
     * 目前从 db 中获取<br>
     * </summary>
     *
     * @author jojo
     *
     * @param taskId
     * @return
     */
    public ProcessTaskFormDO getTaskFormData(String taskId);

}
