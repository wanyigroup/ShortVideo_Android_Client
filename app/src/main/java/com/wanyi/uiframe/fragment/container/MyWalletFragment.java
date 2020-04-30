package com.wanyi.uiframe.fragment.container;

import android.util.Log;
import android.view.View;

import com.wanyi.uiframe.R;
import com.wanyi.uiframe.base.BaseFragment;
import com.wanyi.uiframe.fragment.container.web.impl.CouponWebFragment;
import com.xuexiang.xpage.annotation.Page;

import butterknife.OnClick;

@Page(name = "我的钱包")
public class MyWalletFragment extends BaseFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_container_wallet;
    }

    @Override
    protected void initViews() {

    }

    @OnClick({R.id.vip_recharge, R.id.vip_coupon_exchange, R.id.vip_upgrade})
    public void onClick(View view) {
        Log.e(getClass().getSimpleName(),"onclick");
        switch (view.getId()) {
            case R.id.vip_recharge:
                openNewPage(RenewFragment.class);
                break;
            case R.id.vip_coupon_exchange:
                openNewPage(CouponWebFragment.class);
                break;
            case R.id.vip_upgrade:
                break;
        }
    }
}
