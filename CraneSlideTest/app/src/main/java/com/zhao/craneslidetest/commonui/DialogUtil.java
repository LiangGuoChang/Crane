package com.zhao.craneslidetest.commonui;

import android.app.Activity;
import android.app.Dialog;

/**
 * @author LiangGuoChang
 * @date 2020/11/2
 * @description
 */
public class DialogUtil {

    public static void showDialogSafe(Dialog dialog) {
        if (dialog == null || dialog.isShowing() || ((dialog.getContext() instanceof Activity) && ((Activity) dialog.getContext()).isFinishing())) {
            return;
        }
        dialog.show();
    }

    public static void closeDialogSafe(Dialog dialog) {
        if (dialog == null || !dialog.isShowing()) {
            return;
        }
        dialog.cancel();
    }
}