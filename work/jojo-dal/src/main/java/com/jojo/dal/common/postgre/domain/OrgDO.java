package com.jojo.dal.common.postgre.domain;

import java.util.ArrayList;
import java.util.List;

import com.jojo.util.pojo.BasePOJO;

/**
 * <summary>
 * [部门表]<br>
 * <br>
 * </summary>
 *
 * @author jojo
 *
 */
public class OrgDO extends BasePOJO
{

    /**   */
    private static final long serialVersionUID = -33491729851112009L;

    private int level;
    private String code;
    private String parentId;
    private boolean leafFlg;


    private List<UserDO> userDOs = new ArrayList<UserDO>(20);



    public List<UserDO> getUserDOs()
    {
        return userDOs;
    }
    public void setUserDOs(List<UserDO> userDOs)
    {
        this.userDOs = userDOs;
    }
    public int getLevel()
    {
        return level;
    }
    public void setLevel(int level)
    {
        this.level = level;
    }
    public String getCode()
    {
        return code;
    }
    public void setCode(String code)
    {
        this.code = code;
    }
    public String getParentId()
    {
        return parentId;
    }
    public void setParentId(String parentId)
    {
        this.parentId = parentId;
    }
    public boolean isLeafFlg()
    {
        return leafFlg;
    }
    public void setLeafFlg(boolean leafFlg)
    {
        this.leafFlg = leafFlg;
    }
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("OrgDO [level=");
        builder.append(level);
        builder.append(", code=");
        builder.append(code);
        builder.append(", parentId=");
        builder.append(parentId);
        builder.append(", leafFlg=");
        builder.append(leafFlg);
        builder.append(", userDOs=");
        builder.append(userDOs);
        builder.append(", toString()=");
        builder.append(super.toString());
        builder.append("]");
        return builder.toString();
    }


}
