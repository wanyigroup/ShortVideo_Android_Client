package com.wanyi.uiframe.fragment.container.web.impl;

import android.view.View;

import com.wanyi.uiframe.aop.AopUserLogin;
import com.wanyi.uiframe.api.consts.UriConst;
import com.wanyi.uiframe.fragment.container.DataEditionFragment;
import com.wanyi.uiframe.fragment.container.web.BaseWebFragment;
import com.wanyi.uiframe.usercenter.api.UserCenterTokenFactory;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xui.widget.actionbar.TitleBar;

@Page(name = "卡券兑换")
public class CouponWebFragment extends BaseWebFragment {


    @Override
    protected com.xuexiang.xui.widget.actionbar.TitleBar initTitle() {
        com.xuexiang.xui.widget.actionbar.TitleBar titleBar = super.initTitle();
        titleBar.addAction(new TitleBar.TextAction("刷新") {

            @AopUserLogin
            @Override
            public void performAction(View view) {
                 loadUrl();
            }
        });
        return titleBar;
    }


    @Override
    protected void initViews() {
        super.initViews();
        loadUrl();
    }

    /**
     * 加载网页
     */
    private  void loadUrl() {
        String tokenUrl = UriConst.HTML_VOUCHER + "?" +"token=" + UserCenterTokenFactory.getAuthToken();
        webView.loadUrl(tokenUrl);
    }


}
