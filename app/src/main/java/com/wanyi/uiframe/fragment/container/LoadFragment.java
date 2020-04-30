package com.wanyi.uiframe.fragment.container;

import android.view.View;
import android.widget.TextView;

import com.wanyi.uiframe.R;
import com.wanyi.uiframe.base.BaseFragment;
import com.wanyi.uiframe.eventbus.ELoginResult;
import com.wanyi.uiframe.eventbus.ERegisterResult;
import com.wanyi.uiframe.fragment.action.ILoginResult;
import com.wanyi.uiframe.utlis.Validator;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.button.ButtonView;
import com.xuexiang.xui.widget.edittext.materialedittext.MaterialEditText;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;


@Page(name = "登录")
public class LoadFragment extends BaseFragment implements ILoginResult {

    @BindView(R.id.input_email)
    MaterialEditText inputEmail;
    @BindView(R.id.input_psw)
    MaterialEditText inputPsw;
    @BindView(R.id.psw_forget)
    TextView pswForget;
    @BindView(R.id.input_login)
    ButtonView inputLogin;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_container_login;
    }


    @Override
    protected TitleBar initTitle() {
        TitleBar titleBar = super.initTitle();
        titleBar.addAction(new TitleBar.TextAction("注册") {
            @Override
            public void performAction(View view) {
                openNewPage(RegisterFragment1.class);
            }
        });
        return titleBar;
    }

    @Override
    protected void initViews() {
        EventBus.getDefault().register(this);
    }

    /**
     * 验证密码
     * @return 是否为密码
     */
    private boolean verifyPsw() {
        boolean  isPsw = Validator.isPassword(inputPsw.getText().toString().trim());
        if(!isPsw) {
            inputPsw.setError("密码格式错误，长度6-16");
        }
        return isPsw;
    }

    /**
     * 验证邮箱
     * @return 是否为邮箱
     */
    private boolean verifyEmail() {
        boolean isEmail = Validator.isEmail(inputEmail.getText().toString().trim());
        if(!isEmail) {
            inputEmail.setError("邮箱格式错误");
        }
        return isEmail;
    }




    @OnClick({R.id.psw_forget, R.id.input_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.psw_forget:
                openNewPage(ForgetPswFragment1.class);
                break;
            case R.id.input_login:
                if(verifyEmail()&&verifyPsw()) {
                   login();
                }
                break;
        }
    }

    /**
     * 登录逻辑
     */
    private void login() {
        String email = inputEmail.getText().toString().trim();
        String psw = inputPsw.getText().toString().trim();
    }

    @Override
    public void loginError(String error) {
        inputPsw.setError(error);
        EventBus.getDefault().post(ELoginResult.FAIL);
    }

    @Override
    public void loginRight() {
        getActivity().finish();
        EventBus.getDefault().post(ELoginResult.SUCCESS);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void subscribe(ERegisterResult result) {
        getActivity().finish();
    }

}
