package com.dommy.loading.listener;

import android.app.Activity;
import android.view.View;

/**
 * 返回事件监听
 */
public class BtnBackListener implements View.OnClickListener{
    private Activity activity;

    public BtnBackListener(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onClick(View view) {
        activity.finish();
    }
}
