package com.example.wall.invoicetest.ui;

import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.wall.invoicetest.R;
import com.example.wall.invoicetest.base.BaseActivity;
import com.example.wall.invoicetest.util.StatusBarHeight;

/**
 * 忘记密码
 * Created by Wall on 2018/1/31.
 */
public class ForgetPwdActivity extends BaseActivity implements View.OnClickListener {
    private Toolbar toolbar_forgetPwd;
    private TextView tv_title;
    private EditText ed_chechkCode,ed_newPwd;
    private Button btn_code;

    private Button btn_submit;
    @Override
    public int getLayoutID() {
        return R.layout.activity_forgetpwd;
    }

    @Override
    public void init() {
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        ViewGroup decorViewGroup = (ViewGroup) window.getDecorView();
        View statusBarView = new View(window.getContext());
        int statusBarHeight = new StatusBarHeight(ForgetPwdActivity.this).getStatusBarHeight();
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, statusBarHeight);
        params.gravity = Gravity.TOP;
        statusBarView.setLayoutParams(params);
        statusBarView.setBackgroundColor(getResources().getColor(R.color.toolbar_back));
        decorViewGroup.addView(statusBarView);

        toolbar_forgetPwd = (Toolbar) findViewById(R.id.toolbar_forgetPwd);
        tv_title = (TextView) findViewById(R.id.tv_toolbar_normalTitle);
        btn_submit = (Button) findViewById(R.id.btn_forgetPwd_submint);
        btn_code = (Button) findViewById(R.id.btn_forgetPwd_code);

        btn_code.setOnClickListener(this);
        btn_submit.setOnClickListener(this);

        toolbar_forgetPwd.setTitle("");
        tv_title.setText(R.string.title_backPwd);
        toolbar_forgetPwd.setBackgroundColor(getResources().getColor(R.color.toolbar_back));
        setSupportActionBar(toolbar_forgetPwd);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar_forgetPwd.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    @Override
    public void loadData() {

    }

    @Override
    public void work() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_forgetPwd_submint:

                break;
            case R.id.btn_forgetPwd_code:

                break;
            default:
                break;
        }
    }
}
