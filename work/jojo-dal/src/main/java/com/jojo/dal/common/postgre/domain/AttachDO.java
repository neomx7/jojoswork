/**
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.dal.common.postgre.domain;

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
public class AttachDO extends BasePOJO
{

    /**   */
    private static final long serialVersionUID = -5556802416515320160L;

    private String attachId;
    private byte[] attachContent;
//    private Object attachContent;

    public String getAttachId()
    {
        return attachId;
    }
    public void setAttachId(String attachId)
    {
        this.attachId = attachId;
    }
    public byte[] getAttachContent()
    {
        return attachContent;
    }
    public void setAttachContent(byte[] attachContent)
    {
        this.attachContent = attachContent;
    }
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("AttachDO [");
        if (attachId != null)
        {
            builder.append("attachId=");
            builder.append(attachId);
            builder.append(", ");
        }
        if (attachContent != null)
        {
            builder.append("attachContent=[...]");
//            builder.append(Arrays.toString(attachContent));
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
//    public Object getAttachContent()
//    {
//        return attachContent;
//    }
//    public void setAttachContent(Object attachContent)
//    {
//        this.attachContent = attachContent;
//    }
}
