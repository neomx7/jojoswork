/**
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.biz;

import java.util.List;

import com.jojo.dal.common.postgre.domain.MaterialApplyDO;
import com.jojo.util.pojo.DataRequest;

/**
 * <summary>
 * 物料申请的biz
 * </summary>
 *
 * @author jojo
 *
 */
public interface ApplyBiz
{

    /**
     *
     * <summary>
     * [翻页查询，物料申请列表]<br>
     * <br>
     * </summary>
     *
     * @author jojo
     *
     * @param params
     * @return
     */
   public List<MaterialApplyDO> queryPage(DataRequest request);

   /**
    *
    * <summary>
    * [新增物料申请]<br>
    * <br>
    * </summary>
    *
    * @author jojo
    *
    * @param applyDO
    */
   public void addApply(MaterialApplyDO applyDO);
}
