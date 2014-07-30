package com.jojo.dal.common.postgre.domain;

import java.util.ArrayList;
import java.util.List;

import com.jojo.util.pojo.BasePOJO;

/**
 * <summary>
 * [职务]<br>
 * <br>
 * </summary>
 *
 * @author jojo
 *
 */
public class DutyDO extends BasePOJO
{

    /**   */
    private static final long serialVersionUID = -33491729851112009L;


    private String targetId;
    private int targetType;

    private List<RoleDO> roleDOs = new ArrayList<RoleDO>(2);



    public String getTargetId()
    {
        return targetId;
    }
    public void setTargetId(String targetId)
    {
        this.targetId = targetId;
    }
    public int getTargetType()
    {
        return targetType;
    }
    public void setTargetType(int targetType)
    {
        this.targetType = targetType;
    }
    public List<RoleDO> getRoleDOs()
    {
        return roleDOs;
    }
    public void setRoleDOs(List<RoleDO> roleDOs)
    {
        this.roleDOs = roleDOs;
    }


}
