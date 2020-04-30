package com.wanyi.uiframe.usercenter.realize.model;

import com.wanyi.uiframe.api.ApiFacade;
import com.wanyi.uiframe.usercenter.api.model.ResponseDTO;
import com.wanyi.uiframe.usercenter.api.model.action.ILoadResult;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class LogoutModel  {


    public Observable<ResponseDTO> getResult() {
       Observable<ResponseDTO> observable =  ApiFacade.createUserService().user_logout().subscribeOn(Schedulers.io());
       return observable;
    }


}
