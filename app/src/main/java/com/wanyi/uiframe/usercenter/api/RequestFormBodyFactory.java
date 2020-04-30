package com.wanyi.uiframe.usercenter.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.Map;
import java.util.Set;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class RequestFormBodyFactory {

    public static RequestBody create(Object object) {
        FormBody.Builder formBody = new FormBody.Builder();
        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(object));
        Set<Map.Entry<String, Object>> set = jsonObject.entrySet();
        for(Map.Entry<String,Object> item:set) {
            formBody.add(item.getKey(),item.getValue() + "");
        }
        return formBody.build();
    }

}
