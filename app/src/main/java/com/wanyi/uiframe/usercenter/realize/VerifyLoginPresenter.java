package com.wanyi.uiframe.usercenter.realize;

import com.wanyi.uiframe.eventbus.ELoginEvent;
import com.wanyi.uiframe.usercenter.abs.presenter.ILoginPresenter;
import com.wanyi.uiframe.usercenter.abs.presenter.IVerifyLoginPresenter;
import com.wanyi.uiframe.usercenter.abs.view.ILoginVerifyView;
import com.wanyi.uiframe.usercenter.api.model.UserResultDTO;
import com.wanyi.uiframe.usercenter.api.model.query.MobileLoginQuery;
import com.wanyi.uiframe.usercenter.api.model.query.MobileVerifyQuery;
import com.wanyi.uiframe.usercenter.realize.model.MobileLoginModel;
import com.wanyi.uiframe.usercenter.realize.model.SendVerifyModel;
import com.wanyi.uiframe.usercenter.realize.subscribe.UserResultSubscribe;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class VerifyLoginPresenter extends BasePresenter<ILoginVerifyView> implements ILoginPresenter, IVerifyLoginPresenter {

    SendVerifyModel sendVerifyModel;
    MobileLoginModel mobileLoginModel;

    public void setVerifyStatus(MobileVerifyQuery mobileVerifyQuery) {
       this.sendVerifyModel = new SendVerifyModel(mobileVerifyQuery);
    }

    public void setRegisterStatus(MobileLoginQuery mobileLoginQuery) {
        this.mobileLoginModel = new MobileLoginModel(mobileLoginQuery);
    }

    //获取验证码
    @Override
    public void getVerify() {
        sendVerifyModel.getResult().observeOn(AndroidSchedulers.mainThread()).subscribe(loadResult -> {
            if(loadResult.isSuccess()) {
                EventBus.getDefault().post(ELoginEvent.login);
                getView().getVerifySuccess();
            } else {
                getView().getVerifyFail();
                getView().showMessage(loadResult.getMsg());
            }
        });
    }

    //注册
    @Override
    public void login() {
        mobileLoginModel.getResult().subscribe(new UserResultSubscribe(){
            @Override
            public void successOperation(UserResultDTO userResultDTO) {
                super.successOperation(userResultDTO);
                getView().loginSuccess();
            }

            @Override
            public void failOperation(UserResultDTO userResultDTO) {
                super.failOperation(userResultDTO);
                getView().loginFail();
                getView().showMessage(userResultDTO.getMsg());
            }
        });
    }




}
