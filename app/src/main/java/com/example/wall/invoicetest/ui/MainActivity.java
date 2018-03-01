package com.example.wall.invoicetest.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wall.invoicetest.R;
import com.example.wall.invoicetest.adapter.Ad_List_mainDrawer;
import com.example.wall.invoicetest.adapter.Ad_RV_main;
import com.example.wall.invoicetest.base.BaseActivity;
import com.example.wall.invoicetest.util.StatusBarHeight;

import java.util.ArrayList;
import java.util.List;

import cn.jiguang.analytics.android.api.JAnalyticsInterface;

/**
 * 首页
 */
public class MainActivity extends BaseActivity {
    private Toolbar toolbar_main;
    private TextView tv_title;
    private RecyclerView rv_main;
    private LinearLayoutManager linearLayoutManager;
    private List<String> iconList = new ArrayList<>();
    private List<String> titleList = new ArrayList<>();
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout drawerLayout;
    private ListView lv_drawer;
    private Ad_List_mainDrawer ad_rv_mainDrawer;
    private List<String> mianIconList = new ArrayList<>();
    private List<String> mainTitleList = new ArrayList<>();
    private Typeface typeface;

    @Override
    public int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    public void init() {
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        ViewGroup decorViewGroup = (ViewGroup) window.getDecorView();
        View statusBarView = new View(window.getContext());
        int statusBarHeight = new StatusBarHeight(MainActivity.this).getStatusBarHeight();
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, statusBarHeight);
        params.gravity = Gravity.TOP;
        statusBarView.setLayoutParams(params);
        statusBarView.setBackgroundColor(getResources().getColor(R.color.status_bar));
        decorViewGroup.addView(statusBarView);

        typeface = Typeface.createFromAsset(getAssets(),"iconfont/iconfont.ttf");
        toolbar_main = (Toolbar) findViewById(R.id.toolbar_main);
        tv_title = (TextView) findViewById(R.id.tv_toolbar_normalTitle);
        rv_main = (RecyclerView) findViewById(R.id.rv_main);

        drawerLayout = (DrawerLayout) findViewById(R.id.cdl_main);
        lv_drawer = (ListView) findViewById(R.id.lv_main_drawer);
        toolbar_main.setTitle("");
        tv_title.setText(R.string.title_main);
        toolbar_main.setBackgroundColor(getResources().getColor(R.color.status_bar));
        setSupportActionBar(toolbar_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public void loadData() {
        titleList.add(getResources().getString(R.string.title_upPwd));
        titleList.add(getResources().getString(R.string.title_aboutUS));
        titleList.add(getResources().getString(R.string.title_versionUP));
        titleList.add(getResources().getString(R.string.title_setting));
        titleList.add(getResources().getString(R.string.title_logOut));

        iconList.add("\ue64c");
        iconList.add("\ue649");
        iconList.add("\ue63c");
        iconList.add("\ue61d");
        iconList.add("\ue614");

        mainTitleList.add(getResources().getString(R.string.title_invoiceTest));
        mainTitleList.add(getResources().getString(R.string.title_voucher));
        mainTitleList.add(getResources().getString(R.string.title_eximCancle));
        mianIconList.add("\ue72a");
        mianIconList.add("\ue610");
        mianIconList.add("\ue619");

    }

    @Override
    public void work() {
        mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar_main, R.string.open, R.string.close) {
        };
        mDrawerToggle.syncState();
        drawerLayout.setDrawerListener(mDrawerToggle);
        View headerView = getLayoutInflater().inflate(R.layout.rv_headview, null);
        lv_drawer.addHeaderView(headerView);
        ((TextView) (headerView.findViewById(R.id.tv_drawerHead_userName))).setText(myApplication.getUserName());
        ad_rv_mainDrawer = new Ad_List_mainDrawer(this,iconList,titleList);
        lv_drawer.setAdapter(ad_rv_mainDrawer);

        lv_drawer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 1:
                        Intent pwdIntent = new Intent(MainActivity.this,ModifyPwdActivity.class);
                        startActivity(pwdIntent);
                        break;
                    case 2:
                        Intent aboutIntent = new Intent(MainActivity.this,AboutUSActivity.class);
                        startActivity(aboutIntent);
                        break;
                    case 3:
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this,R.string.toast_comingSoon,Toast.LENGTH_SHORT).show();
                                return;
                            }
                        });
                        break;
                    case 4:
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this,R.string.toast_comingSoon,Toast.LENGTH_SHORT).show();
                                return;
                            }
                        });
                        break;
                    case 5:
                        Intent logOutIntent = new Intent(MainActivity.this,LoginActivity.class);
                        startActivity(logOutIntent);
                        finish();
                        break;
                    default:
                        break;
                }
            }
        });

        linearLayoutManager = new LinearLayoutManager(this);
        rv_main.setLayoutManager(linearLayoutManager);
        Ad_RV_main ad_rv_main = new Ad_RV_main(MainActivity.this,mianIconList,mainTitleList);
        rv_main.setAdapter(ad_rv_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        JAnalyticsInterface.onPageStart(this,this.getClass().getCanonicalName());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        JAnalyticsInterface.onPageEnd(this,this.getClass().getCanonicalName());
    }
}
