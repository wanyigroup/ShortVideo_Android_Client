package com.wanyi.uiframe.fragment.container;

import android.util.Log;
import android.widget.LinearLayout;

import com.just.agentweb.AgentWeb;
import com.wanyi.uiframe.R;
import com.wanyi.uiframe.base.BaseFragment;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xrouter.annotation.AutoWired;
import com.xuexiang.xrouter.launcher.XRouter;

@Page(name = "支付",params = {RechargePayFragment.PAY_URL})
public class RechargePayFragment extends BaseFragment {

    public static final String PAY_URL = "pay_url";

    AgentWeb mAgentWeb = null;

    @AutoWired(name = PAY_URL)
    String payUrl;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_container_recharge;
    }

    @Override
    protected void initViews() {
        Log.e(getClass().getName(),"payUrl:" + payUrl);
       mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent((LinearLayout) getRootView(), new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .createAgentWeb()
                .ready()
                .go(payUrl);
    }

    @Override
    protected void initArgs() {
        XRouter.getInstance().inject(this);
    }

    @Override
    public void onDestroy() {
        mAgentWeb.destroy();
        super.onDestroy();
    }

}
