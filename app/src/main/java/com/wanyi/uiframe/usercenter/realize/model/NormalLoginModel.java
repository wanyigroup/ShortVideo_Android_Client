package com.wanyi.uiframe.usercenter.realize.model;

import com.wanyi.uiframe.api.ApiFacade;
import com.wanyi.uiframe.usercenter.api.RequestFormBodyFactory;
import com.wanyi.uiframe.usercenter.api.model.UserResultDTO;
import com.wanyi.uiframe.usercenter.api.model.query.LoginQuery;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class NormalLoginModel {

    private LoginQuery loginQuery;
    public NormalLoginModel(LoginQuery loginQuery) {
        this.loginQuery = loginQuery;
    }


    public Observable<UserResultDTO> getResult() {
        Observable<UserResultDTO> observable =  ApiFacade.createUserService().normalLogin(RequestFormBodyFactory.create(loginQuery)).subscribeOn(Schedulers.io());
        return observable;
    }

}
