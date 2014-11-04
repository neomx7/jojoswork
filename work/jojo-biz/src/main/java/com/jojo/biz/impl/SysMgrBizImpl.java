/**
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.biz.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jojo.biz.SysMgrBiz;
import com.jojo.biz.model.JsTreeNode;
import com.jojo.dal.common.postgre.domain.OrgDO;
import com.jojo.dal.common.postgre.domain.OrgUserDO;
import com.jojo.dal.common.postgre.domain.RoleDO;
import com.jojo.dal.common.postgre.domain.UserDO;
import com.jojo.dal.common.postgre.domain.UserResourcesDO;
import com.jojo.service.SysMgrService;
import com.jojo.util.pojo.DataRequest;
import com.jojo.util.pojo.DataRequest4Dept;
import com.jojo.util.pojo.DataRequest4DeptUser;

/**
 * <summary>
 * []<br>
 * <br>
 * </summary>
 *
 * @author jojo
 *
 */
@Repository
public class SysMgrBizImpl implements SysMgrBiz
{
    @Autowired
    private SysMgrService sysMgrService;

    public void saveUser(UserDO userDO){
        Map<String, Object> params = new HashMap<String, Object>(16);
        params.put("usrId", userDO.getUsrId());

        params.put("updTimestamp", new Timestamp(new Date().getTime()));
        params.put("theName", userDO.getTheName());
        params.put("email", userDO.getEmail());
        params.put("tel", userDO.getTel());
        params.put("mobile", userDO.getMobile());
        params.put("updUserId", userDO.getUpdUserId());
        params.put("usrId", userDO.getUsrId());
        params.put("pwd", userDO.getPwd());


        sysMgrService.saveUser(params);
    }

    @Override
    public List<UserDO> queryUsers(Map<String, Object> params)
    {
        return sysMgrService.queryUsers(params);
    }

    @Override
    public List<OrgUserDO> queryOrgUsers(Map<String, Object> params)
    {
        return sysMgrService.queryOrgUsers(params);
    }

    @Override
    public List<RoleDO> queryRoles(Map<String, Object> params)
    {
        return sysMgrService.queryRoles(params);
    }

    @Override
    public UserDO getUser(Map<String, Object> params)
    {
        return sysMgrService.getUser(params);
    }

    @Override
    public List<UserResourcesDO> queryUsrResources(Map<String, Object> params)
    {
        return sysMgrService.queryUsrResources(params);
    }

    @Override
    public List<OrgDO> queryDeptsByParentCode(DataRequest dataRequest)
    {
        DataRequest4Dept dataRequest4Dept = (DataRequest4Dept) dataRequest;
        Map<String, Object> params = new HashMap<String, Object>(8);
        params.put("parentCode", dataRequest4Dept.getParentCode());
        params.put("subCodeLength", dataRequest4Dept.getSubCodeLength());
        return sysMgrService.queryDeptsByParentCode(params);
    }

    @Override
    public List<JsTreeNode> queryDeptsByParentCode4JsTree(DataRequest dataRequest)
    {
        List<OrgDO> orgDOs = queryDeptsByParentCode(dataRequest);
        if (CollectionUtils.isNotEmpty(orgDOs))
        {
            List<JsTreeNode> list = new ArrayList<JsTreeNode>(orgDOs.size());
            for (OrgDO orgDO : orgDOs)
            {
                JsTreeNode treeNode = new JsTreeNode();
                treeNode.setId(orgDO.getCode());
                treeNode.setText(orgDO.getTheName());
                treeNode.setChildren((!orgDO.isLeafFlg()));
                list.add(treeNode);
            }
            return list;
        }
        return null;
    }

    @Override
    public List<UserDO> queryDeptUsers(DataRequest dataRequest)
    {
        DataRequest4DeptUser deptUser = (DataRequest4DeptUser)dataRequest;
        Map<String, Object> params = new HashMap<String, Object>(8);
        params.put("deptCode", deptUser.getDeptCode());
        List<UserDO> userDOs = sysMgrService.queryDeptUser(params);
        //去重复，根据用户id
        Map<String, UserDO> map = new HashMap<String, UserDO>(userDOs.size());
        for (UserDO userDO : userDOs)
        {
            UserDO entry = map.get(userDO.getUsrId());

            if (entry == null)
            {
                map.put(userDO.getUsrId(),userDO);
            }

            List<RoleDO> roleDOs = userDO.getRoles();

            RoleDO roleDO = new RoleDO();
            roleDO.setTheId(userDO.getRoleId());
            roleDO.setTheName(userDO.getRoleName());
            roleDOs.add(roleDO);
        }
        return new ArrayList<UserDO>(map.values());
    }

}
