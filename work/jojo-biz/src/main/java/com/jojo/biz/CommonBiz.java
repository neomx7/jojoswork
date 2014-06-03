/**
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.biz;

import com.jojo.dal.common.postgre.domain.AttachDO;


/**
 * <summary>
 *
 * </summary>
 *
 * @author jojo
 *
 */
public interface CommonBiz
{
    /**
     *
     * <summary>
     * [根据附件id得到附件内容]<br>
     * <br>
     * </summary>
     *
     * @author jojo
     *
     * @param attchId
     * @return
     */
    public AttachDO download(String attchId);
}
