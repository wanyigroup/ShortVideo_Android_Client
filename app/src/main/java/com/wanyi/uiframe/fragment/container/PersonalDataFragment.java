package com.wanyi.uiframe.fragment.container;

import android.view.View;
import com.wanyi.uiframe.R;
import com.wanyi.uiframe.base.BaseFragment;
import com.wanyi.uiframe.usercenter.abs.view.IPersonalView;
import com.wanyi.uiframe.usercenter.realize.PersonalDataPresenter;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xui.widget.dialog.materialdialog.MaterialDialog;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;
import butterknife.BindView;
import butterknife.OnClick;


@Page(name = "个人资料")
public class PersonalDataFragment extends BaseFragment implements IPersonalView {

    @BindView(R.id.signature)
    SuperTextView signature;
    @BindView(R.id.nick)
    SuperTextView nick;
    @BindView(R.id.phone)
    SuperTextView phone;

    PersonalDataPresenter personalDataPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_container_personal_data;
    }

    @Override
    protected void initViews() {
      personalDataPresenter = new PersonalDataPresenter();
      personalDataPresenter.attach(this);
      personalDataPresenter.displayPersonalInfo();
    }

    @OnClick({R.id.signature, R.id.nick, R.id.phone})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.signature:{
                personalDataPresenter.updateDio();
            }
                break;
            case R.id.nick:{
                personalDataPresenter.updateNickname();
            }
                break;
            case R.id.phone:{
                //personalDataPresenter.updateMobilePhone();
            }
                break;
        }
    }
//36fbb354-e372-4ed5-9443-a041f9d29e3c

    public void showDialog(String title, String context, MaterialDialog.SingleButtonCallback callback) {
        new MaterialDialog.Builder(getContext())
                .title(title)
                .input(context,"",false,(dialog, input) -> {})
                .positiveText("确定")
                .negativeText("取消")
                .onPositive(callback)
                .cancelable(false)
                .show();
    }


    @Override
    public void setBio(String bio) {
        signature.setRightString(bio);
    }

    @Override
    public void setNickName(String nickName) {
        nick.setRightString(nickName);
    }

    @Override
    public void setMobile(String mobileNumber) {
       this.phone.setRightString(mobileNumber);
    }

    @Override
    public void showMessage(String message) {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(getContext());
        builder.content(message).positiveText("确定");
        builder.show();
    }


    @Override
    public void onDestroy() {
        personalDataPresenter.detach();
        super.onDestroy();
    }
}



