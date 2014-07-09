/**
 * JOJO
 *
 * Copyright (c) 2013-2096 JOJO,Inc.All Rights Reserved.
 */
package com.jojo.util.ui.vo.workflow;

import java.io.Serializable;

/**
 * <summary>
 * []<br>
 * <br>
 * </summary>
 *
 * @author jojo
 *
 */
public class LocationGraph implements Serializable
{
    /**   */
    private static final long serialVersionUID = -8404850843450169641L;

    private int x=0;
    private int y=0;

    private int width = 0;
    private int height = 0;

    private double defX = 0;
    private double defY = 0;

    public int getX()
    {
        return x;
    }
    public void setX(int x)
    {
        this.x = x;
    }
    public int getY()
    {
        return y;
    }
    public void setY(int y)
    {
        this.y = y;
    }
    public int getWidth()
    {
        return width;
    }
    public void setWidth(int width)
    {
        this.width = width;
    }
    public int getHeight()
    {
        return height;
    }
    public void setHeight(int height)
    {
        this.height = height;
    }
    public double getDefX()
    {
        return defX;
    }
    public void setDefX(double defX)
    {
        this.defX = defX;
    }
    public double getDefY()
    {
        return defY;
    }
    public void setDefY(double defY)
    {
        this.defY = defY;
    }

}
