package com.example.wall.invoicetest.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.wall.invoicetest.R;
import com.example.wall.invoicetest.base.BaseActivity;

import cn.jiguang.analytics.android.api.JAnalyticsInterface;
import cn.jiguang.analytics.android.api.LoginEvent;

/**
 * 登录
 * Created by Wall on 2018/1/30.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private TextInputLayout til_user,til_pwd;
    private EditText ed_pwd,ed_user;
    private TextView tv_forget;
    private Button btn_login;
    @Override
    public int getLayoutID() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        JAnalyticsInterface.onPageStart(this,this.getClass().getCanonicalName());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//此FLAG可使状态栏透明，且当前视图在绘制时，从屏幕顶端开始即top = 0开始绘制，这也是实现沉浸效果的基础
            this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);//可不加
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        JAnalyticsInterface.onPageEnd(this,this.getClass().getCanonicalName());
    }

    @Override
    public void init() {
        til_user = (TextInputLayout) findViewById(R.id.til_user);
        til_pwd = (TextInputLayout) findViewById(R.id.til_pwd);
        ed_user = (EditText) findViewById(R.id.ed_user);
        ed_pwd = (EditText) findViewById(R.id.ed_pwd);
        tv_forget = (TextView) findViewById(R.id.tv_forget);
        btn_login = (Button) findViewById(R.id.btn_login);

        tv_forget.setOnClickListener(this);
        btn_login.setOnClickListener(this);

        til_user.setHint(getResources().getString(R.string.hint_user));
        til_pwd.setHint(getResources().getString(R.string.hint_pwd));
        ed_user.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (s.length() > 6) {
                    til_user.setError(getResources().getString(R.string.hint_userOver));
                    til_user.setErrorEnabled(true);
                } else {
                    til_user.setErrorEnabled(false);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    @Override
    public void loadData() {

    }

    @Override
    public void work() {
        //text
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_forget:

                Intent intent = new Intent(LoginActivity.this,ForgetPwdActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_login:
                boolean isNest = true;
                String user = ed_user.getText().toString();
                if("".equals(user)){
                    til_user.setError(getResources().getString(R.string.hint_userNull));
                    til_user.setErrorEnabled(true);
                    isNest = false;
                }else {
                    til_user.setErrorEnabled(false);
                }
                String pwd = ed_pwd.getText().toString();
                if("".equals(pwd)){
                    til_pwd.setError(getResources().getString(R.string.hint_pwdNull));
                    til_pwd.setErrorEnabled(true);
                    isNest = false;
                }else {
                    til_pwd.setErrorEnabled(false);
                }
                if(isNest){
                    LoginEvent lEvent = new LoginEvent("AndroidAPP",true);
                    lEvent.addKeyValue("key1","value1").addKeyValue("key2","value2");
                    JAnalyticsInterface.onEvent(this, lEvent);
                    myApplication.setUserName(ed_user.getText().toString());
                    Intent intentMian = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intentMian);
                    finish();
                }
                break;
            default:
                break;
        }
    }


}
