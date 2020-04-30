package com.wanyi.uiframe.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;

import java.util.Map;
import java.util.Set;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class RequestJsonBodyFactory {

    /**
     * 获取请求的数据
     * @param object 数据对象
     * @return 对应的值
     */
    public static RequestBody create(Object object) {
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        Gson gson = new Gson();
        String jsonStr = gson.toJson(object);
        RequestBody body = RequestBody.create(JSON,jsonStr);
        return body;
    }



}
