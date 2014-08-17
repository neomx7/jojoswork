/**
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jojo.dal.common.postgre.AttachMgrMapper;
import com.jojo.dal.common.postgre.domain.AttachDO;
import com.jojo.facade.service.CommonService;

/**
 * <summary>
 *
 * </summary>
 *
 * @author jojo
 *
 */
@Repository
public class CommonServiceImpl implements CommonService
{

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private AttachMgrMapper attachMgrMapper;

    @Override
    public AttachDO download(String attchId)
    {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("attachId", attchId);
        List<AttachDO> attachDOs = attachMgrMapper.download(params);
        if (attachDOs!= null && !attachDOs.isEmpty() && attachDOs.get(0)!=null && attachDOs.get(0).getAttachContent() != null)
        {
            return attachDOs.get(0);
        }
        return null;
    }

}

