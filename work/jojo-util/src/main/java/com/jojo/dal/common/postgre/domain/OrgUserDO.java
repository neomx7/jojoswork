package com.jojo.dal.common.postgre.domain;

import java.util.ArrayList;
import java.util.List;

import com.jojo.util.pojo.BasePOJO;

/**
 * <summary>
 * [部门用户表]<br>
 * <br>
 * </summary>
 *
 * @author jojo
 *
 */
public class OrgUserDO extends BasePOJO
{

    /**   */
    private static final long serialVersionUID = -33491729851112009L;

    private String deprtId;
    private String theUsrDbId;
    private String usrId;

    private List<RoleDO> roleDOs = new ArrayList<RoleDO>(20);



    public List<RoleDO> getRoleDOs()
    {
        return roleDOs;
    }
    public void setRoleDOs(List<RoleDO> roleDOs)
    {
        this.roleDOs = roleDOs;
    }
    public String getDeprtId()
    {
        return deprtId;
    }
    public void setDeprtId(String deprtId)
    {
        this.deprtId = deprtId;
    }
    public String getUsrId()
    {
        return usrId;
    }
    public void setUsrId(String usrId)
    {
        this.usrId = usrId;
    }
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("OrgUserDO [deprtId=");
        builder.append(deprtId);
        builder.append(", theUsrDbId=");
        builder.append(theUsrDbId);
        builder.append(", usrId=");
        builder.append(usrId);
        builder.append(", roleDOs=");
        builder.append(roleDOs);
        builder.append(", toString()=");
        builder.append(super.toString());
        builder.append("]");
        return builder.toString();
    }
    public String getTheUsrDbId()
    {
        return theUsrDbId;
    }
    public void setTheUsrDbId(String theUsrDbId)
    {
        this.theUsrDbId = theUsrDbId;
    }


}
