package com.wanyi.uiframe.fragment.container;

import com.wanyi.uiframe.R;
import com.wanyi.uiframe.base.BaseFragment;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xui.widget.flowlayout.FlowTagLayout;
import butterknife.BindView;
import butterknife.OnClick;

@Page(name = "账户充值")
public class RenewFragment extends BaseFragment {

    @BindView(R.id.flow_layout)
    FlowTagLayout flowLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_container_renew;
    }

    @Override
    protected void initViews() {

    }

    @OnClick(R.id.right_renew)
    public void onClick() {
        String amount = flowLayout.getSelectedItem();

    }

}
