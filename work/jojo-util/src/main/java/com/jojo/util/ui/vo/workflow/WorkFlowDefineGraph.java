package com.jojo.util.ui.vo.workflow;

import java.awt.Point;

import com.jojo.util.pojo.BasePOJO;

public class WorkFlowDefineGraph extends BasePOJO
{

    /**   */
    private static final long serialVersionUID = -8769325252002759063L;

    private String proDefId;

    //流程图片指纹,全局唯一，冗余
    private String graphKey;
    //流程图片路径，先用文件存储
    private String graphURL;
//    private InputStream inputStream;
    private Point point;

    public String getProDefId()
    {
        return proDefId;
    }
    public void setProDefId(String proDefId)
    {
        this.proDefId = proDefId;
    }
    public Point getPoint()
    {
        return point;
    }
    public void setPoint(Point point)
    {
        this.point = point;
    }
    public String getGraphKey()
    {
        return graphKey;
    }
    public void setGraphKey(String graphKey)
    {
        this.graphKey = graphKey;
    }
    public String getGraphURL()
    {
        return graphURL;
    }
    public void setGraphURL(String graphURL)
    {
        this.graphURL = graphURL;
    }
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("WorkFlowDefineGraph [");
        if (proDefId != null)
        {
            builder.append("proDefId=");
            builder.append(proDefId);
            builder.append(", ");
        }
        if (graphKey != null)
        {
            builder.append("graphKey=");
            builder.append(graphKey);
            builder.append(", ");
        }
        if (graphURL != null)
        {
            builder.append("graphURL=");
            builder.append(graphURL);
            builder.append(", ");
        }
        if (point != null)
        {
            builder.append("point=");
            builder.append(point);
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
