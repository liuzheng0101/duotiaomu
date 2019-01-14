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

public class TwoAdapter extends RecyclerView.Adapter<TwoAdapter.ViewHolder> {
    private Context context;
    private List<UserInfo.Data.Fenlei> list;
    public TwoAdapter(Context context, List<UserInfo.Data.Fenlei> list) {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final View view = LayoutInflater.from(context).inflate(R.layout.item_two_one, viewGroup, false);
        final ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.two_name.setText(list.get(i).getName());
        Glide.with(context).load(list.get(i).getIcon()).into(viewHolder.img);
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView two_name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.img);
            two_name=itemView.findViewById(R.id.two_name);
        }
    }
}
