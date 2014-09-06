/**
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.biz.impl;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jojo.biz.CommonBiz;
import com.jojo.dal.common.postgre.domain.AttachDO;
import com.jojo.service.CommonService;

/**
 * <summary>
 *
 * </summary>
 *
 * @author jojo
 *
 */
@Repository
public class CommonBizImpl implements CommonBiz
{

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CommonService commonService;

    @Override
    public AttachDO download(String attchId)
    {
        if (StringUtils.isBlank(attchId))
        {
            return null;
        }
        return commonService.download(attchId);
    }


}
