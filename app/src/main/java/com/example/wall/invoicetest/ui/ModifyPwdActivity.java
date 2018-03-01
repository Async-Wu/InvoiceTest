package com.example.wall.invoicetest.ui;

import android.content.DialogInterface;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
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
 * Created by Wall on 2018/1/31.
 */
public class ModifyPwdActivity extends BaseActivity implements View.OnClickListener {
    private Toolbar toolbar_forgetPwd;
    private TextView tv_title;
    private TextInputLayout til_old,til_new,til_newAgain;
    private EditText ed_old,ed_new,ed_newAgain;
    private Button btn_submit;
    @Override
    public int getLayoutID() {
        return R.layout.activity_modify_pwd;
    }

    @Override
    public void init() {
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        ViewGroup decorViewGroup = (ViewGroup) window.getDecorView();
        View statusBarView = new View(window.getContext());
        int statusBarHeight = new StatusBarHeight(ModifyPwdActivity.this).getStatusBarHeight();
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, statusBarHeight);
        params.gravity = Gravity.TOP;
        statusBarView.setLayoutParams(params);
        statusBarView.setBackgroundColor(getResources().getColor(R.color.toolbar_back));
        decorViewGroup.addView(statusBarView);

        toolbar_forgetPwd = (Toolbar) findViewById(R.id.toolbar_modifyPwd);
        tv_title = (TextView) findViewById(R.id.tv_toolbar_normalTitle);
        til_old = (TextInputLayout) findViewById(R.id.til_PwdUP_old);
        til_new = (TextInputLayout) findViewById(R.id.til_PwdUP_new);
        til_newAgain = (TextInputLayout) findViewById(R.id.til_PwdUP_newAgain);
        ed_old = (EditText) findViewById(R.id.ed_pwdUP_old);
        ed_new = (EditText) findViewById(R.id.ed_pwdUP_new);
        ed_newAgain = (EditText) findViewById(R.id.ed_pwdUP_newAgain);
        btn_submit = (Button) findViewById(R.id.btn_modifyPwd);

        btn_submit.setOnClickListener(this);

        toolbar_forgetPwd.setTitle("");
        tv_title.setText(R.string.title_modifyPwd);
        toolbar_forgetPwd.setBackgroundColor(getResources().getColor(R.color.toolbar_back));
        setSupportActionBar(toolbar_forgetPwd);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar_forgetPwd.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        til_old.setHint(getResources().getString(R.string.hint_pwdUPOld));
        til_new.setHint(getResources().getString(R.string.hint_pwdUPnew));
        til_newAgain.setHint(getResources().getString(R.string.hint_pwdUPnewAagin));

        ed_old.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

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

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_forgetPwd_submint:
                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        builder.setTitle(R.string.title_alertDialog)
                                .setMessage(R.string.toast_upPwd)
                                .setNegativeButton(R.string.btn_cancle,null)
                                .setPositiveButton(R.string.btn_sure, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        String oldPwd = ed_old.getText().toString();
                                        if("".equals(oldPwd)){
                                            til_old.setError(getResources().getString(R.string.hint_pwdNowNull));
                                            til_old.setErrorEnabled(true);
                                        }else {
                                            til_old.setErrorEnabled(false);
                                        }
                                        String newPwd = ed_new.getText().toString();
                                        if("".equals(newPwd)){
                                            til_new.setError(getResources().getString(R.string.hint_pwdNowNull));
                                            til_new.setErrorEnabled(true);
                                        }else {
                                            til_new.setErrorEnabled(false);
                                        }
                                        String newAgainPwd = ed_newAgain.getText().toString();
                                        if("".equals(newAgainPwd)){
                                            til_newAgain.setError(getResources().getString(R.string.hint_pwdNowNull));
                                            til_newAgain.setErrorEnabled(true);
                                        }else {
                                            til_newAgain.setErrorEnabled(false);
                                        }
                                    }
                                }).show();
                    }
                });
                break;
            default:
                break;
        }
    }
}
