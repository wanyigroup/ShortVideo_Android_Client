package com.wanyi.uiframe.dialog.impl;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.wanyi.uiframe.R;
import com.wanyi.uiframe.dialog.BaseDialogFragment;
import com.wanyi.uiframe.usercenter.abs.view.ILoginNormalView;
import com.wanyi.uiframe.usercenter.abs.view.ILoginSelectedView;
import com.wanyi.uiframe.usercenter.abs.view.ILoginVerifyView;
import com.wanyi.uiframe.usercenter.abs.view.ILoginView;
import com.wanyi.uiframe.usercenter.api.model.query.LoginQuery;
import com.wanyi.uiframe.usercenter.api.model.query.MobileLoginQuery;
import com.wanyi.uiframe.usercenter.api.model.query.MobileVerifyQuery;
import com.wanyi.uiframe.usercenter.realize.NormalLoginPresenter;
import com.wanyi.uiframe.usercenter.realize.SelectLoginPresenter;
import com.wanyi.uiframe.usercenter.realize.VerifyLoginPresenter;
import com.wanyi.uiframe.usercenter.realize.model.types.VerifyEnums;
import com.wanyi.uiframe.utlis.Validator;
import com.xuexiang.xui.widget.alpha.XUIAlphaImageView;
import com.xuexiang.xui.widget.button.CountDownButton;
import com.xuexiang.xui.widget.button.roundbutton.RoundButton;
import com.xuexiang.xui.widget.dialog.materialdialog.MaterialDialog;
import com.xuexiang.xui.widget.edittext.materialedittext.MaterialEditText;

import butterknife.BindView;
import butterknife.OnClick;


public class UserLoadDialog extends BaseDialogFragment implements ILoginSelectedView, ILoginView, ILoginVerifyView, ILoginNormalView {


    @BindView(R.id.bt_exit)
    XUIAlphaImageView btExit;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.input_user)
    MaterialEditText inputUser;
    @BindView(R.id.input_password)
    MaterialEditText inputPassword;
    @BindView(R.id.bt_verify)
    CountDownButton btVerify;
    @BindView(R.id.rb_load)
    RoundButton rbLoad;
    @BindView(R.id.pwd_load)
    TextView pwdLoad;
    @BindView(R.id.bt_register)
    TextView btRegister;
    @BindView(R.id.bt_find)
    TextView btFind;

    CallBack callBack;
    //是否为正常页面
    boolean isNormalLoad;
    //手机登录
    VerifyLoginPresenter verifyRegisterPresenter = new VerifyLoginPresenter();
    //验证码登录
    NormalLoginPresenter normalRegisterPresenter = new NormalLoginPresenter();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setWindowAnimations(R.style.animate_dialog);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SelectLoginPresenter selectLoadPresenter = new SelectLoginPresenter(getContext());
        selectLoadPresenter.attach(this);
        selectLoadPresenter.showUI();
        verifyRegisterPresenter.attach(this);
        normalRegisterPresenter.attach(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.dialog_usercenter_load;
    }

    @Override
    protected boolean isTouchHide() {
        return false;
    }

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    public void showNormalLogin() {
        isNormalLoad = true;
        tvTitle.setText("账号密码登录");
        inputUser.setFloatingLabelText("用户名");
        inputUser.setHint("请输入用户名");
        inputPassword.setFloatingLabelText("密码");
        inputPassword.setHint("请输入密码");
        inputPassword.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
        btVerify.setVisibility(View.GONE);
        btFind.setVisibility(View.VISIBLE);
        inputPassword.setMinCharacters(-1);
        inputPassword.setMaxCharacters(-1);
        pwdLoad.setText("手机验证码登录");
        inputUser.setText("");
        inputPassword.setText("");
        inputUser.removeTextChangedListener(phoneWatcher);
    }


    @Override
    public void showVerifyLogin() {
        isNormalLoad = false;
        tvTitle.setText("手机验证码登录");
        inputUser.setFloatingLabelText("手机号");
        inputUser.setHint("请输入手机号码");
        inputPassword.setFloatingLabelText("验证码");
        inputPassword.setHint("请输入验证码");
        btVerify.setVisibility(View.VISIBLE);
        btFind.setVisibility(View.GONE);
        inputPassword.setMaxCharacters(6);
        pwdLoad.setText("账号密码登录");
        inputPassword.setInputType(InputType.TYPE_CLASS_NUMBER);
        inputUser.setText("");
        inputPassword.setText("");
        inputUser.addTextChangedListener(phoneWatcher);
        btVerify.setClickable(false);
        btVerify.setTextColor(getContext().getResources().getColor(R.color.cp_color_gray_deep));
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
                btVerify.setTextColor(getContext().getResources().getColor(R.color.black));
            } else {
                btVerify.setClickable(false);
                btVerify.setTextColor(getContext().getResources().getColor(R.color.cp_color_gray_deep));
            }

        }
    };


    @Override
    public void loginSuccess() {
        dismiss();
    }

    @Override
    public void loginFail() {

    }

    @Override
    public void showMessage(String msg) {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(getContext());
        builder.content(msg).positiveText("确定").show();
    }


    @Override
    public void getVerifySuccess() {


    }

    @Override
    public void getVerifyFail() {

    }

    @OnClick({R.id.bt_exit, R.id.bt_verify, R.id.rb_load, R.id.pwd_load, R.id.bt_register,R.id.bt_find})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_exit:
                dismiss();
                break;
            case R.id.bt_find:{
                dismiss();
                if(callBack != null) {
                    callBack.skipFind();
                }
            }
            break;
            case R.id.bt_verify: {

                MobileVerifyQuery mobileVerifyQuery = MobileVerifyQuery.builder().mobile(inputUser.getEditValue()).event(VerifyEnums.MobileLogin.getValue()).build();
                verifyRegisterPresenter.setVerifyStatus(mobileVerifyQuery);
                verifyRegisterPresenter.getVerify();
            }
            break;
            case R.id.rb_load: {
                if (isNormalLoad) {
                    LoginQuery loginQuery = LoginQuery.builder().account(inputUser.getEditValue()).password(inputPassword.getEditValue()).build();
                    normalRegisterPresenter.setRegisterStatus(loginQuery);
                    normalRegisterPresenter.login();
                } else {
                    MobileLoginQuery mobileLoginQuery = new MobileLoginQuery();
                    mobileLoginQuery.setMobile(inputUser.getEditValue());
                    mobileLoginQuery.setCaptcha(inputPassword.getEditValue());
                    verifyRegisterPresenter.setRegisterStatus(mobileLoginQuery);
                    verifyRegisterPresenter.login();
                }
            }
            break;
            case R.id.pwd_load:
                if (isNormalLoad) {
                    showVerifyLogin();
                } else {
                    showNormalLogin();
                }
                break;
            case R.id.bt_register: {
                if (callBack != null) {
                    dismiss();
                    callBack.skipRegister();
                }
            }
            break;

        }
    }


    @Override
    public void dismiss() {
        verifyRegisterPresenter.detach();
        normalRegisterPresenter.detach();
        super.dismiss();
    }

    public interface CallBack {

        /**
         * 跳转到注册界面
         */
        void skipRegister();

        /**
         * 跳转到重置密码界面
         */
        void skipFind();

    }


}
