//打折的摘要信息，包括商家的logo和打折信息的标题，用于显示在打折信息列表中
package com.weng.discountinformer;

/**
 * Created by Weng Anxiang on 2017/3/11.
 */

public class DiscountAbstractInfo {
    private String title; //标题
    private int sellerLogoId; //商家logo的图片

    public DiscountAbstractInfo(String title, int sellerLogoId)
    {
        this.title = title;
        this.sellerLogoId = sellerLogoId;
    }

    public String getTitle()
    {
        return title;
    }
    public int getImageId()
    {
        return sellerLogoId;
    }
}
