/**
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.util.ui.vo.workflow;

import com.jojo.util.pojo.BasePOJO;

/**
 * <summary>
 * []<br>
 * <br>
 * </summary>
 *
 * @author jojo
 *
 */
public class WorkFlowQuery extends BasePOJO
{

    /**   */
    private static final long serialVersionUID = 7415923292129013478L;

    private String proDefId;

    private String proDefKey;

    public String getProDefId()
    {
        return proDefId;
    }

    public void setProDefId(String proDefId)
    {
        this.proDefId = proDefId;
    }

    public String getProDefKey()
    {
        return proDefKey;
    }

    public void setProDefKey(String proDefKey)
    {
        this.proDefKey = proDefKey;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("WorkFlowQuery [");
        if (proDefId != null)
        {
            builder.append("proDefId=");
            builder.append(proDefId);
            builder.append(", ");
        }
        if (proDefKey != null)
        {
            builder.append("proDefKey=");
            builder.append(proDefKey);
            builder.append(", ");
        }
        if (super.toString() != null)
        {
            builder.append("toString()=");
            builder.append(super.toString());
        }
        builder.append("]");
        return builder.toString();
    }

}
