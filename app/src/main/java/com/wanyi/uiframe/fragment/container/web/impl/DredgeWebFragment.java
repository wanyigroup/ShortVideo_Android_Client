package com.wanyi.uiframe.fragment.container.web.impl;

import com.wanyi.uiframe.api.consts.UriConst;
import com.wanyi.uiframe.fragment.container.web.BaseWebFragment;
import com.xuexiang.xpage.annotation.Page;

@Page(name = "开通续费")
public class DredgeWebFragment extends BaseWebFragment {

    @Override
    protected void initViews() {
        super.initViews();
        webView.loadUrl(UriConst.HTML_DREDGE);
    }


}
