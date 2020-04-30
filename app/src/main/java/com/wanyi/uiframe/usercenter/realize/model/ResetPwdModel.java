package com.wanyi.uiframe.usercenter.realize.model;

import com.wanyi.uiframe.api.ApiFacade;
import com.wanyi.uiframe.usercenter.api.RequestFormBodyFactory;
import com.wanyi.uiframe.usercenter.api.model.ResponseDTO;
import com.wanyi.uiframe.usercenter.api.model.query.ResetPwdQuery;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class ResetPwdModel {

    private ResetPwdQuery resetPwdQuery;
    public ResetPwdModel(ResetPwdQuery resetPwdQuery) {
        this.resetPwdQuery = resetPwdQuery;
    }


    public Observable<ResponseDTO> resetPwd() {
        Observable<ResponseDTO> observable = ApiFacade.createUserService().user_resetpwd(RequestFormBodyFactory.create(resetPwdQuery))
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        return observable;
    }


}
