package com.jojo.util.biz.bo;

public class UserBO extends BaseBO
{

    /**   */
    private static final long serialVersionUID = -3351494629582171038L;


    private String userName;
    private int age;

    private String usrId;
    private int sex;
    private String email;
    private String tel;
    private String mobile;
    private String pwd;

    public UserBO(){}

    public String getUserName()
    {
        return userName;
    }
    public void setUserName(String userName)
    {
        this.userName = userName;
    }
    public int getAge()
    {
        return age;
    }
    public void setAge(int age)
    {
        this.age = age;
    }
    public String getUsrId()
    {
        return usrId;
    }
    public void setUsrId(String usrId)
    {
        this.usrId = usrId;
    }
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

}
