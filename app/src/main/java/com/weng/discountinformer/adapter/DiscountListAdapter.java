package com.weng.discountinformer.adapter;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.weng.discountinformer.DiscountAbstractInfo;
import com.weng.discountinformer.R;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Weng Anxiang on 2017/6/14.
 */

public class DiscountListAdapter extends ArrayAdapter<DiscountAbstractInfo> {
    private int resourceId;

    public DiscountListAdapter(Context context, int textViewResourceId, List<DiscountAbstractInfo> objects)
    {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DiscountAbstractInfo abstractInfo = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        ImageView sellerLogo = (ImageView) view.findViewById(R.id.seller_logo);
        TextView title = (TextView) view.findViewById(R.id.discout_title);
        sellerLogo.setImageResource(abstractInfo.getImageId());
        title.setText(abstractInfo.getTitle());
        return view;
    }
}
