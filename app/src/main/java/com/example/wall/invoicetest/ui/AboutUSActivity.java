package com.example.wall.invoicetest.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.wall.invoicetest.R;
import com.example.wall.invoicetest.base.BaseActivity;
import com.example.wall.invoicetest.util.StatusBarHeight;
import com.example.wall.invoicetest.widget.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

import cn.jiguang.analytics.android.api.BrowseEvent;
import cn.jiguang.analytics.android.api.JAnalyticsInterface;
import cn.jiguang.analytics.android.api.RegisterEvent;

/**
 * 关于我们
 * Created by Wall on 2018/2/1.
 */
public class AboutUSActivity extends BaseActivity {
    private TextView tv_title,tv_introduce,tv_business,tv_phone,tv_address;
    private Toolbar toolbar_aboutus;
    private Banner banner;
    private List<String> titles = new ArrayList<>();
    private List<Integer> images = new ArrayList<>();
    @Override
    public int getLayoutID() {
        return R.layout.activity_aboutus;
    }

    @Override
    public void init() {
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        ViewGroup decorViewGroup = (ViewGroup) window.getDecorView();
        View statusBarView = new View(window.getContext());
        int statusBarHeight = new StatusBarHeight(AboutUSActivity.this).getStatusBarHeight();
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, statusBarHeight);
        params.gravity = Gravity.TOP;
        statusBarView.setLayoutParams(params);
        statusBarView.setBackgroundColor(getResources().getColor(R.color.status_bar));
        decorViewGroup.addView(statusBarView);

        toolbar_aboutus = (Toolbar) findViewById(R.id.toolbar_aboutUS);
        tv_title = (TextView) findViewById(R.id.tv_toolbar_normalTitle);
        banner = (Banner) findViewById(R.id.banner_aboutUS);
        tv_introduce = (TextView) findViewById(R.id.tv_aboutUS_introduce);
        tv_business = (TextView) findViewById(R.id.tv_aboutUS_business);
        tv_phone = (TextView) findViewById(R.id.tv_aboutUS_phone);
        tv_address = (TextView) findViewById(R.id.tv_aboutUS_address);

        toolbar_aboutus.setTitle("");
        tv_title.setText(R.string.title_aboutUS);
        toolbar_aboutus.setBackgroundColor(getResources().getColor(R.color.status_bar));
        setSupportActionBar(toolbar_aboutus);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar_aboutus.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        JAnalyticsInterface.onPageStart(this,this.getClass().getCanonicalName());
    }

    @Override
    protected void onStart() {
        super.onStart();
        BrowseEvent bEvent = new BrowseEvent("aboutus","关于我们","news",30);
        bEvent.addKeyValue("key1","value1").addKeyValue("key2","value2");
        JAnalyticsInterface.onEvent(this, bEvent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        JAnalyticsInterface.onPageEnd(this,this.getClass().getCanonicalName());
    }

    @Override
    public void loadData() {

        images.add(R.mipmap.back);
        images.add(R.mipmap.banner_back);
        images.add(R.mipmap.drawer_bg);

        titles.add(getResources().getString(R.string.banner_one));
        titles.add(getResources().getString(R.string.banner_two));
        titles.add(getResources().getString(R.string.banner_three));
    }

    @Override
    public void work() {
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
        banner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(2000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.RIGHT);
        //banner设置方法全部调用完毕时最后调用
        banner.start();


        String introduce = getResources().getString(R.string.desc_campany);
        tv_introduce.setText("\u3000\u3000" + introduce);
        String business = getResources().getString(R.string.desc_business);
        tv_business.setText("\u3000\u3000" + business);
        String phone = getResources().getString(R.string.desc_phone);
        tv_phone.setText("\u3000\u3000" + phone);
        String address = getResources().getString(R.string.desc_address);
        tv_address.setText("\u3000\u3000" + address);

    }

    @Override
    protected void onStop() {
        super.onStop();
        banner.stopAutoPlay();
    }
}
