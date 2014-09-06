package com.jojo.dal.common.postgre.domain;

import com.jojo.util.pojo.BasePOJO;

/**
 * <summary>
 * [权限]<br>
 * <br>
 * </summary>
 *
 * @author jojo
 *
 */
public class PrivilegeDO extends BasePOJO
{

    /**   */
    private static final long serialVersionUID = -945527619818182756L;

    private String targetId;
    private int targetType;

    private ResourceDO resourceDO;

    /**
     * 功能串
     */
    private String funFlag;

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

    public String getFunFlag()
    {
        return funFlag;
    }

    public void setFunFlag(String funFlag)
    {
        this.funFlag = funFlag;
    }

    public ResourceDO getResourceDO()
    {
        return resourceDO;
    }

    public void setResourceDO(ResourceDO resourceDO)
    {
        this.resourceDO = resourceDO;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("PrivilegeDO [targetId=");
        builder.append(targetId);
        builder.append(", targetType=");
        builder.append(targetType);
        builder.append(", resourceDO=");
        builder.append(resourceDO);
        builder.append(", funFlag=");
        builder.append(funFlag);
        builder.append(", toString()=");
        builder.append(super.toString());
        builder.append("]");
        return builder.toString();
    }

}
