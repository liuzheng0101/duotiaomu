package com.example.duotiaomu.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.duotiaomu.R;
import com.example.duotiaomu.bean.UserInfo;

import java.util.List;

public class ThreeAdapter extends RecyclerView.Adapter<ThreeAdapter.ViewHolder> {
    private Context context;
    private List<UserInfo.Data.Miao.Li> list;
    public ThreeAdapter(Context context, List<UserInfo.Data.Miao.Li> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final View view = LayoutInflater.from(context).inflate(R.layout.item_three_one, viewGroup, false);
        final ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.three_price.setText(list.get(i).getPrice());
        final String[] split = list.get(i).getImages().split("!");
        Glide.with(context).load(split[0]).into(viewHolder.three_img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView three_img;
        private TextView three_price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            three_img=itemView.findViewById(R.id.three_img);
            three_price=itemView.findViewById(R.id.three_price);
        }
    }
}
