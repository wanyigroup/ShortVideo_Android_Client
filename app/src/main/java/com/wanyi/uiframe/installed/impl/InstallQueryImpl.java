package com.wanyi.uiframe.installed.impl;

import android.content.Context;
import android.content.SharedPreferences;

import com.alibaba.fastjson.JSON;
import com.wanyi.uiframe.installed.IInstallQuery;
import com.wanyi.uiframe.installed.query.InstalledQuery;


public class InstallQueryImpl implements IInstallQuery {

    final String INSTALL_KEY = "install";
    SharedPreferences sp;

    public InstallQueryImpl(Context context) {
        sp = context.getSharedPreferences("installQuery",Context.MODE_PRIVATE);
    }


    @Override
    public boolean isEmpty() {
        return sp.getString(INSTALL_KEY,"").isEmpty();
    }

    @Override
    public void saveInstalledQuery(InstalledQuery query) {
       String json =  JSON.toJSONString(query);
       sp.edit().putString(INSTALL_KEY,json).commit();
    }

    @Override
    public InstalledQuery getInstalledQuery() {
        String json = sp.getString(INSTALL_KEY,"");
        InstalledQuery installedQuery = JSON.parseObject(json,InstalledQuery.class);
        return installedQuery;
    }



}
