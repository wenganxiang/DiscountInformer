package com.weng.discountinformer;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Weng Anxiang on 2017/3/11.
 */
//recycleview的适配器
public class DiscountListTitleAdapter extends RecyclerView.Adapter<DiscountListTitleAdapter.ViewHolder>
{
    private List<DiscountAbstractInfo> mDiscountAbstractList = new ArrayList<>();//储存打折信息的标题和商家logo

    static class ViewHolder extends RecyclerView.ViewHolder
    {
        View sellerView;
        ImageView sellerLogoImage;//商家logo
        TextView discountTitle;//打折信息标题

        public ViewHolder(View view)
        {
            super(view);
            sellerView = view;  //保存最外层布局的实例
            sellerLogoImage = (ImageView) view.findViewById(R.id.seller_logo);
            discountTitle = (TextView) view.findViewById(R.id.discout_title);
        }
    }
    public DiscountListTitleAdapter(List<DiscountAbstractInfo> abstractList)
    {
        mDiscountAbstractList = abstractList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //加载打折信息列表的单项信息
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.discount_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.sellerView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //点击之后，调用相应的打折详细信息
            }
        });
        holder.sellerLogoImage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //点击之后，调用相应的打折详细信息
               //Intent intent = new Intent(, DiscountInfoActivity.class);

            }
        });
        holder.discountTitle.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //点击之后，调用相应的打折详细信息
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DiscountAbstractInfo info = mDiscountAbstractList.get(position);
        holder.discountTitle.setText(info.getTitle());
        holder.sellerLogoImage.setImageResource(info.getImageId());
    }

    @Override
    public int getItemCount() {
        return mDiscountAbstractList.size();
    }
}
