package com.dommy.loading.util;

import android.content.Context;
import android.graphics.Typeface;


/**
 * APP资源配置
 *
 * @author Dommy
 */
public class AppResource {
    private static Typeface typeface = null; // 公共字体

    /**
     * 获取自定义字体
     *
     * @param context
     * @return Typeface 字体
     */
    public static Typeface getTypeface(Context context) {
        if (typeface == null) {
            typeface = Typeface.createFromAsset(context.getAssets(), "fonts/AkzidenzGrotesk-LightCond.otf");
        }
        return typeface;
    }
}
