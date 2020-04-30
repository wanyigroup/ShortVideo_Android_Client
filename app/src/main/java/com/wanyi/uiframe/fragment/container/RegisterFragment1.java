package com.wanyi.uiframe.fragment.container;

import android.text.Html;
import android.widget.TextView;
import com.wanyi.uiframe.R;
import com.wanyi.uiframe.base.BaseFragment;
import com.wanyi.uiframe.usercenter.abs.view.IRegisterView;
import com.wanyi.uiframe.usercenter.realize.RegisterPresenter;
import com.wanyi.uiframe.utlis.Validator;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xui.widget.button.ButtonView;
import com.xuexiang.xui.widget.dialog.materialdialog.MaterialDialog;
import com.xuexiang.xui.widget.edittext.materialedittext.MaterialEditText;

import butterknife.BindView;
import butterknife.OnClick;

@Page(name = "注册")
public class RegisterFragment1 extends BaseFragment implements IRegisterView {

    @BindView(R.id.explain_register)
    TextView explainRegister;
    @BindView(R.id.input_username)
    MaterialEditText inputUserName;
    @BindView(R.id.input_psw)
    MaterialEditText inputPsw;
    @BindView(R.id.input_psw_again)
    MaterialEditText inputPswAgain;
    @BindView(R.id.input_email)
    MaterialEditText inputEmail;
    @BindView(R.id.input_phone)
    MaterialEditText inputPhone;
    @BindView(R.id.step_next)
    ButtonView stepNext;
    //注册使用
    RegisterPresenter registerPresenter = new RegisterPresenter();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_container_register_1;
    }

    @Override
    protected void initViews() {
        registerPresenter.attach(this);
        String explainText = getContext().getString(R.string.explain_register);
        explainRegister.setText(Html.fromHtml(explainText));
    }


    @OnClick(R.id.step_next)
    public void onClick() {
        if(validatorUserName()&&validatorPsw()&&validatorRightPsw()&&validatorEmail()&&validatorPhone()) {
            registerUser();
        }

    }

    private boolean validatorPhone() {
        boolean rst = false;
        rst = Validator.isMobile(inputPhone.getText().toString().trim());
        if(!rst) {
            inputPhone.setError("手机格式错误");
        }
        return rst;
    }

    /**
     * 验证用户名
     * @return
     */
    private boolean validatorUserName() {
        boolean rst = false;
        rst = Validator.isUsername(inputUserName.getText().toString().trim());
        if(!rst) {
            inputUserName.setError("用户名格式错误");
        }
        return rst;
    }

    /**
     * 注册用户
     */
    private void registerUser() {

        registerPresenter.register(inputUserName.getEditValue(),inputPsw.getEditValue(),inputEmail.getEditValue(),inputPhone.getEditValue());
    }

    /**
     * 验证用户
     * @return
     */
    private boolean validatorEmail() {
      boolean rst = false;
      rst = Validator.isEmail(inputEmail.getText().toString().trim());
      if(!rst) {
          inputEmail.setError("邮箱格式错误");
      }
      return rst;
    }

    /**
     * 验证密码
     * @return
     */
    private boolean validatorPsw() {
       boolean rst = false;
       rst = Validator.isPassword(inputPsw.getText().toString().trim());
       if(!rst) {
           inputPsw.setError("密码格式错误");
       }
       return rst;
    }

    /**
     * 验证码密码是否一致
     * @return
     */
    private boolean validatorRightPsw() {
        boolean rst = false;
        rst =inputPsw.getText().toString().trim().equals(inputPswAgain.getText().toString().trim());
        if(!rst) {
            inputPswAgain.setError("输入密码不一致");
        }
        return rst;
    }



    @Override
    public void onDestroy() {
        registerPresenter.detach();
        super.onDestroy();
    }


    @Override
    public void registerSuccess() {
        getActivity().finish();
    }

    @Override
    public void showMessage(String msg) {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(getContext());
        builder.content(msg).positiveText("确定").show();
    }


}
