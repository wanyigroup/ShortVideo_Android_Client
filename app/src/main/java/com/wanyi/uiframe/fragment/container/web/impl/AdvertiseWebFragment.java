package com.wanyi.uiframe.fragment.container.web.impl;

import com.wanyi.uiframe.fragment.container.web.BaseWebFragment;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;

@Page(name = "WebView",anim = CoreAnim.fade)
public class AdvertiseWebFragment extends BaseWebFragment {

    /**
     * 路径
     */
    public static String URL;

    @Override
    protected void initViews() {
        super.initViews();
        webView.loadUrl(URL);
    }


}
