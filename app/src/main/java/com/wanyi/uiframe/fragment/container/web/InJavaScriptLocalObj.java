package com.wanyi.uiframe.fragment.container.web;

import android.util.Log;
import android.webkit.JavascriptInterface;

/**
 * 逻辑处理
 * @author linzewu
 */
public  class InJavaScriptLocalObj {
        @JavascriptInterface
        public void getSource(String html) {
            Log.d("html=", html);
        }
}
