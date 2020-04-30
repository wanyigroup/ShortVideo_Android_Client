package com.wanyi.uiframe.fragment.container.web.impl;

import com.wanyi.uiframe.api.consts.UriConst;
import com.wanyi.uiframe.fragment.container.web.BaseWebFragment;
import com.wanyi.uiframe.usercenter.api.UserCenterTokenFactory;
import com.xuexiang.xpage.annotation.Page;

@Page(name = "账户充值")
public class RechargeFragment extends BaseWebFragment {

    @Override
    protected void initViews() {
        super.initViews();
        webView.loadUrl(UriConst.HTML_CHARGE + "token=" + UserCenterTokenFactory.getAuthToken());
    }


}
