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

    private String usrId;
    private int sex;
    private String email;
    private String tel;
    private String mobile;
    private String pwd;

    /**
     * 冗余，查询用户角色使用
     */
    private String roleId;
    /**
     * 冗余，查询用户角色使用
     */
    private String roleName;

    /**
     * 冗余，查询用户角色使用
     */
    private List<RoleDO> roles = new ArrayList<RoleDO>(10);

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

    public String getUsrId()
    {
        return usrId;
    }

    public void setUsrId(String usrId)
    {
        this.usrId = usrId;
    }

    public String getRoleId()
    {
        return roleId;
    }
    public void setRoleId(String roleId)
    {
        this.roleId = roleId;
    }
    public String getRoleName()
    {
        return roleName;
    }
    public void setRoleName(String roleName)
    {
        this.roleName = roleName;
    }
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("UserDO [usrId=");
        builder.append(usrId);
        builder.append(", sex=");
        builder.append(sex);
        builder.append(", email=");
        builder.append(email);
        builder.append(", tel=");
        builder.append(tel);
        builder.append(", mobile=");
        builder.append(mobile);
        builder.append(", pwd=");
        builder.append(pwd);
        builder.append(", roleId=");
        builder.append(roleId);
        builder.append(", roleName=");
        builder.append(roleName);
        builder.append(", orgUserDOs=");
        builder.append(orgUserDOs);
        builder.append("]");
        return builder.toString();
    }
    public List<RoleDO> getRoles()
    {
        return roles;
    }
    public void setRoles(List<RoleDO> roles)
    {
        this.roles = roles;
    }


}
