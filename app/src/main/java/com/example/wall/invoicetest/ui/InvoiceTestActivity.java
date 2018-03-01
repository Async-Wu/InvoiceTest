package com.example.wall.invoicetest.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.acker.simplezxing.activity.CaptureActivity;
import com.example.wall.invoicetest.R;
import com.example.wall.invoicetest.base.BaseActivity;
import com.example.wall.invoicetest.util.StatusBarHeight;



/**
 * 发票验证
 * Created by Wall on 2018/2/2.
 */
public class InvoiceTestActivity extends BaseActivity implements View.OnClickListener {
    private Toolbar toolbar_invoice;
    private TextView tv_title;
    private EditText ed_code1, ed_code2;
    private Button btn_scan, btn_edit;
    private LinearLayout ll_edit;
    private static final int REQ_CODE_PERMISSION = 0x1111;


    @Override
    public int getLayoutID() {
        return R.layout.activity_invoicetest;
    }

    @Override
    public void init() {
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        ViewGroup decorViewGroup = (ViewGroup) window.getDecorView();
        View statusBarView = new View(window.getContext());
        int statusBarHeight = new StatusBarHeight(InvoiceTestActivity.this).getStatusBarHeight();
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, statusBarHeight);
        params.gravity = Gravity.TOP;
        statusBarView.setLayoutParams(params);
        statusBarView.setBackgroundColor(getResources().getColor(R.color.status_bar));
        decorViewGroup.addView(statusBarView);

        toolbar_invoice = (Toolbar) findViewById(R.id.toolbar_invoiceTest);
        tv_title = (TextView) findViewById(R.id.tv_toolbar_normalTitle);
        btn_scan = (Button) findViewById(R.id.btn_invoice_scan);
        ed_code1 = (EditText) findViewById(R.id.ed_invoice_code1);
        ed_code2 = (EditText) findViewById(R.id.ed_invoice_code2);
        btn_edit = (Button) findViewById(R.id.btn_invoice_edit);
        ll_edit = (LinearLayout) findViewById(R.id.ll_invoiceEdit);

        btn_scan.setOnClickListener(this);
        btn_edit.setOnClickListener(this);

        toolbar_invoice.setTitle("");
        tv_title.setText(R.string.title_invoiceTest);
        toolbar_invoice.setBackgroundColor(getResources().getColor(R.color.status_bar));
        setSupportActionBar(toolbar_invoice);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar_invoice.setNavigationOnClickListener(new View.OnClickListener() {
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CaptureActivity.REQ_CODE:
                switch (resultCode) {
                    case RESULT_OK:
                        ed_code1.setText(data.getStringExtra(CaptureActivity.EXTRA_SCAN_RESULT));  //or do sth
                        break;
                    case RESULT_CANCELED:
                        if (data != null) {
                            // for some reason camera is not working correctly
                            ed_code1.setText(data.getStringExtra(CaptureActivity.EXTRA_SCAN_RESULT));
                        }
                        break;
                }
                break;
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_invoice_scan:
                scan();
                break;
            case R.id.btn_invoice_edit:
                ll_edit.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
    }

    public void scan() {
        if (ContextCompat.checkSelfPermission(InvoiceTestActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            // Do not have the permission of camera, request it.
            ActivityCompat.requestPermissions(InvoiceTestActivity.this, new String[]{Manifest.permission.CAMERA}, REQ_CODE_PERMISSION);
        } else {
            // Have gotten the permission
            startCaptureActivityForResult();
        }
    }

    private void startCaptureActivityForResult() {
        Intent intent = new Intent(InvoiceTestActivity.this, CaptureActivity.class);
        Bundle bundle = new Bundle();
        bundle.putBoolean(CaptureActivity.KEY_NEED_BEEP, CaptureActivity.VALUE_BEEP);
        bundle.putBoolean(CaptureActivity.KEY_NEED_VIBRATION, CaptureActivity.VALUE_VIBRATION);
        bundle.putBoolean(CaptureActivity.KEY_NEED_EXPOSURE, CaptureActivity.VALUE_NO_EXPOSURE);
        bundle.putByte(CaptureActivity.KEY_FLASHLIGHT_MODE, CaptureActivity.VALUE_FLASHLIGHT_OFF);
        bundle.putByte(CaptureActivity.KEY_ORIENTATION_MODE, CaptureActivity.VALUE_ORIENTATION_AUTO);
        bundle.putBoolean(CaptureActivity.KEY_SCAN_AREA_FULL_SCREEN, CaptureActivity.VALUE_SCAN_AREA_FULL_SCREEN);
        bundle.putBoolean(CaptureActivity.KEY_NEED_SCAN_HINT_TEXT, CaptureActivity.VALUE_SCAN_HINT_TEXT);
        intent.putExtra(CaptureActivity.EXTRA_SETTING_BUNDLE, bundle);
        startActivityForResult(intent, CaptureActivity.REQ_CODE);
    }


}
