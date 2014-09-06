/**
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.dal.common.postgre;

import java.util.List;
import java.util.Map;

import com.jojo.dal.common.postgre.domain.MaterialApplyDO;

/**
 * <summary>
 * <br>
 * </summary>
 *
 * @author jojo
 *
 */
public interface MaterialApplyMapper
{

    /**
     *
     * <summary>
     * [[新增]]<br>
     * <br>
     * </summary>
     *
     * @author jojo
     *
     * @param applyDO
     */
    public void insert(MaterialApplyDO applyDO);

    /**
     *
     * <summary>
     * [更新]<br>
     * <br>
     * </summary>
     *
     * @author jojo
     *
     * @param params
     */
    public void update(Map<String, Object> params);

    /**
     *
     * <summary>
     * [删除]<br>
     * <br>
     * </summary>
     *
     * @author jojo
     *
     * @param params
     */
    public void delete(Map<String, Object> params);

    /**
     *
     * <summary>
     * [获取一个申请]<br>
     * <br>
     * </summary>
     *
     * @author jojo
     *
     * @param params
     * @return
     */
    public MaterialApplyDO select(Map<String, Object> params);

    /**
     *
     * <summary>
     * [查询，翻页]<br>
     * <br>
     * </summary>
     *
     * @author jojo
     *
     * @param params
     * @return
     */
    public List<MaterialApplyDO> queryPage(Map<String, Object> params);

}
