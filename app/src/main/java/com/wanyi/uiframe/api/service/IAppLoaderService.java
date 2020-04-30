package com.wanyi.uiframe.api.service;


import com.wanyi.uiframe.api.model.dto.GlobalUrisDTO;
import io.reactivex.Observable;
import retrofit2.http.GET;

public interface IAppLoaderService {

    /**
     * 获取配置信息
     * @return 返回数据
     */
    @GET("/index/config")
    Observable<GlobalUrisDTO> globalConfig();


}
