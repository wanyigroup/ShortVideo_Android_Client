package com.wanyi.uiframe.usercenter.realize;

import com.wanyi.uiframe.usercenter.abs.view.IVerifyView;
import com.wanyi.uiframe.usercenter.api.model.query.MobileVerifyQuery;
import com.wanyi.uiframe.usercenter.realize.model.SendVerifyModel;


public class VerifyResetPresenter extends BasePresenter<IVerifyView>  {

    SendVerifyModel sendVerifyModel;

    public void setSendVerifyModel(MobileVerifyQuery mobileVerifyQuery) {
        sendVerifyModel = new SendVerifyModel(mobileVerifyQuery);
    }

    /**
     * 获取验证码
     */
    public void getVerify() {
        sendVerifyModel.getResult().subscribe(loadResult -> {
            if(loadResult.isSuccess()) {
                if(getView() != null) {
                    getView().getVerifySuccess();
                }
            } else {
                if(getView() != null) {
                    getView().getVerifyFail();
                    getView().showMessage(loadResult.getMsg());
                }
            }
        });

    }





}
