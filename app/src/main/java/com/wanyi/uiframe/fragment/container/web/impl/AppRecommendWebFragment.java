package com.wanyi.uiframe.fragment.container.web.impl;

import com.wanyi.uiframe.api.consts.UriConst;
import com.wanyi.uiframe.fragment.container.web.BaseWebFragment;
import com.xuexiang.xpage.annotation.Page;

@Page(name = "应用推荐")
public class AppRecommendWebFragment extends BaseWebFragment {

    @Override
    protected void initViews() {
        super.initViews();
        webView.loadUrl(UriConst.HTML_APP_RECOMMEND);
    }


}
