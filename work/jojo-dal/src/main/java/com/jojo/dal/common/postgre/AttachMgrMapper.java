/**
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.dal.common.postgre;

import java.util.List;
import java.util.Map;

import com.jojo.dal.common.postgre.domain.AttachDO;

/**
 * <summary>
 *
 * <br>
 * </summary>
 *
 * @author jojo
 *
 */
public interface AttachMgrMapper
{

    /**
     *
     * <summary>
     * [从服务器下载文件]<br>
     * <br>
     * </summary>
     *
     * @author jojo
     *
     * @return
     */
    public List<AttachDO> download(Map<String, Object> params);

}
