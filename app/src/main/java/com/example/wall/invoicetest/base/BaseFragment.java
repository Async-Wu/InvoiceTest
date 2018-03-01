package com.example.wall.invoicetest.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Wall on 2018/1/29.
 */
public abstract class BaseFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getViewResid(), container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        init(view);
        loadDatas();
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        work();
        super.onActivityCreated(savedInstanceState);
    }

    public void work(){}

    /**
     * 加载数据方法，由子类具体实现
     */
    public  void loadDatas(){}

    /**
     * 初始化方法
     * @param view
     */
    public  void init(View view){}

    /**
     * 获得布局id
     * @return
     */
    public abstract int getViewResid();
}
