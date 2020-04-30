package com.wanyi.uiframe.usercenter.realize.model;

import com.wanyi.uiframe.api.ApiFacade;
import com.wanyi.uiframe.usercenter.api.model.ResponseDTO;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;



public class MobileCheckModel {


    private String mobile;
    public MobileCheckModel(String mobile) {
        this.mobile = mobile;
    }


    /**
     * 核查手机是否可登录
     * @return
     */
    public Observable<ResponseDTO> check() {
       Observable<ResponseDTO> observable = ApiFacade.createUserService().phone_check(mobile).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
       return observable;
    }



}
