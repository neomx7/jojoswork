/**
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.process.dal.postgre;

import java.io.InputStream;
import java.util.Map;

/**
 * <summary>
 * []<br>
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
     * [上传图片到服务器]<br>
     * <br>
     * </summary>
     *
     * @author jojo
     *
     * @param attchKey
     * @param inputStream
     */
    public void upload(Map<String, Object> params);

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
    public InputStream download(Map<String, Object> params);


    public Long getDemoCount();

    /**
     *
     * <summary>
     * [获取工作流程图的个数]<br>
     * <br>
     * </summary>
     *
     * @author jojo
     *
     * @param params
     * @return
     */
    public Long count(Map<String, Object> params);
}
