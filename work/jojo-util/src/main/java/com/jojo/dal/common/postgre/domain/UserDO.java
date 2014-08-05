package com.jojo.dal.common.postgre.domain;

import java.util.ArrayList;
import java.util.List;

import com.jojo.util.pojo.BasePOJO;

/**
 * <summary>
 * [用户对象]<br>
 * <br>
 * </summary>
 *
 * @author jojo
 *
 */
public class UserDO extends BasePOJO
{

    /**   */
    private static final long serialVersionUID = 2558434283338089842L;

    private int sex;
    private String email;
    private String tel;
    private String mobile;
    private String pwd;

    private List<OrgUserDO> orgUserDOs = new ArrayList<OrgUserDO>(10);


    public int getSex()
    {
        return sex;
    }
    public void setSex(int sex)
    {
        this.sex = sex;
    }
    public String getEmail()
    {
        return email;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }
    public String getTel()
    {
        return tel;
    }
    public void setTel(String tel)
    {
        this.tel = tel;
    }
    public String getMobile()
    {
        return mobile;
    }
    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }
    public String getPwd()
    {
        return pwd;
    }
    public void setPwd(String pwd)
    {
        this.pwd = pwd;
    }
    public List<OrgUserDO> getOrgUserDOs()
    {
        return orgUserDOs;
    }
    public void setOrgUserDOs(List<OrgUserDO> orgUserDOs)
    {
        this.orgUserDOs = orgUserDOs;
    }


}
