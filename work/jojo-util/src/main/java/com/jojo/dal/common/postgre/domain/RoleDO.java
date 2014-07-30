package com.jojo.dal.common.postgre.domain;

import java.util.ArrayList;
import java.util.List;

import com.jojo.util.pojo.BasePOJO;

/**
 * <summary>
 * [角色]<br>
 * <br>
 * </summary>
 *
 * @author jojo
 *
 */
public class RoleDO extends BasePOJO
{

    /**   */
    private static final long serialVersionUID = 1102479682285182713L;

    private List<PrivilegeDO> privilegeDOs = new ArrayList<PrivilegeDO>(200);

    public List<PrivilegeDO> getPrivilegeDOs()
    {
        return privilegeDOs;
    }

    public void setPrivilegeDOs(List<PrivilegeDO> privilegeDOs)
    {
        this.privilegeDOs = privilegeDOs;
    }


}
