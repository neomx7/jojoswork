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


    private List<UserDO> userDOs = new ArrayList<UserDO>(20);



    public List<UserDO> getUserDOs()
    {
        return userDOs;
    }
    public void setUserDOs(List<UserDO> userDOs)
    {
        this.userDOs = userDOs;
    }


}
