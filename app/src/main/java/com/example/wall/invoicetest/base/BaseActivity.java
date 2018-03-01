package com.example.wall.invoicetest.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.wall.invoicetest.MyApplication;

/**
 * Created by Wall on 2018/1/29.
 */
public abstract class BaseActivity extends AppCompatActivity{
    public MyApplication myApplication;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutID());
        myApplication = (MyApplication) this.getApplication();
        init();
        loadData();
        work();
    }

    public void init() {

    }

    public void loadData() {

    }

    public void work() {

    }

    public abstract int getLayoutID();

}
