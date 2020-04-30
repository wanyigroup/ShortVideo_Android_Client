package com.wanyi.uiframe.upgrade;


import com.wanyi.uiframe.upgrade.enity.AppEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface IUpdateService {


    /**
     * 获取app消息信息
     * @return
     */
    @GET("/api/update/check_version")
    Observable<AppEntity> app_detail();
}
