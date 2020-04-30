package com.wanyi.uiframe.api.service;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;


public interface IFileDownService {



    @Streaming
    @GET
    Observable<ResponseBody> downFile(@Url String path);



}
