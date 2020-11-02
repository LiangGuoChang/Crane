package com.zhao.craneslidetest.commonui;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.zhao.craneslidetest.R;

/**
 * @Author : LiangGuoChang
 * @Date : 2020/11/1
 * @描述 :
 */
public class CLoadingDailog extends AlertDialog {

    private String msg;
    private ImageView loadingImg;
    private AnimationDrawable animationDrawable;

    protected CLoadingDailog(@NonNull Context context, String msg) {
        super(context, R.style.loadingStyle);
        this.msg = msg;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    public void setMsg(String msg) {
        this.msg = msg;
        initView();
    }

    private void initView() {
        setContentView(R.layout.cl_loading_layout);
        setCanceledOnTouchOutside(false);
        TextView loadingMsg = findViewById(R.id.loading_msg);
        loadingImg = findViewById(R.id.loading_img);
        if (null != loadingImg) {
            animationDrawable = (AnimationDrawable) loadingImg.getDrawable();
            animationDrawable.start();
        }
        if (loadingMsg != null && !TextUtils.isEmpty(msg)) {
            loadingMsg.setText(msg);
        }
    }

    @Override
    public void show() {
        super.show();
        animationDrawable.start();
    }

    @Override
    public void onDetachedFromWindow() {
        animationDrawable.stop();
        super.onDetachedFromWindow();
    }
}
