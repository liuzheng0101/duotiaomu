package com.example.moniti.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.moniti.R;
import com.example.moniti.activity.ScanActivity;

public class Fragment_Home extends Fragment {
    private ImageView scan;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=LayoutInflater.from(getContext()).inflate(R.layout.home_fragment,container,false);
        scan=view.findViewById(R.id.scan);
        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),ScanActivity.class));
            }
        });
        return view;
    }
}
