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
    private static final long serialVersionUID = 0L;

    private String theUsrId;

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
        builder.append("UserResourcesDO [theUsrId=");
        builder.append(theUsrId);
        builder.append(", resourceDOs=");
        builder.append(resourceDOs);
        builder.append(", toString()=");
        builder.append(super.toString());
        builder.append("]");
        return builder.toString();
    }

    public String getTheUsrId()
    {
        return theUsrId;
    }

    public void setTheUsrId(String theUsrId)
    {
        this.theUsrId = theUsrId;
    }




}
