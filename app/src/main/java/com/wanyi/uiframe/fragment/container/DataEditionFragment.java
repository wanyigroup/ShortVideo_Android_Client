package com.wanyi.uiframe.fragment.container;

import android.widget.ImageView;
import android.widget.TextView;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.pixplicity.sharp.Sharp;
import com.wanyi.uiframe.R;
import com.wanyi.uiframe.base.BaseFragment;
import com.wanyi.uiframe.usercenter.abs.view.IMineView;
import com.wanyi.uiframe.usercenter.api.model.UserResultDTO;
import com.wanyi.uiframe.usercenter.realize.TokenGetPresenter;
import com.xuexiang.xpage.annotation.Page;
import butterknife.BindView;

@Page(name = "资料编辑")
public class DataEditionFragment extends BaseFragment implements IMineView {

    @BindView(R.id.user_photo)
    ImageView userPhoto;
    @BindView(R.id.user_name)
    TextView userName;
    @BindView(R.id.spinner_user_sex)
    MaterialSpinner spinnerUserSex;

    TokenGetPresenter tokenGetPresenter = new TokenGetPresenter();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_container_edition;
    }

    @Override
    protected void initViews() {
        spinnerUserSex.setItems("男","女","保密");
        tokenGetPresenter.attach(this);
        tokenGetPresenter.get();
    }

    @Override
    public void setUserResult(UserResultDTO userResult) {
        String nickName = userResult.getMobile();
        userName.setText(nickName);
        String photo = userResult.getAvatarStr();
        Sharp.loadString(photo).into(userPhoto);
    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void setVipText(String vipText) {

    }

    @Override
    public void finishRefresh() {

    }


    @Override
    public void setVipStyle() {

    }

    @Override
    public void setVisitorStyle() {

    }

    @Override
    public void showLoginDialog() {

    }


    @Override
    public void onDestroy() {
        tokenGetPresenter.detach();
        super.onDestroy();
    }


}
