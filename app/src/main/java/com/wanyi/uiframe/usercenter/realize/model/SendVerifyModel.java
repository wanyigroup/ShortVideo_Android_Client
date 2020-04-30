package com.wanyi.uiframe.usercenter.realize.model;

import com.wanyi.uiframe.api.ApiFacade;
import com.wanyi.uiframe.usercenter.api.RequestFormBodyFactory;
import com.wanyi.uiframe.usercenter.api.model.ResponseDTO;
import com.wanyi.uiframe.usercenter.api.model.query.MobileVerifyQuery;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SendVerifyModel {


    private MobileVerifyQuery mobileVerifyQuery;
    public SendVerifyModel(MobileVerifyQuery mobileVerifyQuery) {
        this.mobileVerifyQuery = mobileVerifyQuery;
    }


    public Observable<ResponseDTO> getResult() {
        Observable<ResponseDTO> observable =   ApiFacade.createUserService().mobileVerify(RequestFormBodyFactory.create(mobileVerifyQuery))
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        return observable;
    }

}
