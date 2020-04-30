package com.wanyi.uiframe.fragment.container;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import com.wanyi.uiframe.R;
import com.wanyi.uiframe.base.BaseFragment;
import com.wanyi.uiframe.usercenter.abs.view.ILoginNormalView;
import com.wanyi.uiframe.usercenter.abs.view.IMobileCheckView;
import com.wanyi.uiframe.usercenter.abs.view.IResetView;
import com.wanyi.uiframe.usercenter.abs.view.IVerifyView;
import com.wanyi.uiframe.usercenter.api.model.query.LoginQuery;
import com.wanyi.uiframe.usercenter.api.model.query.MobileVerifyQuery;
import com.wanyi.uiframe.usercenter.api.model.query.ResetPwdQuery;
import com.wanyi.uiframe.usercenter.realize.MobileCheckPresenter;
import com.wanyi.uiframe.usercenter.realize.NormalLoginPresenter;
import com.wanyi.uiframe.usercenter.realize.ResetPwdPresenter;
import com.wanyi.uiframe.usercenter.realize.VerifyResetPresenter;
import com.wanyi.uiframe.usercenter.realize.model.types.ResetEnums;
import com.wanyi.uiframe.usercenter.realize.model.types.VerifyEnums;
import com.wanyi.uiframe.utlis.Validator;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xui.widget.button.CountDownButton;
import com.xuexiang.xui.widget.dialog.materialdialog.MaterialDialog;
import com.xuexiang.xui.widget.edittext.materialedittext.MaterialEditText;

import butterknife.BindView;
import butterknife.OnClick;

@Page(name = "重置密码")
public class ResetPswFragment1 extends BaseFragment implements IVerifyView, IMobileCheckView, IResetView, ILoginNormalView {


    @BindView(R.id.input_phone)
    MaterialEditText inputPhone;
    @BindView(R.id.input_verify)
    MaterialEditText inputVerify;
    @BindView(R.id.bt_verify)
    CountDownButton btVerify;
    @BindView(R.id.input_psw_new)
    MaterialEditText inputPswNew;

    /**
     * 验证码逻辑
     */
    VerifyResetPresenter verifyResetPresenter = new VerifyResetPresenter();

    /**
     * 核查手机逻辑
     */
    MobileCheckPresenter mobileCheckPresenter = new MobileCheckPresenter();

    /**
     * 密码重置
     */
    ResetPwdPresenter resetPwdPresenter = new ResetPwdPresenter();

    /**
     * 用户注册
     */
    NormalLoginPresenter normalLoginPresenter = new NormalLoginPresenter();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_container_resetpsw_1;
    }


    @Override
    protected void initViews() {
        verifyResetPresenter.attach(this);
        mobileCheckPresenter.attach(this);
        resetPwdPresenter.attach(this);
        normalLoginPresenter.attach(this);

        btVerify.setClickable(false);
        btVerify.setTextColor(getContext().getResources().getColor(R.color.cp_color_gray_deep));
        inputPhone.addTextChangedListener(phoneWatcher);
    }


    @OnClick({R.id.bt_verify, R.id.bt_reset_psw})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_verify: {
                mobileCheckPresenter.setMobileCheckModel(inputPhone.getEditValue());
                mobileCheckPresenter.check();
            }
            break;
            case R.id.bt_reset_psw: {
                if(validatePhone()&&validateVerify()&&validateNewPsw()){
                  resetPsw();
                }
            }
            break;
        }
    }

    private void resetPsw() {
        ResetPwdQuery resetPwdQuery = ResetPwdQuery.builder().mobile(inputPhone.getEditValue()).captcha(inputVerify.getEditValue())
                .newpassword(inputPswNew.getEditValue()).type(ResetEnums.mobile.getValue()).build();
        resetPwdPresenter.setResetPwdModel(resetPwdQuery);
        resetPwdPresenter.resetPwd();
    }

    private boolean validateNewPsw() {
        if(inputPswNew.isEmpty()) {
            inputPswNew.setError("新密码不能为空");
            return false;
        }
        if(!Validator.isPassword(inputPswNew.getEditValue())){
             inputPswNew.setError("密码格式错误");
             return false;
         }
        return true;
    }

    private boolean validateVerify() {
        if(inputVerify.isEmpty()){
            inputVerify.setError("验证码不能为空");
            return false;
        }
        if(inputVerify.length() > 6) {
            inputVerify.setError("验证码格式错误");
            return false;
        }
        return true;
    }

    private boolean validatePhone() {
        if(inputPhone.isEmpty()) {
           inputPhone.setError("手机号不能为空");
           return false;
       }
       if(!Validator.isMobile(inputPhone.getEditValue())){
           inputPhone.setError("手机号格式错误");
       }
        return true;
    }


    /**
     * 检测是否为手机号码
     */
    TextWatcher phoneWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String phoneStr = s.toString();
            if (Validator.isMobile(phoneStr)) {
                btVerify.setClickable(true);
                btVerify.setTextColor(getContext().getResources().getColor(R.color.white));
            } else {
                btVerify.setClickable(false);
                btVerify.setTextColor(getContext().getResources().getColor(R.color.cp_color_gray_deep));
            }

        }
    };

    @Override
    public void getVerifySuccess() {

    }

    @Override
    public void getVerifyFail() {

    }


    @Override
    public void onDestroy() {
        mobileCheckPresenter.detach();
        verifyResetPresenter.detach();
        resetPwdPresenter.detach();
        normalLoginPresenter.detach();
        super.onDestroy();
    }

    @Override
    public void mobileExist() {
        MobileVerifyQuery mobileVerifyQuery = MobileVerifyQuery.builder().event(VerifyEnums.ResetPwd.getValue()).mobile(inputPhone.getEditValue()).build();
        verifyResetPresenter.setSendVerifyModel(mobileVerifyQuery);
        verifyResetPresenter.getVerify();
    }

    @Override
    public void mobileNotExist() {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(getContext());
        builder.title("尊敬的用户:").content("您的手机尚未注册，现在是否注册?").positiveText("注册").negativeText("取消").onPositive((dialog, which) -> {
            openNewPage(RegisterFragment1.class);
            getActivity().finish();
        });
        builder.show();
    }


    @Override
    public void resetSuccess() {
       MaterialDialog.Builder builder = new MaterialDialog.Builder(getContext());
       builder.title("尊敬的用户:").content("您的密码已重置成功,现在是否立即登录?").positiveText("登录").negativeText("取消")
       .onPositive((dialog, which) -> {
             LoginQuery loginQuery = LoginQuery.builder().account(inputPhone.getEditValue()).password(inputPswNew.getEditValue()).build();
             normalLoginPresenter.setRegisterStatus(loginQuery);
             normalLoginPresenter.login();
       });
       builder.show();
    }

    @Override
    public void resetFail() {
       // showMessage("重置密码失败");
    }

    @Override
    public void loginSuccess() {
          getActivity().finish();
    }

    @Override
    public void loginFail() {
         showMessage("登录失败!");
    }



}
