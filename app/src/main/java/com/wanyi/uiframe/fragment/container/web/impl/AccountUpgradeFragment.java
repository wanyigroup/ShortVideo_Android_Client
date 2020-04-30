package com.wanyi.uiframe.fragment.container.web.impl;

import com.wanyi.uiframe.api.consts.UriConst;
import com.wanyi.uiframe.fragment.container.web.BaseWebFragment;
import com.wanyi.uiframe.usercenter.api.UserCenterTokenFactory;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;

@Page(name = "账户升级",anim = CoreAnim.fade)
public class AccountUpgradeFragment extends BaseWebFragment {


    @Override
    protected void initViews() {
        super.initViews();
        webView.loadUrl(UriConst.HTML_USER_UPGRADE + "token="+ UserCenterTokenFactory.getAuthToken());
    }

}
