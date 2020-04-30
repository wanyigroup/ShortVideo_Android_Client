package com.wanyi.uiframe.installed.impl;

import android.content.Context;
import android.os.Build;
import android.util.Log;


import com.alibaba.fastjson.JSON;
import com.wanyi.uiframe.installed.IInstallFactory;
import com.wanyi.uiframe.installed.query.InstalledQuery;

import java.lang.ref.WeakReference;
import java.util.UUID;

import io.reactivex.functions.Consumer;


public class InstallFactoryImpl implements IInstallFactory {

    WeakReference<Context> weakReference;

    public InstallFactoryImpl(Context context) {
        weakReference = new WeakReference(context);
    }

    @Override
    public void subscribe(Consumer<InstalledQuery> consumer) {
        Context context = weakReference.get();
        String device = Build.MODEL;
        String osver = Build.VERSION.RELEASE + "";
        String uuid = UUID.randomUUID().toString();
        String apkver =  Utils.getVersionCode(context);
        InstalledQuery installedQuery = new InstalledQuery();
        installedQuery.setUuid(uuid);
        installedQuery.setDevice(device);
        installedQuery.setOsver(osver);
        installedQuery.setApkver(apkver);
        try {
            consumer.accept(installedQuery);
        }catch (Exception e){

        }
    }


}
