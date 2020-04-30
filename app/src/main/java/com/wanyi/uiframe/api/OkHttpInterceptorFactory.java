package com.wanyi.uiframe.api;

import com.wanyi.uiframe.api.consts.UriConst;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpInterceptorFactory {


        /**
         * 创建推广拦截器,UUID
         * @return
         */
        public static Interceptor crateMarketClient() {
                Interceptor interceptor =  new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                                Request original = chain.request();
                                if(UriConst.UUID != null) {
                                        Request.Builder requestBuilder = original.newBuilder()
                                                .header("uuid", UriConst.UUID);
                                        Request request = requestBuilder.build();
                                        return chain.proceed(request);
                                }
                                return chain.proceed(chain.request());
                        }
                };
                return interceptor;
        }







}
