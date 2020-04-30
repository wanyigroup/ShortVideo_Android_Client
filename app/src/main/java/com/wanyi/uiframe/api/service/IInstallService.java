package com.wanyi.uiframe.api.service;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IInstallService {

    /**
     * 统计系统
     * @param body
     * @return
     */
    @POST("/api/track")
    Observable<ResponseBody> postTrackInfo(@Body RequestBody body);

}
