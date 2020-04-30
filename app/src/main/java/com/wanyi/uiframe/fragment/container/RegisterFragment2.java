package com.wanyi.uiframe.fragment.container;


import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import com.wanyi.uiframe.R;
import com.wanyi.uiframe.base.BaseFragment;
import com.wanyi.uiframe.eventbus.ERegisterResult;
import com.wanyi.uiframe.fragment.action.ILoginResult;
import com.wanyi.uiframe.fragment.action.IRegisterResult;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xrouter.annotation.AutoWired;
import com.xuexiang.xrouter.launcher.XRouter;
import com.xuexiang.xui.widget.button.CountDownButton;
import com.xuexiang.xui.widget.edittext.materialedittext.MaterialEditText;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

@Page(name = "邮箱绑定", params = {RegisterFragment2.KEY_EMAIL, RegisterFragment2.KEY_PASSWORD})
public class RegisterFragment2 extends BaseFragment implements ILoginResult, IRegisterResult {

    //邮箱账号
    public final static String KEY_EMAIL = "email";
    //邮箱密码
    public final static String KEY_PASSWORD = "password";
    //邮箱
    @AutoWired(name = KEY_EMAIL)
    String email;
    //密码
    @AutoWired(name = KEY_PASSWORD)
    String password;



    @BindView(R.id.input_code)
    MaterialEditText inputCode;
    @BindView(R.id.verify_email)
    CountDownButton verifyEmail;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_container_register_2;
    }


    @Override
    protected void initArgs() {
        XRouter.getInstance().inject(this);
    }

    @Override
    protected void initViews() {


        verifyEmail.onTouchEvent(MotionEvent.obtain(System.currentTimeMillis(),System.currentTimeMillis(),MotionEvent.ACTION_UP,0,0,1));
    }

    @Override
    public void loginError(String error) {
       inputCode.setError(error);
    }

    @Override
    public void loginRight() {
        new Handler().postDelayed(() -> {
            getActivity().finish();
        },500);
        EventBus.getDefault().post(ERegisterResult.success);
    }

    @Override
    public void registerSuccess() {
    }

    @Override
    public void registerFail(String fail) {
        inputCode.setError(fail);
    }

    @OnClick({R.id.verify_email, R.id.right_bind})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.verify_email: {

            }
            break;
            case R.id.right_bind: {

            }
            break;
        }
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
    }
}
