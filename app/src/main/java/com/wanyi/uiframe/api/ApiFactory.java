package com.wanyi.uiframe.api;




import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 调用接口的上下文
 */
public class ApiFactory {


    /**
     * 使用泛型接口对象，封装具体的API
     * @param tClass 接口类名
     * @param <T> 具体的参数
     * @return 接口
     */
    public static<T> T createApi(String hostUrl, Class<T> tClass, Interceptor interceptor){
        if( hostUrl==null || hostUrl.isEmpty()) {
            hostUrl= "http://www.baidu.com";
        }
         HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
             @Override
             public void log(String message) {
                 //打印retrofit日志
                // Log.d("ApiFactory body", message);
                 Log.e("httpLog","message:" + message);
             }
         });
         loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
         OkHttpClient.Builder builder  = new OkHttpClient.Builder()//okhttp设置部分，此处还可再设置网络参数
                 .addInterceptor(loggingInterceptor);
         if(interceptor != null) {
             builder.addInterceptor(interceptor);
         }
         OkHttpClient client = builder.readTimeout(30, TimeUnit.SECONDS).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(hostUrl)//基础URL 建议以 / 结尾
                .client(client)//拦截加代码的信息
                .addConverterFactory(GsonConverterFactory.create())//设置 Json 转换器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//RxJava 适配器
                .build();
        T operation = retrofit.create(tClass);
        return operation;
    }





}
