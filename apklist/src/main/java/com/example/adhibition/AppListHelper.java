package com.example.adhibition;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.example.adhibition.model.PMAppInfo;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


/**
 * appList获取应用信息
 */
public class AppListHelper {

    public static void subscribe(Context context, Consumer<List<PMAppInfo>> consumer) {

        PackageManager packageManage = context.getPackageManager();
        Observable.just(packageManage).map(pm -> {
           List<ApplicationInfo> applicationInfoList = pm.getInstalledApplications(PackageManager.GET_UNINSTALLED_PACKAGES);
           return applicationInfoList;
        }).subscribe(item -> {
            Observable.fromIterable(item).filter(app -> {
                        return ((app.flags & ApplicationInfo.FLAG_SYSTEM) <= 0) || ((app.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) != 0);
                    }
              ).flatMap(temp -> {
                 return Observable.just(temp).map(applicationInfo -> {
                      PackageInfo packageInfo = packageManage.getPackageInfo(
                              temp.packageName, 0);
                      PMAppInfo pmAppInfo = new PMAppInfo(temp.loadLabel(packageManage).toString(),temp.packageName, packageInfo.versionName);
                      return pmAppInfo;
                  }).onErrorReturnItem(new PMAppInfo("-1","-1","-1"));
            }).filter(appInfo -> !appInfo.getAppName().equals("-1")).toList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(consumer);
        });

    }


}
