package com.wanyi.uiframe.comment;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;

/**
 * author: wu
 * date: on 2019/3/8.
 * describe:
 */
public class SoftKeyHideShow {

    //隐藏或显示软键盘
    public static void HideShowSoftKey(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }
}
