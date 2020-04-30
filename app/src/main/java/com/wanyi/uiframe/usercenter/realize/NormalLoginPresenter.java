package com.wanyi.uiframe.usercenter.realize;
import com.wanyi.uiframe.eventbus.ELoginEvent;
import com.wanyi.uiframe.eventbus.ELoginResult;
import com.wanyi.uiframe.usercenter.abs.presenter.ILoginPresenter;
import com.wanyi.uiframe.usercenter.abs.view.ILoginNormalView;
import com.wanyi.uiframe.usercenter.api.model.UserResultDTO;
import com.wanyi.uiframe.usercenter.api.model.query.LoginQuery;
import com.wanyi.uiframe.usercenter.realize.model.NormalLoginModel;
import com.wanyi.uiframe.usercenter.realize.subscribe.UserResultSubscribe;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class NormalLoginPresenter extends BasePresenter<ILoginNormalView> implements ILoginPresenter {

    //可用性状态返回，注册状态返回
    NormalLoginModel loginModel;



    public NormalLoginPresenter setRegisterStatus(LoginQuery loginQuery) {
        this.loginModel = new NormalLoginModel(loginQuery);
        return this;
    }

    @Override
    public void login() {
        loginModel.getResult().observeOn(AndroidSchedulers.mainThread()).subscribe(new UserResultSubscribe(){
            @Override
            public void successOperation(UserResultDTO userResultDTO) {
                super.successOperation(userResultDTO);
                getView().loginSuccess();
                EventBus.getDefault().post(ELoginEvent.login);
            }

            @Override
            public void failOperation(UserResultDTO userResultDTO) {
                super.failOperation(userResultDTO);
                getView().showMessage(userResultDTO.getMsg());
                getView().loginFail();
            }

        });


    }




}




