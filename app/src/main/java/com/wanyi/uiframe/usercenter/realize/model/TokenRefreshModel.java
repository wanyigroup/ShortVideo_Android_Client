package com.wanyi.uiframe.usercenter.realize.model;

import com.wanyi.uiframe.api.ApiFacade;
import com.wanyi.uiframe.usercenter.api.model.TokenResultDTO;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class TokenRefreshModel{


    public Observable<TokenResultDTO> getResult() {
        Observable<TokenResultDTO> observable =  ApiFacade.createUserService().token_refersh().subscribeOn(Schedulers.io());
        return observable;
    }



}
