package com.wanyi.uiframe.fragment.container.web.impl;

import com.wanyi.uiframe.api.consts.UriConst;
import com.wanyi.uiframe.fragment.container.web.BaseWebFragment;
import com.xuexiang.xpage.annotation.Page;

@Page(name = "意见反馈")
public class FeedbackWebFragment extends BaseWebFragment {

    @Override
    protected void initViews() {
        super.initViews();
        webView.loadUrl(UriConst.HTML_APP_FEEDBACK);
    }


}
