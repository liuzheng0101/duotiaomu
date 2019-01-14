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

public class FourAdapter extends RecyclerView.Adapter<FourAdapter.ViewHolder> {
    private Context context;
    private List<UserInfo.Data.Tui.LL> list;
    public FourAdapter(Context context, List<UserInfo.Data.Tui.LL> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.item_four_one,viewGroup,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.four_price.setText(list.get(i).getPrice());
        viewHolder.four_title.setText(list.get(i).getTitle());
        String[] spilt=list.get(i).getImages().split("!");
        Glide.with(context).load(spilt[0]).into(viewHolder.four_img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView four_img;
        private TextView four_price;
        private TextView four_title;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            four_img=itemView.findViewById(R.id.four_img);
            four_price=itemView.findViewById(R.id.four_price);
            four_title=itemView.findViewById(R.id.four_title);
        }
    }
}
