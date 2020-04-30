package com.wanyi.uiframe.usercenter.api;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class UserCenterTokenFactory {

    /**
     * 用户Token
     */
    static String AUTH_TOKEN = null;

    /**
     *  设置用户的token
      * @param authToken 用户token
     */
    public static void setAuthToken(String authToken) {
        AUTH_TOKEN = authToken;
    }

    public static String getAuthToken() {
        return AUTH_TOKEN;
    }

    /**
     * 清空用户的token
     */
    public static void clearAuthToken() {
        AUTH_TOKEN = null;
    }

    /**
     * 创建服务拦截器，TOKEN
     * @return
     */
    public static Interceptor createTokenClient() {
        Interceptor interceptor =  new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
               // Log.e("TOKEN", (AUTH_TOKEN == null) + "" );
                if(AUTH_TOKEN != null) {
                    // Log.e("TOKEN", AUTH_TOKEN );
                    Request.Builder requestBuilder = original.newBuilder()
                            .header("token",AUTH_TOKEN);
                           // .header("Authorization", "Bearer " + AUTH_TOKEN);
                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
                return chain.proceed(chain.request());
            }
        };
        return interceptor;
    }





}
