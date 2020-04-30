package com.wanyi.uiframe.fragment.container;

import android.view.View;
import android.widget.ScrollView;

import com.wanyi.uiframe.R;
import com.wanyi.uiframe.api.ApiFacade;
import com.wanyi.uiframe.base.BaseFragment;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xui.widget.dialog.materialdialog.MaterialDialog;
import com.xuexiang.xui.widget.edittext.materialedittext.MaterialEditText;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@Page(name = "重置密码")
public class ForgetPswFragment1 extends BaseFragment {

    @BindView(R.id.input_email)
    MaterialEditText inputEmail;
    @BindView(R.id.input_verify)
    MaterialEditText inputVerify;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_container_forgetpsw_1;
    }

    @Override
    protected void initViews() {

    }

    @OnClick({R.id.verify_email, R.id.step_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.verify_email: {

            }
            break;
        }
    }


}
