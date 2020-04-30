package com.wanyi.uiframe.dkplayer.util;

import android.view.View;
import android.view.ViewParent;
import android.widget.FrameLayout;

public final class Utils {

    private Utils() {
    }


    /**
     * 将View从父控件中移除
     */
    public static void removeViewFormParent(View v) {
        if (v == null) return;
        ViewParent parent = v.getParent();
        if (parent instanceof FrameLayout) {
            ((FrameLayout) parent).removeView(v);
        }
    }


}
