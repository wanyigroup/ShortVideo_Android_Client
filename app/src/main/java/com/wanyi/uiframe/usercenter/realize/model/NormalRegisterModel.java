package com.wanyi.uiframe.usercenter.realize.model;
import com.wanyi.uiframe.api.ApiFacade;
import com.wanyi.uiframe.usercenter.api.RequestFormBodyFactory;
import com.wanyi.uiframe.usercenter.api.model.UserResultDTO;
import com.wanyi.uiframe.usercenter.api.model.query.NormalRegisterQuery;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NormalRegisterModel {

    /**
     * 用户注册
     */
    private NormalRegisterQuery normalRegisterQuery;

    public NormalRegisterModel(NormalRegisterQuery normalRegisterQuery) {
        this.normalRegisterQuery = normalRegisterQuery;
    }


    public Observable<UserResultDTO> getResult() {
        Observable<UserResultDTO>  observable = ApiFacade.createUserService().normalRegister(RequestFormBodyFactory.create(normalRegisterQuery)).subscribeOn(Schedulers.io());
        observable.observeOn(AndroidSchedulers.mainThread());
        return observable;
    }



}
