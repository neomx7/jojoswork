package com.jojo.biz.model;

public class UserBO extends BaseBO
{

    /**   */
    private static final long serialVersionUID = -3351494629582171038L;


    private String userName;
    private int age;



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

}
