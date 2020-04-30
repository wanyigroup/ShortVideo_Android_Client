package com.wanyi.uiframe.fragment.container;

import com.wanyi.uiframe.R;
import com.wanyi.uiframe.base.BaseFragment;
import com.xuexiang.xpage.annotation.Page;

import butterknife.OnClick;


@Page(name = "账户和安全")
public class SecurityFragment extends BaseFragment {


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_container_security;
    }

    @Override
    protected void initViews() {

    }

    @OnClick(R.id.phone_text_view)
    public void onClick() {
          //openNewPage(SafeVerifyFragment.class);
    }

}
