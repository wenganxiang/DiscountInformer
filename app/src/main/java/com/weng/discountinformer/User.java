package com.weng.discountinformer;

/**
 * Created by Weng Anxiang on 2017/2/23.
 */
//用户的共有信息
public interface User {
    //void setUserID(String id);
    String getUserID();
    void setPassword(String password);
    String getPassword();
    void setPhoneNum(String num);
    String getPhoneNum();
}
