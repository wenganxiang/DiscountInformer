package com.weng.discountinformer;

import com.weng.discountinformer.util.MD5Encrypt;

import java.util.List;

/**
 * Created by Weng Anxiang on 2017/2/23.
 */
//买家信息
public class Buyer implements User {
    private final String userID;
   // private String userName;
    private String pass_word;
    private String name;
    private String phoneNum;
    private String email;
    private List<String> myCollection;//买家关注的店铺

    private String gender;  //性别
    private int age;


    public Buyer(String id)
    {
        this.userID = id;
    }
    public String getUserID()
    {
        return userID;
    }


    public void setPassword(String s)
    {
        pass_word = MD5Encrypt.encryptMD5(s);
    }
    public String getPassword()
    {
        return pass_word;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public String getName()
    {
        return name;
    }
    public void setPhoneNum(String num)
    {
        this.phoneNum = num;
    }
    public String getPhoneNum()
    {
        return phoneNum;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }
    public String getEmail()
    {
        return email;
    }
    public void setAge(int age)
    {
        this.age= age;
    }
    public void setGender(String gender)
    {
        this.gender = gender;
    }


    public int getAge() {
        return age;
    }
    public String getGender() {
        return gender;
    }

    public List<String> getMyCollection()
    {
        return myCollection;
    }
}
