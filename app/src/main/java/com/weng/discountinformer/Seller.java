package com.weng.discountinformer;

import android.media.Image;

import com.weng.discountinformer.util.MD5Encrypt;

import java.util.List;

/**
 * Created by Weng Anxiang on 2017/2/23.
 */
//商家信息
public class Seller implements User {
    private final String userID;
    private String pass_word;
    private String name;
    private String phoneNum;
    private String address;
    private String email;
    private List<String> labelList;//商家标签
    private Image logo; //商家的logo
    //private SellerAttribute sellerAttribute;
    private List<DiscountInfo> publishedList;//商家已发布的打折信息
    private List<String> mFans;     //关注商家的用户
    //enum SellerAttribute{CLOTHES, FOOD, LIVING, GOING};


    public Seller(String userID)
    {
        this.userID = userID;
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
    public void setPhoneNum(String phoneNum)
    {
        this.phoneNum = phoneNum;
    }
    public String getPhoneNum()
    {
        return phoneNum;
    }
    public void setAddress(String address)
    {
        this.address = address;
    }
    public String getAddress()
    {
        return address;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }
    public String getEmail()
    {
        return email;
    }

   /*
    public void setSellerAttribute(SellerAttribute attribute)
    {
        this.sellerAttribute = attribute;
    }
    */

    public void addLabel(String label)
    {
        labelList.add(label);
    }
    public void removeLabel()//删除标签
    {

    }






    class DiscountInfo          //用于储存打折的详细信息
    {
        private String title;   //打折信息标题，显示在打折列表中
        private String content;//打折具体信息
        private Image image;    //可能附带的图片
    }

}
