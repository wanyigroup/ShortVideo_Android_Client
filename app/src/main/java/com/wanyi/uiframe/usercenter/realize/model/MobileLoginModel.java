package com.wanyi.uiframe.usercenter.realize.model;

import com.wanyi.uiframe.api.ApiFacade;
import com.wanyi.uiframe.usercenter.api.RequestFormBodyFactory;
import com.wanyi.uiframe.usercenter.api.model.UserResultDTO;
import com.wanyi.uiframe.usercenter.api.model.query.MobileLoginQuery;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MobileLoginModel  {

    private MobileLoginQuery mobileLoginQuery;
    public MobileLoginModel(MobileLoginQuery mobileLoginQuery){
      this.mobileLoginQuery = mobileLoginQuery;
    }


    public Observable<UserResultDTO> getResult() {
        Observable<UserResultDTO> observable = ApiFacade.createUserService().mobileLogin(RequestFormBodyFactory.create(mobileLoginQuery)).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        return observable;
    }



}
