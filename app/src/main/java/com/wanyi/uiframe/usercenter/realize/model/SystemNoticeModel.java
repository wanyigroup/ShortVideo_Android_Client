package com.wanyi.uiframe.usercenter.realize.model;

import com.wanyi.uiframe.api.ApiFacade;
import com.wanyi.uiframe.usercenter.api.model.NoticeResultDTO;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SystemNoticeModel {

    /**
     * 获取广告列表
     * @return
     */
    public Observable<NoticeResultDTO> getNotice() {
      Observable observable =  ApiFacade.createUserService().notice_list().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        return observable;
    }



}
