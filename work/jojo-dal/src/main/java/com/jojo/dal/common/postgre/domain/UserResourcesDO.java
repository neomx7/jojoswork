package com.jojo.dal.common.postgre.domain;

import java.util.ArrayList;
import java.util.List;

import com.jojo.util.pojo.BasePOJO;

/**
 * <summary>
 * [用户对象]<br>
 * <br>
 * </summary>
 *
 * @author jojo
 *
 */
public class UserResourcesDO extends BasePOJO
{


    /**   */
    private static final long serialVersionUID = -3497450446864915700L;

    private String usrId;

    private List<ResourceDO> resourceDOs = new ArrayList<ResourceDO>(100);


    public List<ResourceDO> getResourceDOs()
    {
        return resourceDOs;
    }

    public void setResourceDOs(List<ResourceDO> resourceDOs)
    {
        this.resourceDOs = resourceDOs;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("UserResourcesDO [usrId=");
        builder.append(usrId);
        builder.append(", resourceDOs=");
        builder.append(resourceDOs);
        builder.append(", toString()=");
        builder.append(super.toString());
        builder.append("]");
        return builder.toString();
    }

    public String getUsrId()
    {
        return usrId;
    }

    public void setUsrId(String usrId)
    {
        this.usrId = usrId;
    }





}
