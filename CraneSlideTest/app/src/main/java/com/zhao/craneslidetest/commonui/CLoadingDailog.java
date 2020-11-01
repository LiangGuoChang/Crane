package com.zhao.craneslidetest.commonui;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.widget.ImageView;

import com.zhao.craneslidetest.R;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

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
    }

    public void setMsg(String msg) {
        this.msg = msg;
        initView();
    }

    private void initView() {
        setContentView(R.layout.cl_loading_layout);
    }


}
