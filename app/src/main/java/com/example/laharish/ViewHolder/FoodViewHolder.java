package com.example.laharish.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.laharish.Interface.ItemClickListener;
import com.example.laharish.R;

public class FoodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtmenuName;
    public ImageView imgview;

    private ItemClickListener itemClickListener;


    public FoodViewHolder(View itemView) {
        super(itemView);

        txtmenuName=itemView.findViewById(R.id.food_namae);
        imgview=itemView.findViewById(R.id.food_list);

        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
         itemClickListener.onClick(v,getAdapterPosition(),false);
    }
}
