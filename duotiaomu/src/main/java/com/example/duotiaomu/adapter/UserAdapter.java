package com.example.duotiaomu.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.duotiaomu.R;
import com.example.duotiaomu.bean.UserInfo;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.recker.flybanner.FlyBanner;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends XRecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int ONE=0;
    private final int TWO=1;
    private final int THREE=2;
    private final int FOUR=3;
    private Context context;
    private UserInfo.Data list;
    public UserAdapter(Context context) {
        this.context = context;
        this.list=new UserInfo.Data();
    }
    public void setProduct(UserInfo.Data data) {
        if (data!=null){
            list=data;
        }
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (getItemViewType(i)==ONE){
            View view=LayoutInflater.from(context).inflate(R.layout.item_one,viewGroup,false);
            final OneVH oneVH = new OneVH(view);
            return oneVH;
        }else if (getItemViewType(i)==TWO){
            View view=LayoutInflater.from(context).inflate(R.layout.item_two,viewGroup,false);
            final TwoVH twoVH = new TwoVH(view);
            return twoVH;
        }else if (THREE == getItemViewType(i)) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_three, viewGroup, false);
            final ThreeVH threeVH = new ThreeVH(view);
            return threeVH;
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.item_four, viewGroup, false);
            final FourVH fourVH = new FourVH(view);
            return fourVH;
        }
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (list.getBanner()!=null&&list.getFenlei()!=null&&list.getMiaosha()!=null&&list.getTuijian()!=null){
            if (viewHolder instanceof OneVH){
                List<String> list1=new ArrayList<>();
                for (int j=0;j<list.getBanner().size();j++){
                    list1.add(list.getBanner().get(i).getIcon());
                }
                ((OneVH) viewHolder).banner.setImagesUrl(list1);
            }else if (viewHolder instanceof TwoVH){
                ((TwoVH) viewHolder).rvv.setLayoutManager(new GridLayoutManager(context,2,GridLayoutManager.HORIZONTAL,false));
                ((TwoVH) viewHolder).rvv.setAdapter(new TwoAdapter(context,list.getFenlei()));
            }else if (viewHolder instanceof ThreeVH){
                ((ThreeVH) viewHolder).name.setText(list.getMiaosha().getName());
                ((ThreeVH) viewHolder).three_rv.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
                ((ThreeVH) viewHolder).three_rv.setAdapter(new ThreeAdapter(context,list.getMiaosha().getList()));
            }else{
                ((FourVH) viewHolder).four_rv.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
                ((FourVH) viewHolder).four_rv.setAdapter(new FourAdapter(context,list.getTuijian().getList()));
            }
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    @Override
    public int getItemViewType(int position) {
        if (position==0){
            return ONE;
        }else if (position==1){
            return TWO;
        }else if (position==2){
            return THREE;
        }else{
            return FOUR;
        }
    }
    //banner
    private class OneVH extends RecyclerView.ViewHolder {
        private FlyBanner banner;
        public OneVH(View view) {
            super(view);
            banner=view.findViewById(R.id.fly);
        }
    }
    //条目3
    private class ThreeVH extends RecyclerView.ViewHolder {
        private RecyclerView three_rv;
        private TextView name;
        public ThreeVH(View view) {
            super(view);
            three_rv=view.findViewById(R.id.three_rv);
            name=view.findViewById(R.id.three_name);
        }
    }
    private class FourVH extends RecyclerView.ViewHolder {
        private RecyclerView four_rv;
        public FourVH(View view) {
            super(view);
            four_rv=view.findViewById(R.id.four_rv);
        }
    }
    //条目2
    private class TwoVH extends RecyclerView.ViewHolder {
        private RecyclerView rvv;
        public TwoVH(@NonNull View itemView) {
            super(itemView);
            rvv=itemView.findViewById(R.id.rvv);
        }
    }
}
