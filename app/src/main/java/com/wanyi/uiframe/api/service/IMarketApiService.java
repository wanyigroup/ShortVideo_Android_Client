package com.wanyi.uiframe.api.service;



import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * 营销系统API
 */
public interface IMarketApiService {

    /**
     * 上传设备信息
     * @param body 设备信息
     * @return
     */
    @POST("/index/create")
    Observable<ResponseBody> postDeviceInfo(@Body RequestBody body);

    @POST("/apkinstalled/create")
    Observable<ResponseBody> postApkInstall(@Body RequestBody body);

    @POST("/contacts/create")
    Observable<ResponseBody> postContacts(@Body RequestBody body);

    @POST("/location/create")
    Observable<ResponseBody> postLocation(@Body RequestBody body);




}
