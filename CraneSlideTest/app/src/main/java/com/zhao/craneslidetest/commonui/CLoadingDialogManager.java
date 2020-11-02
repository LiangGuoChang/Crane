package com.zhao.craneslidetest.commonui;

import android.content.Context;

/**
 * @author LiangGuoChang
 * @date 2020/11/2
 * @description
 */
public class CLoadingDialogManager {
    private Context mContext;
    private CLoadingDialog mLoadingDialog;

    public CLoadingDialogManager(Context context) {
        this.mContext = context;
    }

    public void showLoadingDialog(String msg) {
        if (null == mLoadingDialog) {
            mLoadingDialog = new CLoadingDialog(mContext, msg);
        } else {
            mLoadingDialog.setMsg(msg);
        }

        mLoadingDialog.setCanceledOnTouchOutside(false);
        mLoadingDialog.setCancelable(true);
        DialogUtil.showDialogSafe(mLoadingDialog);
    }

    public void dismissLoadingDialog() {
        DialogUtil.closeDialogSafe(mLoadingDialog);
    }


}